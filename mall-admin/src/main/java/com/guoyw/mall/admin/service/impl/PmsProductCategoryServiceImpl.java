package com.guoyw.mall.admin.service.impl;

import com.guoyw.mall.admin.dao.PmsProductCategoryAttributeRelationDao;
import com.guoyw.mall.admin.dto.PmsProductCategoryDTO;
import com.guoyw.mall.admin.service.PmsProductCategoryService;
import com.guoyw.mall.mbg.mapper.PmsProductCategoryMapper;
import com.guoyw.mall.mbg.model.PmsProductCategory;
import com.guoyw.mall.mbg.model.PmsProductCategoryAttributeRelation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: guoyw
 * create: 2020-04-26 16:01
 **/
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService{
  
  @Autowired
  private PmsProductCategoryMapper productCategoryMapper;
  @Autowired
  private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;
  
  //region 创建商品分类
  @Override
  public int create(PmsProductCategoryDTO pmsProductCategoryDTO){
  
    PmsProductCategory pmsProductCategory = new PmsProductCategory();
    BeanUtils.copyProperties(pmsProductCategoryDTO,pmsProductCategory);
    pmsProductCategory.setProductCount(0);
    // 设置分类等级
    this.setCategoryLevel(pmsProductCategory);
    int count = productCategoryMapper.insertSelective(pmsProductCategory);
    //创建筛选属性关联
    List<Long> productAttributeIdList = pmsProductCategoryDTO.getProductAttributeIdList();
    if(!CollectionUtils.isEmpty(productAttributeIdList))
      this.insertRelationList(pmsProductCategory.getId(), productAttributeIdList);
    return count;
  }
  //endregion
  
  // 根据分类的parentId这是分类的level
  private void setCategoryLevel(PmsProductCategory productCategory){
    //没有父分类时为一级分类
    if(productCategory.getParentId() == 0){
      productCategory.setLevel(0);
    }else {
      //有父分类时选择根据父分类level设置
      PmsProductCategory parentCategory = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
      if (parentCategory != null) {
        productCategory.setLevel(parentCategory.getLevel() + 1);
      } else {
        productCategory.setLevel(0);
      }
    }
  }
  
  
  /**
   * 批量插入商品分类与筛选属性关系表
   * @param productCategoryId 商品分类id
   * @param productAttributeIdList 相关商品筛选属性id集合
   */
  private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
    List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
    for (Long productAttrId : productAttributeIdList) {
      PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
      relation.setProductAttributeId(productAttrId);
      relation.setProductCategoryId(productCategoryId);
      relationList.add(relation);
    }
    productCategoryAttributeRelationDao.insertList(relationList);
  }
}
