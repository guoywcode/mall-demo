package com.guoyw.mall.admin.vo;

import com.guoyw.mall.mbg.model.PmsProductCategory;
import lombok.Data;

import java.util.List;

/**
 * @author: guoyw
 * create: 2020-04-27 14:49
 **/
@Data
public class PmsProductCategoryWithChildrenItemVO extends PmsProductCategory{
  
  private List<PmsProductCategory> children;
}
