package com.guoyw.mall.member.util;

import com.guoyw.mall.common.exception.BusinessException;
import com.guoyw.mall.mbg.model.UmsMember;
import com.guoyw.mall.member.config.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-15 20:25
 **/

public class JwtKit {

  @Autowired
  private JwtProperties jwtProperties;

  // 创建JWTToken
  public String generateJwtToken(UmsMember member){
    Map<String,Object> claims = new HashMap<>();

    claims.put("sub",member.getUsername());
    claims.put("createdate",new Date());
    claims.put("id",member.getId());
    claims.put("memberLevelId",member.getMemberLevelId());
    return Jwts.builder()
        //.addClaims(claims) 上课异常原因,改成下面的方式
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()*1000))
        .signWith(SignatureAlgorithm.HS256,jwtProperties.getSecret())
        .compact();

   /* return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getExpiration()))
        .signWith(SignatureAlgorithm.HS256,jwtProperties.getSecret())
        .compact();*/
  }

  // j解析jwtToken令牌
  public Claims parseJwtToken(String jwtToken){
    Claims claims = null;
    try {
      claims = Jwts.parser()
          .setSigningKey(jwtProperties.getSecret())
          .parseClaimsJws(jwtToken)
          .getBody();
    }catch (ExpiredJwtException e) {
      throw new BusinessException("JWT验证失败:token已经过期");
    } catch (UnsupportedJwtException e) {
      throw new BusinessException("JWT验证失败:token格式不支持");
    } catch (MalformedJwtException e) {
      throw new BusinessException("JWT验证失败:无效的token");
    } catch (SignatureException e) {
      throw new BusinessException("JWT验证失败:无效的token");
    } catch (IllegalArgumentException e) {
      throw new BusinessException("JWT验证失败:无效的token");
    }
    return claims;
  }

}
