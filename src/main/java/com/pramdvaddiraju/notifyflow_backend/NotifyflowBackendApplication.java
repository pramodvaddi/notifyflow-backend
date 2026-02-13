package com.pramdvaddiraju.notifyflow_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotifyflowBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyflowBackendApplication.class, args);
	}

}
