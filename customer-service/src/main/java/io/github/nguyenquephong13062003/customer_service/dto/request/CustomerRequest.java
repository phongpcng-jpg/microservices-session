package io.github.nguyenquephong13062003.customer_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for customer requests.
 * This record encapsulates the data required to create or update a customer.
 */
public record CustomerRequest(

        /**
         * The full name of the customer.
         * Must not be blank and can have a maximum length of 150 characters.
         */
        @NotBlank
        @Size(max = 150)
        String fullName,

        /**
         * The email address of the customer.
         * Must not be blank, must be a valid email format, and can have a maximum length of 255 characters.
         */
        @NotBlank
        @Email
        @Size(max = 255)
        String email,

        /**
         * The password for the customer's account.
         * Must not be blank and must be between 6 and 255 characters in length.
         */
        @NotBlank
        @Size(min = 6, max = 255)
        String password,

        /**
         * The address of the customer.
         * Can have a maximum length of 500 characters.
         */
        @Size(max = 500)
        String address

) {
    
}
