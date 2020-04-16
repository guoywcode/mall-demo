package com.guoyw.mall.admin.dao;

import com.guoyw.mall.mbg.model.UmsAdminRoleRelation;
import com.guoyw.mall.mbg.model.UmsPermission;
import com.guoyw.mall.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: mall-demo
 * @description: 后台用户与角色管理自定义Dao
 * @author: guoyw
 * @create: 2020-04-16 20:49
 **/

public interface UmsAdminRoleRelationDao {
  /**
   * 批量插入用户角色关系
   */
  int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

  /**
   * 获取用于所有角色
   */
  List<UmsRole> getRoleList(@Param("adminId") Long adminId);

  /**
   * 获取用户所有角色权限
   */
  List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

  /**
   * 获取用户所有权限
   */
  List<UmsPermission> getPermissionList(@Param("admnId") Long adminId);

}
