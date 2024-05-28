package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication
public class TimeBasedSqlInjectionApplication {


	public static void main(String[] args) {
		SpringApplication.run(TimeBasedSqlInjectionApplication.class, args);
	}

}
