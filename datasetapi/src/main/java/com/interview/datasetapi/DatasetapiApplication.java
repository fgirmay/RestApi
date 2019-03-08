package com.interview.datasetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DatasetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasetapiApplication.class, args);
	}

}
