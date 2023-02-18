package com.sw.apiperson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ApiPersonApplication {


	String home() {
		return "Hello World!";
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiPersonApplication.class, args);
	}

}
