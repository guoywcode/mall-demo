package com.guoyw.mall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-15 20:19
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.guoyw.mall.mbg.mapper","com.guoyw.mall.admin.dao"})
public class MyBatisConfig {
}
