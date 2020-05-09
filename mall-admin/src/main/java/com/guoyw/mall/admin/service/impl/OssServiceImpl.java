package com.guoyw.mall.admin.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.guoyw.mall.admin.dto.OssCallbackParamDTO;
import com.guoyw.mall.admin.service.OssService;
import com.guoyw.mall.admin.vo.OssPolicyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: guoyw
 * create: 2020-05-09 10:56
 **/
@Service
@Slf4j
public class OssServiceImpl implements OssService{
  
  @Value("${aliyun.oss.policy.expire}")
  private int ALIYUN_OSS_EXPIRE;
  @Value("${aliyun.oss.maxSize}")
  private int ALIYUN_OSS_MAX_SIZE;
  @Value("${aliyun.oss.callback}")
  private String ALIYUN_OSS_CALLBACK;
  @Value("${aliyun.oss.bucketName}")
  private String ALIYUN_OSS_BUCKET_NAME;
  @Value("${aliyun.oss.endpoint}")
  private String ALIYUN_OSS_ENDPOINT;
  @Value("${aliyun.oss.dir.prefix}")
  private String ALIYUN_OSS_DIR_PREFIX;
  
  @Autowired
  private OSSClient ossClient;
  
  //region oss文件上传签名生成
  public OssPolicyVO policy() {
    OssPolicyVO ossPolicyVo = new OssPolicyVO();

    // 存储目录
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String dir = ALIYUN_OSS_DIR_PREFIX+sdf.format(new Date());
    
    // 签名有效期
    long expireEndTime = System.currentTimeMillis()+ALIYUN_OSS_EXPIRE*1000;
    Date expiration = new Date(expireEndTime);
  
    // 文件大小
    long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
  
    // 回调
    OssCallbackParamDTO callback = new OssCallbackParamDTO();
    callback.setCallbackUrl(ALIYUN_OSS_CALLBACK);
    callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
    callback.setCallbackBodyType("application/x-www-form-urlencoded");
    // 提交节点
    String action = "http://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT;
    try {
      PolicyConditions policyConds = new PolicyConditions();
      policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
      policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
      String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
      byte[] binaryData = postPolicy.getBytes("utf-8");
      String policy = BinaryUtil.toBase64String(binaryData);
      String signature = ossClient.calculatePostSignature(postPolicy);
      String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes("utf-8"));
      // 返回结果
      ossPolicyVo.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
      ossPolicyVo.setPolicy(policy);
      ossPolicyVo.setSignature(signature);
      ossPolicyVo.setDir(dir);
      ossPolicyVo.setCallback(callbackData);
      ossPolicyVo.setHost(action);
    } catch (Exception e) {
      log.error("签名生成失败", e);
    }
    return ossPolicyVo;
  }
  
  
}
