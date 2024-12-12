package com.rayan.dscommerce.services;

import com.rayan.dscommerce.dto.ProductDTO;
import com.rayan.dscommerce.entities.Product;
import com.rayan.dscommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product entity = new Product();
        mapperDtoToEntity(productDTO, entity);
        entity = this.productRepository.save(entity);
        return new ProductDTO(entity);

    }

    @Transactional(readOnly = true)
    public ProductDTO searchProductById(Long productId) {
        Optional<Product> result = this.productRepository.findById(productId);
        return new ProductDTO(result.get());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> searchProducts(Pageable pageable) {
        Page<Product> result = this.productRepository.findAll(pageable);
        return result.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product entity = this.productRepository.getReferenceById(productId);
        mapperDtoToEntity(productDTO, entity);
        entity = this.productRepository.save(entity);
        return new ProductDTO(entity);

    }

    @Transactional
    public void deleteProductById(Long productId) {
        this.productRepository.deleteById(productId);
    }

    private void mapperDtoToEntity(ProductDTO productDTO, Product entity) {
        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        entity.setImgUrl(productDTO.getImgUrl());
        entity.setDescription(productDTO.getDescription());
    }
}
