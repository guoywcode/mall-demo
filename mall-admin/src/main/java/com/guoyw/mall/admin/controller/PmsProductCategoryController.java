package com.guoyw.mall.admin.controller;

import com.guoyw.mall.admin.dto.PmsProductCategoryDTO;
import com.guoyw.mall.admin.vo.PmsProductCategoryWithChildrenItemVO;
import com.guoyw.mall.admin.service.PmsProductCategoryService;
import com.guoyw.mall.common.api.CommonPage;
import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.mbg.model.PmsProductCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @program: mall-demo
 * @description: 商品分类模块
 * @author: guoyw
 * @create: 2020-04-25 17:05
 **/
@RestController
@RequestMapping("/api/productCategory")
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
public class PmsProductCategoryController{

  @Autowired
  private PmsProductCategoryService pmsProductCategoryService;

  @ApiOperation("添加商品分类")
  @PostMapping("/create")
  @PreAuthorize("hasAnyAuthority('pms:productCategory:create')")
  public CommonResult create(@Validated @RequestBody PmsProductCategoryDTO pmsProductCategoryDTO){
    int count = pmsProductCategoryService.create(pmsProductCategoryDTO);
    if(count > 0){
      return CommonResult.success(count);
    }else{
      return CommonResult.failed();
    }
  }

  @ApiOperation("更新商品分类")
  @PutMapping("/update/{id}")
  @PreAuthorize("hasAnyAuthority('pms:productCategory:update')")
  public CommonResult update(@PathVariable Long id,@Validated @RequestBody PmsProductCategoryDTO pmsProductCategoryDTO){
    int count = pmsProductCategoryService.update(id,pmsProductCategoryDTO);
    if(count > 0)
      return CommonResult.success(count);
    else
      return CommonResult.failed();

  }

  @ApiOperation("查询商品分类-分页")
  @GetMapping("/page/{parentId}")
  @PreAuthorize("hasAnyAuthority('pms:productCategory:read')")
  public CommonResult<CommonPage<PmsProductCategory>> getPage(@PathVariable Long parentId,
                                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
    List<PmsProductCategory> productCategoryList =  pmsProductCategoryService.getList(parentId,pageSize,pageNum);
    return CommonResult.success(CommonPage.restPage(productCategoryList));
  }

  @ApiOperation("根据id获取商品分类")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('pms:productCategory:read')")
  public CommonResult<PmsProductCategory> getItem(@PathVariable Long id) {
    PmsProductCategory productCategory = pmsProductCategoryService.getItem(id);
    return CommonResult.success(productCategory);
  }

  @ApiOperation("删除商品分类")
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('pms:productCategory:delete')")
  public CommonResult delete(@PathVariable Long id) {
    int count = pmsProductCategoryService.delete(id);
    if (count > 0) {
      return CommonResult.success(count);
    } else {
      return CommonResult.failed();
    }
  }

  @ApiOperation("查询所有一级分类及子分类")
  @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('pms:productCategory:read')")
  public CommonResult<List<PmsProductCategoryWithChildrenItemVO>> listWithChildren() {
    List<PmsProductCategoryWithChildrenItemVO> list = pmsProductCategoryService.listWithChildren();
    return CommonResult.success(list);
  }
  
  @ApiOperation("修改导航栏显示状态")
  @PostMapping("/updateNavStatus")
//  @PreAuthorize("hasAnyAuthority('pms:productCategory:updateNavStatus')")
  public CommonResult updateNavStatus(@RequestParam List<Long> ids,@RequestParam Integer navStatus){
    int count = pmsProductCategoryService.updateNavStatus(ids,navStatus);
    if(count > 0)
      return CommonResult.success(count);
    else
      return CommonResult.failed();
  }
  
  @ApiOperation("修改显示状态")
  @PostMapping("/updateShowStatus")
//  @PreAuthorize("hasAnyAuthority('pms:productCategory:updateShowStatus')")
  public CommonResult updateShowStatus(@RequestParam List<Long> ids,@RequestParam Integer showStatus){
    int count = pmsProductCategoryService.updateShowStatus(ids,showStatus);
    if(count > 0)
      return CommonResult.success(count);
    else
      return CommonResult.failed();
  }
  
}
