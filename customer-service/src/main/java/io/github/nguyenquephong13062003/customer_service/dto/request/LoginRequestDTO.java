package io.github.nguyenquephong13062003.customer_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for login requests.
 * This record encapsulates the data required for user authentication.
 */
public record LoginRequestDTO(

        /**
         * The email address of the user attempting to log in.
         * Must not be blank and must be a valid email format.
         */
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        /**
         * The password for the user's account.
         * Must not be blank.
         */
        @NotBlank(message = "Password is required")
        String password
        
) {

}
