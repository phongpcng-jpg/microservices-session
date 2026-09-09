package io.github.nguyenquephong13062003.product_service.dto.response;

import java.math.BigDecimal;

/**
 * Represents a response containing product details.
 *
 * @param id            the unique identifier of the product
 * @param name          the name of the product
 * @param price         the price of the product
 * @param stockQuantity the quantity of the product in stock
 * @param description   an optional description of the product
 */
public record ProductResponse(

        /**
         * The unique identifier of the product.
         */
        Long id,

        /**
         * The name of the product.
         */
        String name,

        /**
         * The price of the product.
         */
        BigDecimal price,

        /**
         * The quantity of the product in stock.
         */
        Integer stockQuantity,

        /**
         * An optional description of the product.
         */
        String description

) {

}
