package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.dto.PmsProductCategoryDTO;
import com.guoyw.mall.admin.vo.PmsProductCategoryWithChildrenItemVO;
import com.guoyw.mall.mbg.model.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
  产品分类service
 */
public interface PmsProductCategoryService {

  // 创建商品分类
  @Transactional
  int create(PmsProductCategoryDTO pmsProductCategoryDTO);
  
  // 修改商品分类
  @Transactional
  int update(Long id, PmsProductCategoryDTO pmsProductCategoryDTO);
  
  // 查询商品分类-分页
  List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);
  
  // 查询商品-根据id
  PmsProductCategory getItem(Long id);
  
  // 删除商品分类
  @Transactional
  int delete(Long id);
  
  // 获取商品分类一级列表&和之类
  List<PmsProductCategoryWithChildrenItemVO> listWithChildren();
}
