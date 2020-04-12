package com.guoyw.mall.member;

import com.guoyw.mall.member.config.properties.RedisKeyPrefixConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MallMemberApplication.class)
@SpringBootTest
public class MallMemberApplicationTests {

  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private RedisKeyPrefixConfig redisKeyPrefixConfig;

  @Test
 public void contextLoads() {
    redisTemplate.opsForValue().set(redisKeyPrefixConfig.getPrefix().getOtpCode()
        ,"123456",redisKeyPrefixConfig.getExpire().getOtpCode(), TimeUnit.SECONDS);

    System.out.println("001  : "+redisTemplate.hasKey(redisKeyPrefixConfig.getPrefix().getOtpCode()));
    System.out.println("002  : "+redisTemplate.opsForValue().get(redisKeyPrefixConfig.getPrefix().getOtpCode()));

  }

}
