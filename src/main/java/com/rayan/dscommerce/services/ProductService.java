package com.rayan.dscommerce.services;

import com.rayan.dscommerce.dto.ProductDTO;
import com.rayan.dscommerce.entities.Product;
import com.rayan.dscommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO searchProductById(Long productId) {
        Optional<Product> result = this.productRepository.findById(productId);
        return new ProductDTO(result.get());
    }

}
