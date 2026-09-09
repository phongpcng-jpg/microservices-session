package io.github.nguyenquephong13062003.customer_service.common.exception;

/**
 * Exception thrown when a customer is not found in the system.
 * This exception is typically used to indicate that a requested customer resource does not exist.
 */
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomerNotFoundException with a message indicating the customer ID that was not found.
     *
     * @param id the ID of the customer that was not found
     */
    public CustomerNotFoundException(Long id) {
        super("Customer not found with id: " + id);
    }
    
}
