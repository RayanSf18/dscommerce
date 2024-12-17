package com.rayan.dscommerce.services;

import com.rayan.dscommerce.dto.ProductDTO;
import com.rayan.dscommerce.entities.Product;
import com.rayan.dscommerce.repository.ProductRepository;
import com.rayan.dscommerce.services.exceptions.DatabaseException;
import com.rayan.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        Product result = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ProductDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> searchProducts(Pageable pageable) {
        Page<Product> result = this.productRepository.findAll(pageable);
        return result.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        try {
            Product entity = this.productRepository.getReferenceById(productId);
            mapperDtoToEntity(productDTO, entity);
            entity = this.productRepository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteProductById(Long id) {
        if (!this.productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            this.productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

    private void mapperDtoToEntity(ProductDTO productDTO, Product entity) {
        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        entity.setImgUrl(productDTO.getImgUrl());
        entity.setDescription(productDTO.getDescription());
    }
}
