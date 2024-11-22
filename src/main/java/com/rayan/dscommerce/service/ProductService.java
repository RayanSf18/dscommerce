package com.rayan.dscommerce.service;

import com.rayan.dscommerce.model.product.Product;
import com.rayan.dscommerce.model.product.ProductDTO;
import com.rayan.dscommerce.model.product.ProductMapper;
import com.rayan.dscommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product productDb = this.productRepository.findById(id).get();
        return this.productMapper.toProductDTO(productDb);
    }

}
