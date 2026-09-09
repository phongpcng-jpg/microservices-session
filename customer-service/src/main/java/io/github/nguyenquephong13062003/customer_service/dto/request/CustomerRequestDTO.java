package io.github.nguyenquephong13062003.customer_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for customer requests.
 * This record encapsulates the data required to create or update a customer.
 */
public record CustomerRequestDTO(

        /**
         * The full name of the customer.
         * Must not be blank and can have a maximum length of 150 characters.
         */
        @NotBlank(message = "Full name is required")
        @Size(max = 150, message = "Full name must not exceed 150 characters")
        String fullName,

        /**
         * The email address of the customer.
         * Must not be blank, must be a valid email format, and can have a maximum length of 255 characters.
         */
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        String email,

        /**
         * The password for the customer's account.
         * Must not be blank and must be between 6 and 255 characters in length.
         */
        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 255,
                message = "Password must be between 6 and 255 characters")
        String password

) {
    
}
