package com.guoyw.mall.admin.validator;

import javax.validation.Payload;

/*
 * 用户验证状态是否在指定范围内的注解
 */
public @interface FlagValidator {

  String[] value() default {};

  String message() default "flag is not found";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
