package io.github.nguyenquephong13062003.customer_service.dto.response;

/**
 * Data Transfer Object (DTO) for customer responses.
 * This record encapsulates the data returned to the client after processing a customer request.
 */
public record CustomerResponseDTO(

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
        String email
        
) {

}
