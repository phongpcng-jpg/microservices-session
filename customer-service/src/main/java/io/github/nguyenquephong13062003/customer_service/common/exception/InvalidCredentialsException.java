package io.github.nguyenquephong13062003.customer_service.common.exception;

/**
 * Exception thrown when invalid credentials are provided during authentication.
 * This exception is typically used to indicate that the provided email or password is incorrect.
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Constructs a new InvalidCredentialsException with a default message indicating that the email or password is incorrect.
     */
    public InvalidCredentialsException() {
        super("email or password incorrect");
    }
    
}
