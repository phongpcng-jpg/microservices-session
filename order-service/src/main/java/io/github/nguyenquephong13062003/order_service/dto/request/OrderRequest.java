package io.github.nguyenquephong13062003.order_service.dto.request;

import io.github.nguyenquephong13062003.order_service.entity.OrderStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Represents a request to create an order.
 *
 * @param customerId  the ID of the customer placing the order
 * @param totalAmount the total amount of the order
 * @param status      the status of the order
 */
public record OrderRequest(

        /**
         * The ID of the customer placing the order.
         */
        @NotNull
        @Min(1)
        Long customerId,

        /**
         * The total amount of the order.
         */
        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal totalAmount,

        /**
         * The status of the order.
         */
        @NotNull
        OrderStatus status

) {
    
}
