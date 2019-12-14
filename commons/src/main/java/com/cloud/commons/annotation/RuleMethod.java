package com.cloud.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by The_Answer on 2016/6/10.
 * 规则方法注解
 */
@Target(ElementType.METHOD)        //注解用于什么地方(method用于描述方法)
@Retention(RetentionPolicy.RUNTIME)//什么时候用该注解(runtime始终不会丢弃，运行期也保留该注解，可以使用反射机制读取该注解的信息)
public @interface RuleMethod {
    int ruletype()default 0;
    String ruledesc();
}
