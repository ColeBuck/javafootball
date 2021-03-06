package com.cole.javafootball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaFootballApplication {

	public static void main(String[] args) {
		PlayerGeneration.loadNameData();
		SpringApplication.run(JavaFootballApplication.class, args);
	}

}
