package com.guoyw.mall.admin.controller;

import com.guoyw.mall.admin.service.OssService;
import com.guoyw.mall.admin.vo.OssPolicyVO;
import com.guoyw.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: mall-demo
 * @description: oss管理
 * @author: guoyw
 * @create: 2020-05-08 21:37
 **/

@RestController
@RequestMapping("/api/oss/aliyun")
@Api(tags ="OssController",description = "oss管理")
public class OssController {
  
  @Autowired
  private OssService ossService;

  @ApiOperation(value = "oss上传签名生成")
  @GetMapping(value = "/policy")
  public CommonResult<OssPolicyVO> policy() {
    OssPolicyVO result = ossService.policy();
    return CommonResult.success(result);
  }

}
