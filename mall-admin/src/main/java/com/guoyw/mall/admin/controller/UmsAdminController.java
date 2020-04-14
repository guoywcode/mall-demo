package com.guoyw.mall.admin.controller;

import com.guoyw.mall.admin.dto.UmsAdminParamDTO;
import com.guoyw.mall.admin.service.UmsAdminService;
import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.mbg.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: mall-demo
 * @description: 系统用户管理
 * @author: guoyw
 * @create: 2020-04-14 23:30
 **/

@RestController
@Api(tags = "UmsAdminController",description = "后台用户管理")
@RequestMapping("/api/admin")
public class UmsAdminController {

  @Autowired
  private UmsAdminService umsAdminService;

  @ApiOperation(value = "用户注册")
  @PostMapping(value = "/register")
  public CommonResult<UmsAdmin> register(@RequestBody @Valid UmsAdminParamDTO umsAdminParamDTO){
    UmsAdmin umsAdmin = umsAdminService.register(umsAdminParamDTO);
    if (umsAdmin == null) {
      CommonResult.failed();
    }
    return CommonResult.success(umsAdmin);
  }

}
