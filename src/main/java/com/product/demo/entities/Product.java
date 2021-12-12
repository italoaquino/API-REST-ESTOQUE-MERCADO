package com.product.demo.entities;

import lombok.Data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String guid;

	@NotBlank
	private String name;

	@NotBlank
	private String barcode;

	@NotBlank
	private String description;

	@NotNull
	private Double price;




}
