package com.shop.projectshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjectShopApplication {

	public static ConfigurableApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext =SpringApplication.run(ProjectShopApplication.class, args);
		
	}

}
