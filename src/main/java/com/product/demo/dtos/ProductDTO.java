package com.product.demo.dtos;

import com.product.demo.entities.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
public class ProductDTO {

    private String guid;

    private String name;

    private String barcode;

    private String description;

    private double price;

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
            .guid(product.getGuid())
            .name(product.getName())
            .barcode(product.getBarcode())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();
    }
}
