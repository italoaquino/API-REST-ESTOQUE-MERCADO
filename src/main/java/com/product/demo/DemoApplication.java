package com.product.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Profile("local")
	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
	
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "PUT", "OPTIONS", "POST", "DELETE").maxAge(900).allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
	
			}
		
		};
	}
}
