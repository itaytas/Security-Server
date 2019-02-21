package com.itaytas.securityServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SecurityServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServerApplication.class, args);
	}

}

