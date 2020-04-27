package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.dto.PmsBrandDTO;
import com.guoyw.mall.mbg.model.PmsBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: guoyw
 * create: 2020-04-27 15:29
 **/

public interface PmsBrandService{
  
  // 创建商品品牌
  @Transactional
  int create(PmsBrandDTO pmsBrandDTO);
  
  // 更新商品品牌
  @Transactional
  int update(Long id, PmsBrandDTO pmsBrandDTO);
  
  // 删除商品品牌
  @Transactional
  int delete(Long id);
  
  // 获取全部的列表
  List<PmsBrand> getAllList();
  
  // 获取列表-可分页
  List<PmsBrand> getList(String keyword, Integer pageNum, Integer pageSize);
  
  // 获取商品信息-根据id
  PmsBrand getItem(Long id);
}
