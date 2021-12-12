package com.product.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByGuid(String guid);


}
