package io.github.nguyenquephong13062003.order_service.mapper;

import org.springframework.stereotype.Component;

import io.github.nguyenquephong13062003.order_service.dto.request.OrderRequest;
import io.github.nguyenquephong13062003.order_service.dto.response.OrderResponse;
import io.github.nguyenquephong13062003.order_service.entity.Order;

import java.time.LocalDateTime;

/**
 * Maps Order entities and DTOs.
 */
@Component
public class OrderMapper {

    /**
     * Converts an OrderRequest to an Order entity.
     *
     * @param request order request
     * @return order entity
     */
    public Order toEntity(OrderRequest request) {
        return Order.builder()
                .customerId(request.customerId())
                .orderDate(LocalDateTime.now())
                .totalAmount(request.totalAmount())
                .status(request.status())
                .build();
    }

    /**
     * Converts an Order entity to an OrderResponse.
     *
     * @param order order entity
     * @return order response
     */
    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus()
        );
    }

    /**
     * Updates an existing Order entity.
     *
     * @param order existing order
     * @param request update request
     */
    public void updateEntity(Order order, OrderRequest request) {
        order.setCustomerId(request.customerId());
        order.setTotalAmount(request.totalAmount());
        order.setStatus(request.status());
    }
    
}
