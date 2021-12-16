package com.product.demo.dtos;

import com.product.demo.entities.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Builder
@Getter
@Setter
public class ProductDTO {

    @NotBlank
    private String name;

    private String barcode;

    @NotBlank
    @Size(min = 5, max = 50)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
            .barcode(product.getBarcode())
            .name(product.getName())
            .barcode(product.getBarcode())
            .description(product.getDescription())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .build();
    }
}
