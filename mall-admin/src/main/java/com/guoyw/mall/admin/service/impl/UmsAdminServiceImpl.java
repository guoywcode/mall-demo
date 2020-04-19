package com.guoyw.mall.admin.service.impl;

import com.guoyw.mall.admin.dao.UmsAdminRoleRelationDao;
import com.guoyw.mall.admin.dto.UmsAdminDTO;
import com.guoyw.mall.admin.service.UmsAdminService;
import com.guoyw.mall.admin.vo.AdminUserDetailsVO;
import com.guoyw.mall.mbg.mapper.UmsAdminLoginLogMapper;
import com.guoyw.mall.mbg.mapper.UmsAdminMapper;
import com.guoyw.mall.mbg.model.UmsAdmin;
import com.guoyw.mall.mbg.model.UmsAdminExample;
import com.guoyw.mall.mbg.model.UmsAdminLoginLog;
import com.guoyw.mall.mbg.model.UmsPermission;
import com.guoyw.mall.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-14 23:56
 **/
@Service
@Slf4j
public class UmsAdminServiceImpl implements UmsAdminService {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private UmsAdminMapper umsAdminMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UmsAdminRoleRelationDao adminRoleRelationDao;
  @Autowired
  private UmsAdminLoginLogMapper loginLogMapper;

  //region 获取用户管理员根据用户名
  @Override
  public UmsAdmin getUmsAdminByUsername(String username) {
    UmsAdminExample example = new UmsAdminExample();
    example.createCriteria()
        .andUsernameEqualTo(username);
    List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
    if (umsAdminList != null && umsAdminList.size() > 0)
      return umsAdminList.get(0);

    return null;
  }
  //endregion

  //region 后台管理员注册功能
  @Override
  public UmsAdmin register(UmsAdminDTO umsAdminDTO) {
    UmsAdmin varUmsAdmin = new UmsAdmin();
    BeanUtils.copyProperties(umsAdminDTO, varUmsAdmin);
    varUmsAdmin.setCreateTime(new Date());
    varUmsAdmin.setStatus(1);

    //查询是否有同用户名的用户
    UmsAdmin umsAdmin = this.getUmsAdminByUsername(varUmsAdmin.getUsername());
    if (umsAdmin != null)
      return null;

    //将密码进行加密
    String encodePassword = passwordEncoder.encode(varUmsAdmin.getPassword());
    varUmsAdmin.setPassword(encodePassword);
    umsAdminMapper.insert(varUmsAdmin);
    return varUmsAdmin;
  }
  //endregion

  //refion 获取用户权限列表
  @Override
  public List<UmsPermission> getPermissionList(Long adminId) {
    return adminRoleRelationDao.getPermissionList(adminId);
  }
  //endregion

  //region 获取用户信息
  @Override
  public UserDetails loadUserByUsername(String username) {
    UmsAdmin admin = getUmsAdminByUsername(username);
    if (admin != null) {
      List<UmsPermission> permissionList = getPermissionList(admin.getId());
      return new AdminUserDetailsVO(admin, permissionList);
    }
    throw new UsernameNotFoundException("用户名或密码错误");
  }
  //endregion

  //region 登陆
  @Override
  public String login(String username, String password) {
    String token = null;

    //密码需要客户端加密后传递
    try {
      UserDetails userDetails = loadUserByUsername(username);
      if (!passwordEncoder.matches(password, userDetails.getPassword()))
        throw new BadCredentialsException("密码不正确");
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      token = jwtTokenUtil.generateToken(userDetails);
      insertLoginLog(username);
      return token;
    } catch (AuthenticationException e) {
      log.warn("登录异常:{}", e.getMessage());
    }
    return null;
  }
  //endregion

  //region 刷新token
  @Override
  public String refreshToken(String oldToken) {
    return jwtTokenUtil.refreshHeadToken(oldToken);
  }
//endregion

  /**
   * 添加登录记录
   * @param username 用户名
   */
  private void insertLoginLog(String username) {
    UmsAdmin admin = getUmsAdminByUsername(username);
    UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
    loginLog.setAdminId(admin.getId());
    loginLog.setCreateTime(new Date());
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    loginLog.setIp(request.getRemoteAddr());
    loginLogMapper.insert(loginLog);
  }
}
