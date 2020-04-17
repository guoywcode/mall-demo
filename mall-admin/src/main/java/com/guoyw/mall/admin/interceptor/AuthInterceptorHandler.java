package com.guoyw.mall.admin.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guoyw.mall.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-12 21:53
 **/
@Slf4j
public class AuthInterceptorHandler implements HandlerInterceptor {


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("进入前置拦截器");
    log.info(request.getRequestURI());

    return true;
  }

  protected void print(HttpServletResponse response, String message) throws Exception {
    /**
     * 设置响应头
     */
    response.setHeader("Content-Type", "application/json");
    response.setCharacterEncoding("UTF-8");
    String result = new ObjectMapper().writeValueAsString(CommonResult.forbidden(message));
    response.getWriter().print(result);

  }
}
