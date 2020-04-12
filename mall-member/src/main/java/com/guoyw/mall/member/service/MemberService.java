package com.guoyw.mall.member.service;

import com.guoyw.mall.mbg.model.UmsMember;
import com.guoyw.mall.member.dto.RegisterDTO;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-11 23:34
 **/

public interface MemberService {

  /***
   *
   * @Param telPhone
   * @return: java.lang.String
   * @Description:  获取手机验证码
   * @Author: guoyw
   * @Date: 2020/4/11 11:36 PM
  */
  public String getOtpCode(String telPhone);

  // 会员注册
  public int register(RegisterDTO registerDTO);

  // 会员登陆
  public UmsMember login(String username, String password);
}
