package com.guoyw.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.guoyw.mall.admin.dao.PmsProductCategoryAttributeRelationDao;
import com.guoyw.mall.admin.dao.PmsProductCategoryDao;
import com.guoyw.mall.admin.dto.PmsProductCategoryDTO;
import com.guoyw.mall.admin.vo.PmsProductCategoryWithChildrenItemVO;
import com.guoyw.mall.admin.service.PmsProductCategoryService;
import com.guoyw.mall.mbg.mapper.PmsProductCategoryAttributeRelationMapper;
import com.guoyw.mall.mbg.mapper.PmsProductCategoryMapper;
import com.guoyw.mall.mbg.mapper.PmsProductMapper;
import com.guoyw.mall.mbg.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
  @Autowired
  private PmsProductMapper productMapper;
  @Autowired
  private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;
  @Autowired
  private PmsProductCategoryDao pmsProductCategoryDao;
  
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
  
  //region 修改商品分类
  @Override
  public int update(Long id, PmsProductCategoryDTO pmsProductCategoryDTO){
    PmsProductCategory pmsProductCategory = new PmsProductCategory();
    pmsProductCategory.setId(id);
    BeanUtils.copyProperties(pmsProductCategoryDTO,pmsProductCategory);
    // 设置分类等级
    this.setCategoryLevel(pmsProductCategory);
    // 更新商品分类事要更新商品中的名称
    PmsProduct pmsProduct = new PmsProduct();
    pmsProduct.setProductCategoryName(pmsProductCategory.getName());
    PmsProductExample example = new PmsProductExample();
    example.createCriteria().andProductCategoryIdEqualTo(id);
    productMapper.updateByExampleSelective(pmsProduct,example);
    
    // 同时更新筛选属性的信息
    PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
    relationExample.createCriteria().andProductCategoryIdEqualTo(id);
    productCategoryAttributeRelationMapper.deleteByExample(relationExample);
    if(!CollectionUtils.isEmpty(pmsProductCategoryDTO.getProductAttributeIdList())){
      this.insertRelationList(id,pmsProductCategoryDTO.getProductAttributeIdList());
    }
    
    return productCategoryMapper.updateByPrimaryKey(pmsProductCategory);
  }
  //endregion
  
  // 查询商品分类-分页
  @Override
  public List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum){
    PageHelper.startPage(pageNum,pageSize);
    PmsProductCategoryExample example = new PmsProductCategoryExample();
    example.setOrderByClause("sort desc");
    example.createCriteria().andParentIdEqualTo(parentId);
    return productCategoryMapper.selectByExample(example);
  }
  //endregion
  
  // 查询商品分类-根据id
  @Override
  public PmsProductCategory getItem(Long id){
    return productCategoryMapper.selectByPrimaryKey(id);
  }
  //endregion
  
  // 删除商品分类
  @Override
  public int delete(Long id){
    return productCategoryMapper.deleteByPrimaryKey(id);
  }
  //endregion
  
  // 获取商品分类一级分类&子类
  @Override
  public List<PmsProductCategoryWithChildrenItemVO> listWithChildren(){
    return pmsProductCategoryDao.listWithChildren();
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
