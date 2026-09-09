package io.github.nguyenquephong13062003.order_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.nguyenquephong13062003.order_service.dto.request.OrderRequest;
import io.github.nguyenquephong13062003.order_service.dto.response.OrderResponse;
import io.github.nguyenquephong13062003.order_service.service.IOrderService;

import java.util.List;

/**
 * REST controller for order operations.
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    /**
     * Service for order operations.
     */
    private final IOrderService orderService;

    /**
     * Creates a new order.
     *
     * @param request order creation request
     * @return created order
     */
    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @Valid @RequestBody OrderRequest request) {

        OrderResponse response = orderService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Finds an order by ID.
     *
     * @param id order ID
     * @return order information
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.findById(id)
        );
    }

    /**
     * Returns all orders.
     *
     * @return list of orders
     */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {

        return ResponseEntity.ok(
                orderService.findAll()
        );
    }

    /**
     * Returns all orders belonging to a customer.
     *
     * @param customerId customer ID
     * @return list of customer orders
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> findByCustomerId(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                orderService.findByCustomerId(customerId)
        );
    }

    /**
     * Updates an existing order.
     *
     * @param id order ID
     * @param request order update request
     * @return updated order
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {

        return ResponseEntity.ok(
                orderService.update(id, request)
        );
    }

    /**
     * Deletes an order.
     *
     * @param id order ID
     * @return empty response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        orderService.delete(id);

        return ResponseEntity.noContent().build();
    }
    
}
