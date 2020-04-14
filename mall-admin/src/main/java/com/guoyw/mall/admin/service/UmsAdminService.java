package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.dto.UmsAdminParamDTO;
import com.guoyw.mall.mbg.model.UmsAdmin;

/**
 * @program: mall-demo
 * @description: 用户管理service
 * @author: guoyw
 * @create: 2020-04-14 23:52
 **/

public interface UmsAdminService {

  // 根据用户名获取后台管理员
  UmsAdmin getUmsAdminByUsername(String username);

  // 后台管理员注册功能
  UmsAdmin register(UmsAdminParamDTO umsAdminParamDTO);
}
