package io.github.nguyenquephong13062003.order_service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.nguyenquephong13062003.order_service.entity.OrderStatus;

/**
 * Represents a response containing order information.
 *
 * @param id          the ID of the order
 * @param customerId  the ID of the customer who placed the order
 * @param orderDate   the date and time when the order was placed
 * @param totalAmount the total amount of the order
 * @param status      the status of the order
 */
public record OrderResponse(

        /**
         * The ID of the order.
         */
        Long id,

        /**
         * The ID of the customer who placed the order.
         */
        Long customerId,

        /**
         * The date and time when the order was placed.
         */
        LocalDateTime orderDate,

        /**
         * The total amount of the order.
         */
        BigDecimal totalAmount,

        /**
         * The status of the order.
         */
        OrderStatus status

) {
    
}

