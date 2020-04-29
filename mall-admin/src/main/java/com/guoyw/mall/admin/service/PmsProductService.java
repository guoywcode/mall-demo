package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.dto.PmsProductDto;

/**
 * 商品管理
 * @author: guoyw
 * create: 2020-04-28 14:39
 **/

public interface PmsProductService{
  
  // 创建商品
  int create(PmsProductDto pmsProductDto);
  
  // 更新商品
  int update(Long id, PmsProductDto pmsProductDto);
  
  // 删除商品
  int delete(Long id);
}
