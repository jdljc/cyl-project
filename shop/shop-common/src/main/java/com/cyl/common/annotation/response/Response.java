package com.cyl.common.annotation.response;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*@author 25280
*@date 2019年5月5日
*@time 下午5:52:22
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Response {

	Msg msg() default @Msg;
	Code code() default @Code;
}
