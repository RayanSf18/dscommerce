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
                entity.getImgUrl(),
                entity.getPrice()
        );
    }

    public Product toProduct(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Product(
                null,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.imgUrl()
        );
    }
}
