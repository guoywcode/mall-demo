package com.guoyw.mall.common.exception;

/**
 * @program: mall-demo
 * @description: 业务异常
 * @author: guoyw
 * @create: 2020-04-12 00:00
 **/

public class BusinessException extends RuntimeException {

  public BusinessException(){super();}

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }
}
