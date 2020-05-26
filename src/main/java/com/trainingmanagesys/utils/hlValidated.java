package com.trainingmanagesys.utils;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated
public @interface hlValidated {

    Class<?>[] value() default {};

    String action() default "";
}
