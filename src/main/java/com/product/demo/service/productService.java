package com.product.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.product.demo.entities.Product;
import com.product.demo.repository.ProductRepository;
import com.product.demo.service.exception.ObjectNotFound;

@Service
public class productService {

	private ProductRepository repository;

	public productService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> findAll() {
		return this.repository.findAll();
	}

	public Product findByGuid(String guid) {
		if(Strings.isEmpty(guid)) {
			throw new IllegalArgumentException("Guid nao pode ser nulo");
		}
		return repository.findByGuid(guid).orElseThrow(() -> new ObjectNotFound("Objeto nao encontrado"));
	}

	public void validation(String name, String barcode, String description, Double price) {
		if(Strings.isEmpty(name)) {
			throw new IllegalArgumentException("Name nao pode ser nullo");
		}
		if(Strings.isEmpty(description)) {
			throw new IllegalArgumentException("Description nao pode ser nullo");
		}
		if(Strings.isEmpty(barcode)) {
			throw new IllegalArgumentException("Barcode nao pode ser nullo");
		}
		if(price == null) {
			throw new IllegalArgumentException("price nao pode ser nullo");
		}
	}

	public Product save(String name, String barcode, String description, Double price) {

		this.validation(name, barcode, description, price);

		Product product = new Product();
		product.setGuid(UUID.randomUUID().toString());
		product.setName(name);
		product.setDescription(description);
		product.setBarcode(barcode);
		product.setPrice(price);
		return repository.save(product);
	}

	public void validationGuid(String guid) {
		if(Strings.isEmpty(guid)) {
			throw new IllegalArgumentException("guid nao pode ser nulo");
		}
	}

	public void delete(String guid) {
        if(Strings.isEmpty(guid)){
            throw new IllegalArgumentException("guid nao pode ser nulo");
        }
		this.validationGuid(guid);
        Product product = repository.findByGuid(guid).orElseThrow( ()-> new IllegalArgumentException("Product nao encontrado"));
        this.repository.delete(product);
	}

	public Product update(String guid, String name, String barcode, String description, Double price) {
		this.validationGuid(guid);

		Product product = this.repository.findByGuid(guid).orElseThrow(()-> new ObjectNotFound("Product nao encontrado"));

		product.setName(name);
		product.setDescription(description);
		product.setBarcode(barcode);
		product.setPrice(price);

		return repository.save(product);
	}




}
