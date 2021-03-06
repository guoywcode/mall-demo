package com.guoyw.mall.member.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.common.exception.BusinessException;
import com.guoyw.mall.member.config.JwtProperties;
import com.guoyw.mall.member.util.JwtKit;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
  @Autowired
  private JwtKit jwtKit;
  @Autowired
  private JwtProperties jwtProperties;

  public final static String GLOBAL_JWT_USER_INFO="jwttoken:usermember:info";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("进入前置拦截器");
    String message = null;
    String authorization = request.getHeader(jwtProperties.getTokenheader());
    log.info("authorization:"+authorization);
    //校验token

    if(!StringUtils.isEmpty(authorization)
        && authorization.startsWith(jwtProperties.getTokenheader())){
      String authToken = authorization.substring(jwtProperties.getTokenheader().length());
      //解析jwt-token
      Claims claims = null;
      try {
        claims = jwtKit.parseJwtToken(authToken);
        if(claims != null){
          request.setAttribute(GLOBAL_JWT_USER_INFO,claims);
          return true;
        }
      } catch (BusinessException e) {
        log.error(message = (e.getMessage()+":"+authToken));
      }
    }

   /* if (!ObjectUtils.isEmpty(request.getSession().getAttribute("member"))) {
      return true;
    }*/
    print(response, "您没有权限访问！请先登录.");
    return false;
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
