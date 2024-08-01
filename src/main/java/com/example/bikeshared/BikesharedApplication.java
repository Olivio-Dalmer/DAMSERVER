package com.example.bikeshared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BikesharedApplication {
	public static void main(String[] args) {
		SpringApplication.run(BikesharedApplication.class, args);
		System.out.println("SERVIDOR BIKE SHARED RODANDO");
	}
}

