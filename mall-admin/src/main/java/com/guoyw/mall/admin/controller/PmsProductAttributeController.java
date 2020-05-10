package com.guoyw.mall.admin.controller;

import com.guoyw.mall.admin.dto.PmsProductAttributeDTO;
import com.guoyw.mall.admin.service.PmsProductAttributeService;
import com.guoyw.mall.common.api.CommonPage;
import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.mbg.model.PmsProductAttribute;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 商品属性管理
 * @author: guoyw
 * create: 2020-04-27 16:59
 **/
@RestController
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/api/productAttribute")
public class PmsProductAttributeController{
  
  @Autowired
  private PmsProductAttributeService pmsProductAttributeService;
  
  @ApiOperation("添加商品属性")
  @PostMapping("/create")
  @PreAuthorize("hasAnyAuthority('pms:attribute:create')")
  public CommonResult create(@Validated @RequestBody PmsProductAttributeDTO pmsProductAttributeDTO){
    int count = pmsProductAttributeService.create(pmsProductAttributeDTO);
    if(count > 0)
      return CommonResult.success(count);
    
    return CommonResult.failed();
  }
  
  @ApiOperation("更新商品属性")
  @PostMapping("/update/{id}")
  @PreAuthorize("hasAnyAuthority('pms:attribute:update')")
  public CommonResult update(@PathVariable Long id, @Validated @RequestBody PmsProductAttributeDTO pmsProductAttributeDTO){
    int count = pmsProductAttributeService.update(id,pmsProductAttributeDTO);
    if(count > 0)
      return CommonResult.success(count);
    
    return CommonResult.failed();
  }
  
  @ApiOperation("更新商品属性")
  @PostMapping("/delete/{id}")
  @PreAuthorize("hasAnyAuthority('pms:attribute:delete')")
  public CommonResult delete(@PathVariable Long id){
    int count = pmsProductAttributeService.delete(id);
    if(count > 0)
      return CommonResult.success(count);
    
    return CommonResult.failed();
  }
  
  @ApiOperation("获取商品属性列表")
  @PostMapping("/list")
  @PreAuthorize("hasAnyAuthority('pms:attribute:list')")
  public CommonResult getList(@PathVariable Long id,
                              @RequestParam(value = "type") Integer type,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
    List<PmsProductAttribute> productAttributeList = pmsProductAttributeService.getList(id, type, pageSize, pageNum);
    return CommonResult.success(CommonPage.restPage(productAttributeList));
  }
 
  @ApiOperation("获取商品属性-根据分类id")
  @GetMapping("/list/{productCategoryId}")
  public CommonResult getListByCategoryId(@PathVariable Long productCategoryId){
    List<PmsProductAttribute> list = pmsProductAttributeService.getListByCategoryId(productCategoryId);
    return CommonResult.success(list);
  }
}
