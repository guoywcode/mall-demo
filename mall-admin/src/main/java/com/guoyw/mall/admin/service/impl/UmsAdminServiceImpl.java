package com.guoyw.mall.admin.service.impl;

import com.guoyw.mall.admin.dto.UmsAdminParamDTO;
import com.guoyw.mall.admin.service.UmsAdminService;
import com.guoyw.mall.mbg.mapper.UmsAdminMapper;
import com.guoyw.mall.mbg.model.UmsAdmin;
import com.guoyw.mall.mbg.model.UmsAdminExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-14 23:56
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

  @Autowired
  private UmsAdminMapper umsAdminMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  //region 获取用户管理员根据用户名
  @Override
  public UmsAdmin getUmsAdminByUsername(String username) {
    UmsAdminExample example = new UmsAdminExample();
    example.createCriteria()
        .andUsernameEqualTo(username);
    List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
    if(umsAdminList != null && umsAdminList.size() > 0)
      return umsAdminList.get(0);

    return null;
  }
  //endregion

  //region 后台管理员注册功能
  @Override
  public UmsAdmin register(UmsAdminParamDTO umsAdminParamDTO) {
    UmsAdmin varUmsAdmin = new UmsAdmin();
    BeanUtils.copyProperties(umsAdminParamDTO,varUmsAdmin);
    varUmsAdmin.setCreateTime(new Date());
    varUmsAdmin.setStatus(1);

    //查询是否有同用户名的用户
    UmsAdmin umsAdmin = this.getUmsAdminByUsername(varUmsAdmin.getUsername());
    if(umsAdmin != null)
      return null;

    //将密码进行加密
    String encodePassword = passwordEncoder.encode(varUmsAdmin.getPassword());
    varUmsAdmin.setPassword(encodePassword);
    umsAdminMapper.insert(varUmsAdmin);
    return varUmsAdmin;
  }
  //endregion

  @Override
  public UserDetails loadUserByUsername(String username) {
    UmsAdmin admin = getUmsAdminByUsername(username);
/*    if (admin != null) {
      List<UmsPermission> permissionList = getPermissionList(admin.getId());
      return new AdminUserDetails(admin,permissionList);
    }*/
    throw new UsernameNotFoundException("用户名或密码错误");
  }

}
