package com.product.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.product.demo.entities.Product;
import com.product.demo.repository.ProductRepository;
import com.product.demo.service.exception.ObjectNotFound;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Service
public class productService {

	private ProductRepository repository;

	public productService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> findAll() {
		return this.repository.findAll();
	}

	public Product findByBarcode(String barcode) {
		return repository.findByBarcode(barcode).orElseThrow(() -> new ObjectNotFound("Objeto nao encontrado"));
	}


	public Product save(String name, String description, Double price, Integer quantity) {

		Product product = new Product();
		product.setBarcode(UUID.randomUUID().toString());
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
        product.setQuantity(quantity);
		return repository.save(product);
	}


	public void delete(String barcode) {

        Product product = repository.findByBarcode(barcode).orElseThrow( ()-> new IllegalArgumentException("Product nao encontrado"));
        this.repository.delete(product);
	}

	public Product update(String barcode, String name, String description, Double price, Integer quantity) {

		Product product = this.repository.findByBarcode(barcode).orElseThrow(()-> new ObjectNotFound("Product nao encontrado"));

		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
        product.setQuantity(quantity);

		return repository.save(product);
	}




}
