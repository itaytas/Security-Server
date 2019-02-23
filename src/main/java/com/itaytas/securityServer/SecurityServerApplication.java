package com.itaytas.securityServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
//@EnableJpaRepositories("com.itaytas.securityServer.dal")
public class SecurityServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServerApplication.class, args);
	}

}

