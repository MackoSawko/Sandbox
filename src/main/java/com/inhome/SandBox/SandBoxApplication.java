package com.inhome.SandBox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource("classpath:knightRepository.properties")
@EnableScheduling
public class SandBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandBoxApplication.class, args);
	}
}
