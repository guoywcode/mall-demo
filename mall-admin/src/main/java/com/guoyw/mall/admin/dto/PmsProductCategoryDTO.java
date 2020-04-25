package com.guoyw.mall.admin.dto;

import com.guoyw.mall.admin.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @program: mall-demo
 * @description: 产品分类DTO
 * @author: guoyw
 * @create: 2020-04-25 17:19
 **/

@Data
@Accessors(chain = true)
public class PmsProductCategoryDTO implements Serializable {

  private Long parentId;
  @ApiModelProperty(value = "商品分类名称",required = true)
  @NotEmpty(message = "商品分类名称不能为空")
  private String name;
  @ApiModelProperty("分类单位")
  private String productUnit;
  @ApiModelProperty("是否在导航栏显示")
  @FlagValidator(value = {"0","1"},message = "状态只能为0或1")
  private Integer navStatus;
  @ApiModelProperty("是否进行显示")
  @FlagValidator(value = {"0","1"},message = "状态只能为0或1")
  private Integer showStatus;
  @ApiModelProperty("排序")
  @Min(value = 0,message = "排序最小为0")
  private Integer sort;
  @ApiModelProperty("图标")
  private String icon;
  @ApiModelProperty("关键字")
  private String keywords;
  @ApiModelProperty("描述")
  private String description;
  @ApiModelProperty("产品相关筛选属性集合")
  private List<Long> productAttributeIdList;

}
