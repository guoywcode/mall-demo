package com.guoyw.mall.admin.service.impl;

import com.guoyw.mall.admin.dto.PmsProductDto;
import com.guoyw.mall.admin.service.PmsProductService;
import com.guoyw.mall.mbg.mapper.PmsProductMapper;
import com.guoyw.mall.mbg.model.PmsProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: guoyw
 * create: 2020-04-28 14:42
 **/
@Service
public class PmsProductServiceImpl implements PmsProductService{
  
  @Autowired
  private PmsProductMapper productMapper;
  
  //region 创建商品
  @Override
  public int create(PmsProductDto pmsProductDto){
    PmsProduct product = new PmsProduct();
    BeanUtils.copyProperties(pmsProductDto,pmsProductDto);
    int count = productMapper.insertSelective(product);
    return count;
  }
  //endregion
  
  //region 更新商品
  @Override
  public int update(Long id, PmsProductDto pmsProductDto){
    return 0;
  }
  //endregion
  
  //region 删除商品
  @Override
  public int delete(Long id){
    return 0;
  }
  //endregion
}
