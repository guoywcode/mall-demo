package com.guoyw.mall.admin.controller;

import com.guoyw.mall.admin.dto.PmsProductDto;
import com.guoyw.mall.admin.service.PmsProductService;
import com.guoyw.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理
 * @author: guoyw
 * create: 2020-04-23 15:15
 **/
@RestController
@Api(tags = "PmsProductController",description = "商品管理")
@RequestMapping("/api/product")
public class PmsProductController{
  
  @Autowired
  private PmsProductService pmsProductService;
  
  @ApiOperation("创建商品")
  @PostMapping("/create")
  @PreAuthorize("hasAnyAuthority('pms:product:create')")
  public CommonResult create(@Validated @RequestBody PmsProductDto pmsProductDto){
    int count = pmsProductService.create(pmsProductDto);
    if(count > 0)
      return CommonResult.success(count);
    return CommonResult.failed();
  }
  
  @ApiOperation("更新商品")
  @PostMapping("/update/{id}")
  @PreAuthorize("hasAnyAuthority('pms:product:update')")
  public CommonResult update(@PathVariable Long id ,@Validated @RequestBody PmsProductDto pmsProductDto){
    int count = pmsProductService.update(id,pmsProductDto);
    if(count > 0)
      return CommonResult.success(null);
    return CommonResult.failed();
  }
  
  @ApiOperation("删除商品")
  @PostMapping("/delete/{id}")
  @PreAuthorize("hasAnyAuthority('pms:product:delete')")
  public CommonResult delete(@PathVariable Long id){
    int count = pmsProductService.delete(id);
    if(count > 0)
      return CommonResult.success(null);
    return CommonResult.failed();
  }
}
