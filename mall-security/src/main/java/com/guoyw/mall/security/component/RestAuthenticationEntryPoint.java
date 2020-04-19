package com.guoyw.mall.security.component;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.guoyw.mall.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 自定义返回结果：未登录或登录过期
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
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
//      HandlerMethod h = (HandlerMethod) handler;
//      sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
//      sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
      sb.append("Params    : ").append(data).append("\n");
      sb.append("URI       : ").append(request.getRequestURI()).append("\n");
      sb.append("URL       : ").append(request.getRequestURL()).append("\n");
      sb .append("-------------------------------------------------------------\n");
      System.out.println(sb.toString());


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
