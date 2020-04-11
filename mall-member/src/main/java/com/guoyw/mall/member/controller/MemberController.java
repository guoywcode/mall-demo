package com.guoyw.mall.member.controller;

import com.guoyw.mall.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-11 23:28
 **/

@RestController
@RequestMapping("/sso")
@Api(tags ="MemberController", description = "会员登录注册管理")
public class MemberController {

  @Autowired
  private MemberService memberService;

  @GetMapping("/getOtpCode")
  public String getOtpCode(@RequestParam String telPhone) {
    return memberService.getOtpCode(telPhone);
  }
}
