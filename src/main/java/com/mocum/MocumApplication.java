package com.mocum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MocumApplication {

	public static void main(String[] args) {
		SpringApplication.run(MocumApplication.class, args);
	}

}
