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
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "tb_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @NotBlank
    private String barcode;

	@NotBlank
	private String name;

    @NotNull
    private Integer quantity;

	@NotBlank
    @Size(min = 10, max = 50)
	private String description;

	@NotNull
	private Double price;




}
