package com.product.demo.loader;

import org.springframework.boot.CommandLineRunner;

import com.product.demo.repository.ProductRepository;

public class personPopulation implements CommandLineRunner{

	private ProductRepository product;
	
	public personPopulation(ProductRepository product) {
		this.product = product;
	}
	
	@Override
	public void run(String... args) throws Exception {		
	
		
		
	}

}
