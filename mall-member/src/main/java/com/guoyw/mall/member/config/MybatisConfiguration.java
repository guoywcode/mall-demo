package com.guoyw.mall.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement //开启事务
@MapperScan(basePackages = {"com.guoyw.mall.mbg.mapper","com.guoyw.mall.member.dao"})
public class MybatisConfiguration {
}
