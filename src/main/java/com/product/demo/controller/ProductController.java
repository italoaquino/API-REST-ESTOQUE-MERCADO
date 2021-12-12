package com.product.demo.controller;

import com.product.demo.dtos.ProductDTO;
import com.product.demo.entities.Product;
import com.product.demo.service.productService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
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
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController

@RequestMapping("/v1/person")
public class ProductController {

	private productService service;

	public ProductController(productService service) {
		this.service = service;
	}

    @CrossOrigin(origins = "http:/localhost:4200")
    @GetMapping(value = "")
	public ResponseEntity<List<ProductDTO>> findAll(){
		 List<ProductDTO> list = StreamSupport.stream(this.service.findAll().spliterator(), false)
				 .map(product -> ProductDTO.toDTO(product)).collect(Collectors.toList());
		 return ResponseEntity.ok().body(list);
	}

    @CrossOrigin(origins = "http:/localhost:4200")
    @GetMapping(value = "{guid}")
	public ResponseEntity<ProductDTO>findById(@PathVariable String guid){
		Product product = this.service.findByGuid(guid);
        ProductDTO productDTO = ProductDTO.toDTO(product);
		return ResponseEntity.ok().body(productDTO);
	}

    @CrossOrigin(origins = "http:/localhost:4200")
	@PostMapping(value = "")
	public ResponseEntity<Void> addNewProduct(@RequestBody @Valid ProductDTO productDTO){

        Product product = this.service.save(productDTO.getName(),productDTO.getBarcode(), productDTO.getDescription(), productDTO.getPrice());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

    @CrossOrigin(origins = "http:/localhost:4200")
    @PutMapping(value ="{guid}")
	public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductDTO productdto, String guid){
        Product p = this.service.findByGuid(guid);
        p = this.service.update(
            productdto.getGuid(), productdto.getName(), productdto.getBarcode(), productdto.getDescription(), productdto.getPrice()
        );
        return ResponseEntity.noContent().build();
	}

    @CrossOrigin(origins = "http:/localhost:4200")
    @DeleteMapping(value ="{guid}")
    public ResponseEntity<Void> Delete(String guid){
        if(Strings.isEmpty(guid)){
            throw new IllegalArgumentException("guid nao pode ser nulo");
        }
        this.service.delete(guid);
        return ResponseEntity.noContent().build();
    }

}
