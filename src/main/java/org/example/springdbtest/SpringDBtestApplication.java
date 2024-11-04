package org.example.springdbtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"org.example.springdbtest"})
//@EnableAutoConfiguration
public class SpringDBtestApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringDBtestApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringDBtestApplication.class, args);
	}

}
