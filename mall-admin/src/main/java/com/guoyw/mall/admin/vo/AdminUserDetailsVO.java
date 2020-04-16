package com.guoyw.mall.admin.vo;

import com.guoyw.mall.mbg.model.UmsAdmin;
import com.guoyw.mall.mbg.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: mall-demo
 * @description: SpringSecurity需要的用户详情
 * @author: guoyw
 * @create: 2020-04-16 20:39
 **/

public class AdminUserDetailsVO implements UserDetails {

  private UmsAdmin umsAdmin;
  private List<UmsPermission> permissionList;

  public AdminUserDetailsVO(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
    this.umsAdmin = umsAdmin;
    this.permissionList = permissionList;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //返回当前用户的权限
    return permissionList.stream()
        .filter(umsPermission -> umsPermission.getValue() != null)
        .map(umsPermission -> new SimpleGrantedAuthority(umsPermission.getValue()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return umsAdmin.getPassword();
  }

  @Override
  public String getUsername() {
    return umsAdmin.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return umsAdmin.getStatus().equals(1);
  }
}
