package io.github.nguyenquephong13062003.customer_service.dto.response;

/**
 * Data Transfer Object (DTO) for customer responses.
 * This record encapsulates the data returned after creating or retrieving a customer.
 */
public record CustomerResponse(

        /**
         * The unique identifier of the customer.
         */
        Long id,

        /**
         * The full name of the customer.
         */
        String fullName,

        /**
         * The email address of the customer.
         */
        String email,

        /**
         * The address of the customer.
         */
        String address

) {
    
}
