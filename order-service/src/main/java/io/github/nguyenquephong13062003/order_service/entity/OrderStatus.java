package io.github.nguyenquephong13062003.order_service.entity;

/**
 * Enum representing the status of an order.
 */
public enum OrderStatus {

    /**
     * The order is pending and has not been processed yet.
     */
    PENDING,

    /**
     * The order has been confirmed and is being processed.
     */
    CONFIRMED,

    /**
     * The order has been cancelled and will not be processed.
     */
    CANCELLED,

    /**
     * The order has been completed and fulfilled.
     */
    COMPLETED
    
}
