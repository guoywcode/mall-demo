package com.guoyw.mall.admin.controller;

import com.guoyw.mall.admin.dto.UmsAdminDTO;
import com.guoyw.mall.admin.dto.UmsAdminLoginDTO;
import com.guoyw.mall.admin.service.UmsAdminService;
import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.common.exception.BusinessException;
import com.guoyw.mall.mbg.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mall-demo
 * @description: 系统用户管理
 * @author: guoyw
 * @create: 2020-04-14 23:30
 **/

@RestController
@Api(tags = "UmsAdminController",description = "后台用户管理")
@RequestMapping("/api/admin")
public class UmsAdminController {

  @Value("${jwt.tokenHeader}")
  private String tokenHeader;
  @Value("${jwt.tokenHead}")
  private String tokenHead;

  @Autowired
  private UmsAdminService umsAdminService;

  @ApiOperation(value = "用户注册")
  @PostMapping(value = "/register")
  public CommonResult<UmsAdmin> register(@RequestBody @Valid UmsAdminDTO umsAdminDTO){
    UmsAdmin umsAdmin = umsAdminService.register(umsAdminDTO);
    if (umsAdmin == null) {
      CommonResult.failed();
    }
    return CommonResult.success(umsAdmin);
  }

  @ApiModelProperty(value = "登录")
  @PostMapping("/login")
  public CommonResult login(@RequestBody @Validated UmsAdminLoginDTO umsAdminLoginDTO){
    String token = umsAdminService.login(umsAdminLoginDTO.getUsername(),umsAdminLoginDTO.getPassword());
    if(token == null)
      return CommonResult.failed("用户名或密码错误");

    Map<String,String> tokenMap = new HashMap<>();
    tokenMap.put("token",token);
    tokenMap.put("tokenHead",tokenHead);
    return  CommonResult.success(tokenMap);
  }

  @ApiOperation(value = "刷新token")
  @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult refreshToken(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader);
    String refreshToken = umsAdminService.refreshToken(token);
    if (refreshToken == null) {
      return CommonResult.failed("token已经过期！");
    }
    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", refreshToken);
    tokenMap.put("tokenHead", tokenHead);
    return CommonResult.success(tokenMap);
  }

  @ApiOperation(value = "获取当前登录用户信息")
  @GetMapping(value = "/info")
  public CommonResult getAdminInfo(Principal principal) {
    String username = principal.getName();
    UmsAdmin umsAdmin = umsAdminService.getUmsAdminByUsername(username);
    Map<String, Object> data = new HashMap<>();
    data.put("username", umsAdmin.getUsername());
    data.put("roles", new String[]{"TEST"});
    data.put("icon", umsAdmin.getIcon());
    return CommonResult.success(data);
  }

  @ApiOperation(value = "登出功能")
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult logout() {
    return CommonResult.success(null);
  }

}
