package com.guoyw.mall.member.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-12 12:32
 **/

@ApiModel("会员注册-domain")
@Data
public class Register implements Serializable {

  @ApiModelProperty("手机号")
  @NotBlank(message = "电话好码不能为空")
  @Length(max = 11, min = 11, message = "电话必须为11位字符")
  @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$", message = "电话号码格式不正确")
  private String phone;

  @ApiModelProperty("手机验证码")
  @NotBlank(message = "验证码不能为空")
  @Length(max = 6, min = 6, message = "验证码必须为6位字符")
  private String otpCode;

  @ApiModelProperty("用户名")
  @NotBlank(message = "用户名不允许为空")
  @Length(max = 20, min = 4, message = "用户名长度必须为4-20位字符之间")
  private String username;

  @ApiModelProperty("用户名密码")
  @NotBlank(message = "密码不能为空")
  @Length(max = 20, min = 8, message = "密码长度必须在8-20字符之间")
  private String password;
}
