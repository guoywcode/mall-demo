package com.guoyw.mall.admin.dao;

import com.guoyw.mall.mbg.model.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品分类和属性关系Dao
 * @author: guoyw
 * create: 2020-04-26 17:28
 **/

public interface PmsProductCategoryAttributeRelationDao{
  
  int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);
  
}
