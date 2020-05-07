package com.guoyw.mall.admin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * controller层日志
 * @author: guoyw
 * create: 2020-05-07 14:59
 **/

@Data
@Accessors(chain = true)
public class WebLogVo implements Serializable{
  
  /**
   * 操作描述
   */
  private String description;
  
  /**
   * 操作用户
   */
  private String username;
  
  /**
   * 操作时间
   */
  private Long startTime;
  
  /**
   * 消耗时间
   */
  private Integer spendTime;
  
  /**
   * 根路径
   */
  private String basePath;
  
  /**
   * URI
   */
  private String uri;
  
  /**
   * URL
   */
  private String url;
  
  /**
   * 请求类型
   */
  private String method;
  
  /**
   * IP地址
   */
  private String ip;
  
  private Object parameter;
  
  private Object result;
  
}
