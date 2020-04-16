package com.guoyw.mall.admin.service;

import com.guoyw.mall.admin.dto.UmsAdminDTO;
import com.guoyw.mall.admin.dto.UmsAdminLoginDTO;
import com.guoyw.mall.mbg.model.UmsAdmin;
import com.guoyw.mall.mbg.model.UmsPermission;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

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
  UmsAdmin register(UmsAdminDTO umsAdminDTO);


  /**
   * 获取用户所有权限（包括角色权限和+-权限）
   */
  List<UmsPermission> getPermissionList(Long adminId);

  /**
   * 获取用户信息
   */
  UserDetails loadUserByUsername(String username);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
  String login(String username, String password);

  /**
   * 刷新token的功能
   * @param oldToken 旧的token
   */
  String refreshToken(String oldToken);
}
