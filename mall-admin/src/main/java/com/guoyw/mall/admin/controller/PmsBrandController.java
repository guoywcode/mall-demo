package com.guoyw.mall.admin.controller;

import com.guoyw.mall.admin.dto.PmsBrandDTO;
import com.guoyw.mall.admin.service.PmsBrandService;
import com.guoyw.mall.common.api.CommonPage;
import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.mbg.model.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: guoyw
 * create: 2020-04-27 15:20
 **/
@RestController
@RequestMapping("/api/brand")
@Api(tags = "PmsBrandController",description = "商品品牌管理")
public class PmsBrandController{
  
  @Autowired
  private PmsBrandService brandService;
  
  @ApiOperation("添加品牌")
  @PostMapping("/create")
//  @PreAuthorize("hasAnyAuthority('pms:brand:create')")
  public CommonResult create(@Validated @RequestBody PmsBrandDTO pmsBrandDTO){
    int count = brandService.create(pmsBrandDTO);
    if(count > 0)
     return CommonResult.success(count);
    return CommonResult.failed();
  }
  
  @ApiOperation("编辑品牌")
  @PostMapping("/update/{id}")
//  @PreAuthorize("hasAnyAuthority('pms:brand:update')")
  public CommonResult update(@PathVariable Long id, @Validated @RequestBody PmsBrandDTO pmsBrandDTO){
    int count = brandService.update(id,pmsBrandDTO);
    if(count > 0)
      return CommonResult.success(count);
    return CommonResult.failed();
  }
  
  @ApiOperation("删除品牌")
  @PostMapping("/delete/{id}")
//  @PreAuthorize("hasAnyAuthority('pms:brand:delete')")
  public CommonResult delete(@PathVariable Long id){
    int count = brandService.delete(id);
    if(count > 0)
      return CommonResult.success(null);
    return CommonResult.failed();
  }
  
  @ApiOperation("获取全部列表")
  @GetMapping("/listAll")
  //@PreAuthorize("hasAnyAuthority('pms:brand:read')")
  public CommonResult<List<PmsBrand>> getAllList(){
    return CommonResult.success(brandService.getAllList());
  }
  
  @ApiOperation(value = "根据品牌名称分页获取品牌列表")
  @GetMapping( "/list")
//  @PreAuthorize("hasAuthority('pms:brand:read')")
  public CommonResult<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
    List<PmsBrand> brandList = brandService.getList(keyword, pageNum, pageSize);
    return CommonResult.success(CommonPage.restPage(brandList));
  }
  
  @ApiOperation(value = "根据编号查询品牌信息")
  @GetMapping("/{id}")
  @ResponseBody
//  @PreAuthorize("hasAuthority('pms:brand:read')")
  public CommonResult<PmsBrand> getItem(@PathVariable("id") Long id) {
    return CommonResult.success(brandService.getItem(id));
  }
  
  @ApiOperation("修改显示状态")
  @PostMapping("/updateShowStatus")
  public CommonResult updateShowStatus(@RequestParam Long id){
  
    int count = brandService.updateShowStatus(id);
    if(count > 0)
    return CommonResult.success(null);
    else
      return CommonResult.failed();
  }
  
}
