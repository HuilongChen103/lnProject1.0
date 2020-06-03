package com.trainingmanagesys.utils;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

/**
 * 测试自定义接口，没有任何用
 */

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated
public @interface hlValidated {

    Class<?>[] value() default {};

    String action() default "";
}
