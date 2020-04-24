package com.guoyw.mall.admin.controller;

import com.guoyw.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理
 * @author: guoyw
 * create: 2020-04-23 15:15
 **/
@RestController
@Api(tags = "PmsProductController",description = "商品管理")
@RequestMapping("/api/product")
public class PmsProductController{
  
  @ApiOperation("创建商品")
  @PostMapping("/create")
  @PreAuthorize("hasAnyAuthority('pms:product:create')")
  public CommonResult create(){
    
    return CommonResult.success(null);
  }
  
}
