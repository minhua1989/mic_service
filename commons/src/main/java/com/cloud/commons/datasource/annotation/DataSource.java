package com.cloud.commons.datasource.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * @author huamin
 * @date 2018/04/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
