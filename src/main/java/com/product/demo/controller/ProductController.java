package com.product.demo.controller;

import com.product.demo.dtos.ProductDTO;
import com.product.demo.entities.Product;
import com.product.demo.service.productService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@CrossOrigin
@RequestMapping("/v1/products")
public class ProductController {

	private final productService service;

	public ProductController(productService service) {
		this.service = service;
	}

    @CrossOrigin
    @GetMapping(value = "")
	public ResponseEntity<List<ProductDTO>> findAll(){
		 List<ProductDTO> list = StreamSupport.stream(this.service.findAll().spliterator(), false)
				 .map(ProductDTO::toDTO).collect(Collectors.toList());
		 return ResponseEntity.ok().body(list);
	}

    @CrossOrigin(origins = "http:/localhost:4200")
    @GetMapping(value = "{barcode}")
	public ResponseEntity<ProductDTO>findByBarcode(@PathVariable String barcode){
		Product product = this.service.findByBarcode(barcode);
        ProductDTO productDTO = ProductDTO.toDTO(product);
		return ResponseEntity.ok().body(productDTO);
	}

    @CrossOrigin(origins = "http:/localhost:4200")
	@PostMapping(value = "")
	public ResponseEntity<Void> addNewProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result){

        if (result.hasErrors()) {
            throw new ValidationException("error: "+result);
        }

        Product product = this.service.save(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getQuantity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

    @CrossOrigin(origins = "http:/localhost:4200")
    @PutMapping(value ="{barcode}")
	public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductDTO productdto, @PathVariable String barcode, BindingResult result){
        if (result.hasErrors()) {
            throw new ValidationException("error: "+result);
        }

        Product p = this.service.findByBarcode(barcode);
        p = this.service.update(productdto.getName(), productdto.getBarcode(), productdto.getDescription(), productdto.getPrice(), productdto.getQuantity());
        return ResponseEntity.noContent().build();
	}

    @CrossOrigin(origins = "http:/localhost:4200")
    @DeleteMapping(value ="{barcode}")
    public ResponseEntity<Void> Delete(@PathVariable String barcode){
        if(Strings.isEmpty(barcode)){
            throw new IllegalArgumentException("guid nao pode ser nulo");
        }
        this.service.delete(barcode);
        return ResponseEntity.noContent().build();
    }

}
