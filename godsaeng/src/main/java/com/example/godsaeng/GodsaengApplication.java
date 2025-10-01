package com.example.godsaeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = "com.godsaeng")
@EntityScan(basePackages = "com.godsaeng.member.entity")
@EnableJpaRepositories(basePackages = "com.godsaeng.member.repository")
public class GodsaengApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodsaengApplication.class, args);
	}

}
