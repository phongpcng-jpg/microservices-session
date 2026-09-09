package io.github.nguyenquephong13062003.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.nguyenquephong13062003.order_service.entity.Order;

/**
 * Repository interface for managing Order entities.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds all orders associated with a specific customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of orders for the specified customer
     */
    List<Order> findByCustomerId(Long customerId);
    
}
