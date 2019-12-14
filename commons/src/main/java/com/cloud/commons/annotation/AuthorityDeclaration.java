package com.cloud.commons.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解
 * id : 在当前类中按照顺序进行编号，规则四位数字，0001-9999
 * desc : 对该权限的文字说明
 * 
 * @author Fay
 * date:2016-5-20
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityDeclaration {
	String id()  default "";
	String interfaceName() default "";
	String interfaceDesc() default "";
	String reqParam() default "{}";
	String resBody() default "data:{}";
	String[] errMsgs() default "";
}
