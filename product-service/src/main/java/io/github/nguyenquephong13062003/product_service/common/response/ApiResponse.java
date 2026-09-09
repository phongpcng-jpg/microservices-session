package io.github.nguyenquephong13062003.product_service.common.response;

/**
 * Standard API response wrapper.
 *
 * @param <T> response data type
 */
public record ApiResponse<T>(

        /**
         * Indicates whether the API call was successful.
         */
        boolean success,

        /**
         * Response message providing additional information about the API call.
         */
        String message,

        /**
         * Response data returned by the API call.
         */
        T data
        
) {

    /**
     * Creates a successful response.
     *
     * @param data response data
     * @param message response message
     * @param <T> response data type
     * @return successful API response
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data);
    }

    /**
     * Creates a failed response.
     *
     * @param message error message
     * @param <T> response data type
     * @return failed API response
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }

}
