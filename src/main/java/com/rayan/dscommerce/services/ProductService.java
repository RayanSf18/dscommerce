package com.rayan.dscommerce.services;

import com.rayan.dscommerce.dtos.ProductDTO;
import com.rayan.dscommerce.entities.Product;
import com.rayan.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product result = this.productRepository.findById(id).get();
        return new ProductDTO(result);
    }

}
