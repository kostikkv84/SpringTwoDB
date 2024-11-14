package org.example.springdbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"org.example.springdbtest"})
@SpringBootApplication
public class SpringDBtestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDBtestApplication.class, args);
	}



}
