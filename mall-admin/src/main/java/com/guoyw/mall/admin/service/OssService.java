package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.vo.OssPolicyVO;

/**
 * oss上传管理
 * @author: guoyw
 * create: 2020-05-09 10:55
 **/
public interface OssService{
  
  // oss上传文件签名
  OssPolicyVO policy();
}
