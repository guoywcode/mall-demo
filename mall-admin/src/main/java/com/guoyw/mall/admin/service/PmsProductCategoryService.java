package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.dto.PmsProductCategoryDTO;
import org.springframework.transaction.annotation.Transactional;

/*
  产品分类service
 */
public interface PmsProductCategoryService {

  /*
   * 创建商品分类
   */
  @Transactional
  int create(PmsProductCategoryDTO pmsProductCategoryDTO);

}
