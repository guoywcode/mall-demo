package com.guoyw.mall.member.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class RedisHttpSessionConfiguration {
    /**
     * 引入分布式会话体系,会话内容存储在Redis当中,原理请阅读源码
     */

}
