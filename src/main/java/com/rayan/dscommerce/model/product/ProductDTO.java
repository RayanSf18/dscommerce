package com.rayan.dscommerce.model.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(

        Long id,

        @Size(min = 3, max = 80, message = "Name must be 3 to 80 characters long")
        @NotBlank(message = "Mandatory field")
        String name,

        @NotBlank
        @Size(min = 10, message = "Description must be at least 10 characters long")
        String description,

        String imgUrl,

        @Positive(message = "The price must be a positive value")
        Double price
) {}
