package com.guoyw.mall.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @program: mall-demo
 * @description: 用户参数DTO
 * @author: guoyw
 * @create: 2020-04-14 23:40
 **/

@Getter
@Setter
@ApiModel(value = "UmsAdminParamDTO",description = "后台用户注册")
public class UmsAdminParamDTO implements Serializable {

  @ApiModelProperty(value = "用户名",required = true)
  @NotEmpty(message = "用户名不能为空")
  private String username;

  @ApiModelProperty(value = "密码",required = true)
  @NotEmpty(message = "密码不能为空")
  private String password;

  @ApiModelProperty(value = "用户头像")
  private  String icon;

  @ApiModelProperty(value = "邮箱")
  @Email(message = "邮箱格式错误")
  private String email;

  @ApiModelProperty(value = "用户昵称")
  private String nickName;

  @ApiModelProperty(value = "备注")
  private String note;
}
