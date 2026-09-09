package io.github.nguyenquephong13062003.order_service.service;

import java.util.List;

import io.github.nguyenquephong13062003.order_service.dto.request.OrderRequest;
import io.github.nguyenquephong13062003.order_service.dto.response.OrderResponse;

/**
 * Service interface for order operations.
 */
public interface IOrderService {

    /**
     * Creates a new order.
     *
     * @param request order creation request
     * @return created order
     */
    OrderResponse create(OrderRequest request);

    /**
     * Finds an order by ID.
     *
     * @param id order ID
     * @return order information
     */
    OrderResponse findById(Long id);

    /**
     * Returns all orders.
     *
     * @return list of orders
     */
    List<OrderResponse> findAll();

    /**
     * Finds all orders belonging to a customer.
     *
     * @param customerId customer ID
     * @return list of customer orders
     */
    List<OrderResponse> findByCustomerId(Long customerId);

    /**
     * Updates an existing order.
     *
     * @param id order ID
     * @param request order update request
     * @return updated order
     */
    OrderResponse update(Long id, OrderRequest request);

    /**
     * Deletes an order.
     *
     * @param id order ID
     */
    void delete(Long id);

    /**
     * Retrieves a product from Product Service through Eureka discovery.
     *
     * @param productId product ID
     * @return Product Service response
     */
    String findProductById(Long productId);
    
}
