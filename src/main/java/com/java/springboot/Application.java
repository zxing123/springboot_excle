package com.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.java.springboot.mapper")
public class Application {
	
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(Application.class, args);
	    }
}
