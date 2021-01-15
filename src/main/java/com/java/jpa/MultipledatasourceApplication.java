package com.java.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.java.jpa.repository", "com.java.jpa.controller","com.java.jpa.service"})
public class MultipledatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipledatasourceApplication.class, args);
	}

}
