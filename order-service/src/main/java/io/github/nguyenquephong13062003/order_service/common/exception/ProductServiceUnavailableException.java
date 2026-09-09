package io.github.nguyenquephong13062003.order_service.common.exception;

/**
 * Exception thrown when Product Service cannot be discovered
 * or is currently unavailable.
 */
public class ProductServiceUnavailableException extends RuntimeException {

    /**
     * Creates a new ProductServiceUnavailableException.
     *
     * @param message exception message
     */
    public ProductServiceUnavailableException(String message) {
        super(message);
    }

    /**
     * Creates a new ProductServiceUnavailableException.
     *
     * @param message exception message
     * @param cause   underlying exception
     */
    public ProductServiceUnavailableException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}