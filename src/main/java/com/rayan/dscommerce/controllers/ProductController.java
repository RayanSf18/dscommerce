package com.rayan.dscommerce.controllers;

import com.rayan.dscommerce.dto.ProductDTO;
import com.rayan.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO dto = this.productService.saveProduct(productDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDTO> searchProductById(@PathVariable(value = "productId") Long productId) {
        return ResponseEntity.ok().body(this.productService.searchProductById(productId));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> searchProducts(Pageable pageable) {
        return ResponseEntity.ok().body(this.productService.searchProducts(pageable));
    }
}
