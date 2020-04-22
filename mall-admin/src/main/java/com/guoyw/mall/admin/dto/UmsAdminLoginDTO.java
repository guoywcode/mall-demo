package com.guoyw.mall.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-16 21:06
 **/
@Getter
@Setter
@ApiModel(value = "UmsAdminLoginDTO",description = "后台用户登陆")
public class UmsAdminLoginDTO implements Serializable{

  @ApiModelProperty(value = "用户名",required = true)
  @NotEmpty(message = "用户名不能为空")
  @NotNull(message = "用户名不能为空")
  private String username;

  @ApiModelProperty(value = "密码",required = true)
  @NotEmpty(message = "密码不能为空")
  @NotNull(message = "密码不能为空")
  private String password;
}
