package com.guoyw.mall.admin.controller;

import com.guoyw.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall-demo
 * @description: 商品分类模块
 * @author: guoyw
 * @create: 2020-04-25 17:05
 **/
@RestController
@RequestMapping("/api/productCategory")
@Api(tags = "PmsProductCategoryController",description = "商品分类管理")
public class PmsProductCategoryController {

  @ApiOperation("添加产品分类")
  @RequestMapping("/create")
  @PreAuthorize("hasAnyAuthority('pms:productCategory:create')")
  public CommonResult create(){
    return null;
  }
}
