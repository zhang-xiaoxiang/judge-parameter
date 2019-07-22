package com.example.judgeparameter.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * RequestRequire:这个注解让aop来统一的帮我们进行非空的判断。
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface RequestRequire {

    /**
     * 请求当前接口所需要的参数,多个以小写的逗号隔开,可以使用中文,做了处理的哈,也可以有空格
     *
     * @return
     */
    String require() default "";


    /**
     * 传递参数的对象类型
     */
    Class<?> parameter() default Object.class;

}