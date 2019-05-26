package com.cyl.common.annotation.response;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*@author 25280
*@date 2019年5月5日
*@time 下午6:10:28
*/
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Msg {

	String suc() default "";
	
	String err() default "";
}
