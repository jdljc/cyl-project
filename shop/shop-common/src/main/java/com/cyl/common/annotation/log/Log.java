package com.cyl.common.annotation.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cyl.common.enums.LogLevel;

/**
*@author 25280
*@date 2019年5月6日
*@time 下午7:21:01
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

	LogLevel level() default LogLevel.INFO;
}
