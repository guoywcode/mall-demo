package com.guoyw.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.guoyw.mall.admin.dto.PmsProductAttributeDTO;
import com.guoyw.mall.admin.service.PmsProductAttributeService;
import com.guoyw.mall.mbg.mapper.PmsProductAttributeCategoryMapper;
import com.guoyw.mall.mbg.mapper.PmsProductAttributeMapper;
import com.guoyw.mall.mbg.model.PmsProductAttribute;
import com.guoyw.mall.mbg.model.PmsProductAttributeCategory;
import com.guoyw.mall.mbg.model.PmsProductAttributeExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: guoyw
 * create: 2020-04-28 11:43
 **/
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService{
  
  @Autowired
  private PmsProductAttributeMapper productAttributeMapper;
  @Autowired
  private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
  
  //region 创建商品属性分类
  @Override
  public int create(PmsProductAttributeDTO pmsProductAttributeDTO){
    PmsProductAttribute productAttribute = new PmsProductAttribute();
    BeanUtils.copyProperties(pmsProductAttributeDTO,productAttribute);
    int count = productAttributeMapper.insertSelective(productAttribute);
    
    // 新增商品属性以后需要更新商品属性分类数量
    PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
    if(productAttribute.getType()==0){
      productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount()+1);
    }else if(productAttribute.getType()==1){
      productAttributeCategory.setParamCount(productAttributeCategory.getParamCount()+1);
    }
    productAttributeCategoryMapper.updateByPrimaryKey(productAttributeCategory);
    return count;
  }
  //endregion
  
  //region 更新商品属性
  @Override
  public int update(Long id, PmsProductAttributeDTO pmsProductAttributeDTO){
    PmsProductAttribute productAttribute = new PmsProductAttribute();
    BeanUtils.copyProperties(pmsProductAttributeDTO,productAttribute);
    productAttribute.setId(id);
    return productAttributeMapper.updateByPrimaryKeySelective(productAttribute);
  }
  //endregion
  
  //region 删除商品属性
  @Override
  public int delete(Long id){
    //获取分类
    PmsProductAttribute pmsProductAttribute = productAttributeMapper.selectByPrimaryKey(id);
    Integer type = pmsProductAttribute.getType();
    
    PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
  
    int count = productAttributeMapper.deleteByPrimaryKey(id);
    //删除完成后修改数量
    if(type==0){
      if(pmsProductAttributeCategory.getAttributeCount()>=count){
        pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()-count);
      }else{
        pmsProductAttributeCategory.setAttributeCount(0);
      }
    }else if(type==1){
      if(pmsProductAttributeCategory.getParamCount()>=count){
        pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()-count);
      }else{
        pmsProductAttributeCategory.setParamCount(0);
      }
    }
    productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
    return count;
  }
  //endregion
  
  //region 获取商品属性列表
  @Override
  public List<PmsProductAttribute> getList(Long id, Integer type, Integer pageSize, Integer pageNum){
    PageHelper.startPage(pageNum, pageSize);
    PmsProductAttributeExample example = new PmsProductAttributeExample();
    example.setOrderByClause("sort desc");
    example.createCriteria().andProductAttributeCategoryIdEqualTo(id).andTypeEqualTo(type);
    return productAttributeMapper.selectByExample(example);
  }
  //endregion
  
  //region 获取商品属性-根据商品分类Id
  @Override
  public List<PmsProductAttribute> getListByCategoryId(Long productCategoryId){
    PmsProductAttributeExample example = new PmsProductAttributeExample();
    example.createCriteria().andProductAttributeCategoryIdEqualTo(productCategoryId);
    return productAttributeMapper.selectByExample(example);
  }
  //endregion
}
