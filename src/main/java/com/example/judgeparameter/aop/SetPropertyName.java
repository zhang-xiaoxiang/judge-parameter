package com.example.judgeparameter.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * SetPropertyName:这个注解的主要目的是为了非空检查返回属性的中文信息
 * @author zhangxiaoxiang
 * @date: 2019/05/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SetPropertyName {

    String value() default "";
}