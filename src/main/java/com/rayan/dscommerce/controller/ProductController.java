package com.rayan.dscommerce.controller;

import com.rayan.dscommerce.model.product.ProductDTO;
import com.rayan.dscommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return this.productService.findAll(pageable);
    }

}
