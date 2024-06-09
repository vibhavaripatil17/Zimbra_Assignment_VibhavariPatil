package com.cache_eviction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheEvictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheEvictionApplication.class, args);
		System.out.println("Application started successfully.");
	}

}
