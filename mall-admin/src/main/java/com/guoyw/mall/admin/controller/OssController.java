package com.guoyw.mall.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

 /* @ApiOperation(value = "oss上传签名生成")
  @GetMapping(value = "/policy")
  public CommonResult<OssPolicyResult> policy() {
    OssPolicyResult result = ossService.policy();
    return CommonResult.success(result);
  }*/

}
