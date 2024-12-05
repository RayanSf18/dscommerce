package com.rayan.dscommerce.repository;

import com.rayan.dscommerce.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
