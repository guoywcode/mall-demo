package com.guoyw.mall.member.controller;

import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.mbg.model.UmsMember;
import com.guoyw.mall.member.dto.RegisterDTO;
import com.guoyw.mall.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-11 23:28
 **/

@RestController
@RequestMapping("/sso")
@Api(tags = "MemberController", description = "会员登录注册管理")
public class MemberController extends BaseController {

  @Autowired
  private MemberService memberService;

  @PostMapping("/getOtpCode")
  @ApiOperation("获取手机验证码")
  public CommonResult getOtpCode(@RequestParam String telPhone) {
    String otpCode = memberService.getOtpCode(telPhone);
    return CommonResult.success(otpCode);
  }


  @PostMapping("/register")
  @ApiOperation("会员注册")
  public CommonResult register(@Valid @RequestBody RegisterDTO registerDTO) {
    int number = memberService.register(registerDTO);
    if (number > 0)
      return CommonResult.success(null);
    return CommonResult.failed();
  }

  @PostMapping("/login")
  @ApiOperation("登陆")
  public CommonResult getOtpCode(@RequestParam String username,@RequestParam String password) {
    UmsMember umsMember = memberService.login(username,password);

    if(umsMember != null){
      getHttpSession().setAttribute("member",umsMember);
      getHttpSession().getAttribute("member");
      return CommonResult.success("登陆成功");
    }

    return CommonResult.failed();
  }

}
