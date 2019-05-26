package com.cyl.common.annotation.response;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.HttpStatus;

/**
*@author 25280
*@date 2019年5月5日
*@time 下午6:34:26
*/
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Code {

	HttpStatus suc() default HttpStatus.OK;
	
	HttpStatus err() default HttpStatus.BAD_REQUEST;
}
