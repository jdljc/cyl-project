package com.cyl.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy=true)
public class ShopOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopOrderApplication.class, args);
	}

}
