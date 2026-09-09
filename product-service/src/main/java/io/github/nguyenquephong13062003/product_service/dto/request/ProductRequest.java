package io.github.nguyenquephong13062003.product_service.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Represents a request to create or update a product.
 *
 * @param name          the name of the product, must not be blank and have a maximum length of 255 characters
 * @param price         the price of the product, must be a positive decimal value
 * @param stockQuantity the quantity of the product in stock, must be a non-negative integer
 * @param description   an optional description of the product, with a maximum length of 1000 characters
 */
public record ProductRequest(

        /**
         * The name of the product.
         */
        @NotBlank
        @Size(max = 255)
        String name,

        /**
         * The price of the product.
         */
        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal price,

        /**
         * The quantity of the product in stock.
         */
        @NotNull
        @Min(0)
        Integer stockQuantity,

        /**
         * An optional description of the product.
         */
        @Size(max = 1000)
        String description

) {
    
}

