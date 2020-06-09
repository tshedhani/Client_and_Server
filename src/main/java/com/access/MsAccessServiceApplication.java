package com.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.access")
public class MsAccessServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(MsAccessServiceApplication.class, args);
	}

}
