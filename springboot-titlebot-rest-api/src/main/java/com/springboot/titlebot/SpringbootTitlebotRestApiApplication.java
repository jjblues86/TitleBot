package com.springboot.titlebot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringbootTitlebotRestApiApplication {

	/** WebClient is a non-blocking HTTP client that can be used to make HTTP requests and retrieve response bodies.
     * It is a thin wrapper around the Java WebClient library. */
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	/** ModelMapper is used to map objects from one type to another. */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTitlebotRestApiApplication.class, args);
	}
}
