package com.crudfin3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrudFin3Application {

	public static void main(String[] args) {
		SpringApplication.run(CrudFin3Application.class, args);
	}

}
