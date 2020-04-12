package com.guoyw.mall.member.controller.Advice;

import com.guoyw.mall.common.api.CommonResult;
import com.guoyw.mall.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-12 12:04
 **/

@RestControllerAdvice
public class AdviceController {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = Exception.class)
  public CommonResult exceptionHandler(Exception ex){
    if(ex instanceof BusinessException){
      return CommonResult.failed(ex.getMessage());
    }else if(ex instanceof MethodArgumentNotValidException){
      MethodArgumentNotValidException validException = (MethodArgumentNotValidException)ex;
      return CommonResult.failed(validException.getBindingResult().getFieldError().getDefaultMessage());
    }

    return CommonResult.failed();
  }


}
