package com.guoyw.mall.member.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: mall-demo
 * @description: rediskey 配置
 * @author: guoyw
 * @create: 2020-04-12 00:05
 **/

@Data
@Configuration
@ConfigurationProperties(prefix = "redis.key")
public class RedisKeyPrefixConfig {

  private RedisKeyPrefixConfig.Prefix prefix;

  private RedisKeyPrefixConfig.Expire expire;

  @Data
  public static class Prefix{
    private String otpCode;

  }

  @Data
  public static class Expire{

    private Long otpCode;

  }


}
