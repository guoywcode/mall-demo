package com.guoyw.mall.admin.dao;

import com.guoyw.mall.admin.vo.PmsProductCategoryWithChildrenItemVO;
import java.util.List;

/**
 * 商品分类自定义dao
 * @author: guoyw
 * create: 2020-04-27 14:55
 **/

public interface PmsProductCategoryDao{
  List<PmsProductCategoryWithChildrenItemVO> listWithChildren();
}
