package com.example.product_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProdutApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutApiApplication.class, args);
	}

	// Definir el bean de RestTemplate
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();  // Retorna una nueva instancia de RestTemplate
	}
}
