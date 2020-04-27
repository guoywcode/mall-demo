package com.guoyw.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.guoyw.mall.admin.dto.PmsBrandDTO;
import com.guoyw.mall.admin.service.PmsBrandService;
import com.guoyw.mall.mbg.mapper.PmsBrandMapper;
import com.guoyw.mall.mbg.mapper.PmsProductMapper;
import com.guoyw.mall.mbg.model.PmsBrand;
import com.guoyw.mall.mbg.model.PmsBrandExample;
import com.guoyw.mall.mbg.model.PmsProduct;
import com.guoyw.mall.mbg.model.PmsProductExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品品牌Service的的实现
 * @author: guoyw
 * create: 2020-04-27 15:39
 **/

@Service
public class PmsBrandServiceImpl implements PmsBrandService{
  
  @Autowired
  private PmsBrandMapper brandMapper;
  @Autowired
  private PmsProductMapper productMapper;
  
  //region 创建商品品牌
  @Override
  public int create(PmsBrandDTO pmsBrandDTO){
    PmsBrand brand = new PmsBrand();
    BeanUtils.copyProperties(pmsBrandDTO,brand);
    
    //如果创建时首字母为空，则取名称的第一个字母
    if(StringUtils.isEmpty(pmsBrandDTO.getFirstLetter()))
      pmsBrandDTO.setFirstLetter(pmsBrandDTO.getName().substring(0,1));
    
    return brandMapper.insertSelective(brand);
  }
  //endregion
  
  //region 更新商品品牌
  @Override
  public int update(Long id, PmsBrandDTO pmsBrandDTO){
    PmsBrand brand = new PmsBrand();
    BeanUtils.copyProperties(pmsBrandDTO,brand);
    brand.setId(id);
  
    //如果创建时首字母为空，则取名称的第一个字母
    if(StringUtils.isEmpty(pmsBrandDTO.getFirstLetter()))
      pmsBrandDTO.setFirstLetter(pmsBrandDTO.getName().substring(0,1));
    
    //更新商品品牌时，更新商品中的品牌名
    PmsProduct pmsProduct =  new PmsProduct();
    pmsProduct.setBrandName(brand.getName());
    PmsProductExample example = new PmsProductExample();
    example.createCriteria().andBrandIdEqualTo(id);
    productMapper.updateByExampleSelective(pmsProduct,example);
    
    return brandMapper.updateByPrimaryKey(brand);
  }
  //endregion
  
  //region 删除商品品牌
  @Override
  public int delete(Long id){
    return brandMapper.deleteByPrimaryKey(id);
  }
  //endregion
  
  //region 获取所有商品品牌列表
  @Override
  public List<PmsBrand> getAllList(){
    return brandMapper.selectByExample(new PmsBrandExample());
  }
  //endregion
  
  //region 获取商品品牌列表
  @Override
  public List<PmsBrand> getList(String keyword, Integer pageNum, Integer pageSize){
    PageHelper.startPage(pageNum,pageSize);
    PmsBrandExample example = new PmsBrandExample();
    example.setOrderByClause("sort desc");
    PmsBrandExample.Criteria criteria = example.createCriteria();
    if (!StringUtils.isEmpty(keyword)) {
      criteria.andNameLike("%" + keyword + "%");
    }
  
    return brandMapper.selectByExample(example);
  }
  //endregion
  
  //region 获取商品品牌信息-根据id
  @Override
  public PmsBrand getItem(Long id){
    return brandMapper.selectByPrimaryKey(id);
  }
  //endregion
}
