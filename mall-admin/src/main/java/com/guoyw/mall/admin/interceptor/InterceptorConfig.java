package com.guoyw.mall.admin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-19 19:55
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //注册Interceptor拦截器
    InterceptorRegistration registration = registry.addInterceptor(new Interceptor());
    //拦截div接口。（/**表示拦截所有请求）
    registration.addPathPatterns(
        "/**"
    );
    //添加不拦截路径
    registration.excludePathPatterns(
        "/**/*.html",            //html静态资源
        "/**/*.js",              //js静态资源
        "/**/*.css",             //css静态资源
        "/**/*.woff",
        "/**/*.ttf",
        "/swagger-ui.html",
        "/swagger-resources/**",
        "/swagger/**",
        "/**/v2/api-docs"
    );
  }

}
