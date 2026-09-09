package io.github.nguyenquephong13062003.order_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.nguyenquephong13062003.order_service.common.response.ApiResponse;
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
     * @param request the order creation request
     * @return the created order response
     */
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @Valid @RequestBody OrderRequest request
    ) {
        OrderResponse response = orderService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Order created successfully"));
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the order ID
     * @return the order response
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(orderService.findById(id), "Order retrieved successfully")
        );
    }

    /**
     * Retrieves all orders.
     *
     * @return a list of order responses
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> findAll() {

        return ResponseEntity.ok(
                ApiResponse.success(orderService.findAll(), "Orders retrieved successfully")
        );
    }

    /**
     * Retrieves orders by customer ID.
     *
     * @param customerId the customer ID
     * @return a list of order responses for the specified customer
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> findByCustomerId(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                ApiResponse.success(orderService.findByCustomerId(customerId), "Orders retrieved successfully")
        );
    }

    /**
     * Updates an existing order.
     *
     * @param id      the order ID
     * @param request the order update request
     * @return the updated order response
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(orderService.update(id, request), "Order updated successfully")
        );
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the order ID
     * @return a response indicating the deletion status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        orderService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.success(null, "Order deleted successfully")
        );
    }

    /**
     * Retrieves a product from Product Service through Eureka service discovery.
     *
     * <p>This endpoint is used to demonstrate dynamic service discovery
     * without hard-coding the Product Service host and port.</p>
     *
     * @param productId product ID
     * @return response received from Product Service
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<String>> findProductById(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        orderService.findProductById(productId),
                        "Product retrieved through Eureka successfully"
                )
        );
    }
    
}
