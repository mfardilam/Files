package com.example.xxxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = CSVController.class)
public class XxxxApplication {

	public static void main(String[] args) {
		SpringApplication.run(XxxxApplication.class, args);
	}

}
