package com.rayan.dscommerce.controllers;

import com.rayan.dscommerce.dto.ProductDTO;
import com.rayan.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping(value = "/{productId}")
    public ProductDTO searchProductById(@PathVariable(value = "productId") Long productId) {
        return this.productService.searchProductById(productId);
    }

    @GetMapping
    public Page<ProductDTO> searchProducts(Pageable pageable) {
        return this.productService.searchProducts(pageable);
    }
}
