package com.rayan.dscommerce.service;

import com.rayan.dscommerce.model.product.Product;
import com.rayan.dscommerce.model.product.ProductDTO;
import com.rayan.dscommerce.model.product.ProductMapper;
import com.rayan.dscommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable).map(productMapper::toProductDTO);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = this.productMapper.toProduct(dto);
        return this.productMapper.toProductDTO(this.productRepository.save(product));
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = this.productRepository.findById(id).get();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setImgUrl(dto.imgUrl());
        return this.productMapper.toProductDTO(this.productRepository.save(product));
    }

}
