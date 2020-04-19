package com.guoyw.mall.admin.interceptor;

import cn.hutool.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-19 19:57
 **/


public class Interceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    StringBuilder sb = new StringBuilder(1000);

    //获取请求参数
    Enumeration em = request.getParameterNames();
    JSONObject data = new JSONObject();
    while (em.hasMoreElements()) {
      String name = (String) em.nextElement();
      String value = request.getParameter(name);
      data.put(name,value);
    }
    sb .append("-------------------------------------------------------------\n");
    HandlerMethod h = (HandlerMethod) handler;
    sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
    sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
    sb.append("Params    : ").append(data).append("\n");
    sb.append("URI       : ").append(request.getRequestURI()).append("\n");
    sb.append("URL       : ").append(request.getRequestURL()).append("\n");
    sb .append("-------------------------------------------------------------\n");
    System.out.println(sb.toString());

    return  true;
  }
}
