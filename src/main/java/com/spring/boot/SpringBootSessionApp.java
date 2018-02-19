package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.spring.boot")
public class SpringBootSessionApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSessionApp.class, args);

		/* DEBUGGER


		System.out.println("Tworzenie Nowego CID: " + cId);

        System.out.println("CID Aktualnej Konwersacji: " + cId);

		System.out.println("Dodanie: " + attributeName + " = " +(String)(attributeValue) + " o CID = " + cId);
		 */

	}
}
