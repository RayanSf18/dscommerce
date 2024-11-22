package com.rayan.dscommerce.model.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toProductDTO(Product entity) {
        if (entity == null) {
            return null;
        }

        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImgUrl()
        );
    }
}
