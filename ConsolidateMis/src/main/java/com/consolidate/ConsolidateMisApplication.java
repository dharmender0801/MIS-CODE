package com.consolidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConsolidateMisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsolidateMisApplication.class, args);
	}

}
