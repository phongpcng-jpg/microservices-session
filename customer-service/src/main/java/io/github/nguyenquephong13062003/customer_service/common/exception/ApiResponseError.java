package io.github.nguyenquephong13062003.customer_service.common.exception;

import java.time.LocalDateTime;

/**
 * Represents an error response for API requests.
 * This record encapsulates the details of an error that occurred during the processing of a request.
 */
public record ApiResponseError(

        /**
         * The timestamp when the error occurred.
         */
        LocalDateTime timestamp,

        /**
         * The HTTP status code associated with the error.
         */
        int status,

        /**
         * A brief description of the error.
         */
        String error,

        /**
         * A detailed message providing more information about the error.
         */
        String message

) {
    
}
