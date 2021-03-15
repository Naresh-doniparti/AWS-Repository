package com.microservices.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			Arrays.stream(applicationContext.getBeanDefinitionNames())
			.forEach(System.out::println);
		};
	}
}
