package com.guoyw.mall.member.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-15 20:26
 **/

@Data
@ConfigurationProperties(prefix = "jwt")
@Configuration
public class JwtProperties {

  // JWT存储的请求头
  private String tokenheader;

  // JWT加密使用的密钥
  private String secret;

  // JWT的超时访问时间（60*60*24）
  private Long expiration;

  // JWT负载均衡的拿到的头
  private String tikenHead;

}
