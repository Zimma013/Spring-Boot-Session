package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.spring.boot")
public class SpringBootSessionApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSessionApp.class, args);

	}
}
