package com.sutanghome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:system.properties")
public class SuTangHomeApp {
	public static void main(String[] args) {
		SpringApplication.run(SuTangHomeApp.class, args);
		System.out.println("Starting World!");
	}
}