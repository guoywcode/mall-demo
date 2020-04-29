package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.dto.PmsProductAttributeDTO;
import com.guoyw.mall.mbg.model.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性管理
 * @author: guoyw
 * create: 2020-04-28 10:33
 **/

public interface PmsProductAttributeService{
  
  // 创建商品属性
  @Transactional
  int create(PmsProductAttributeDTO pmsProductAttributeDTO);
  
  // 更新商品属性
  @Transactional
  int update(Long id, PmsProductAttributeDTO pmsProductAttributeDTO);
  
  // 删除少商品分类
  @Transactional
  int delete(Long id);
  
  // 获取商品分类列表
  List<PmsProductAttribute> getList(Long id, Integer type, Integer pageSize, Integer pageNum);
}
