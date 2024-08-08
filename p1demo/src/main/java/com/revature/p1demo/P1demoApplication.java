package com.revature.p1demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan("com.revature.model") //This tells Spring Boot to look in the model package for DB entities
@ComponentScan("com.revature") //This tells Spring Boot to look for beans in the entire com.rev package
@EnableJpaRepositories("com.revature.DAO") //This allows us to use JpaRepository in our DAOS
public class P1demoApplication {

	public static void main(String[] args) {
		SpringApplication.run(P1demoApplication.class, args);
	}

}
