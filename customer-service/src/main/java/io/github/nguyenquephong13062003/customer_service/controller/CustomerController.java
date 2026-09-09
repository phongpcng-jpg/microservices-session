package io.github.nguyenquephong13062003.customer_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.nguyenquephong13062003.customer_service.common.response.ApiResponse;
import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequest;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponse;
import io.github.nguyenquephong13062003.customer_service.service.ICustomerService;

import java.util.List;

/**
 * REST controller for managing customer-related operations.
 * This controller provides endpoints for creating, retrieving, updating, and deleting customer data.
 */
@RestController
@ConditionalOnProperty(
    name = "app.customer-api.enabled",
    havingValue = "true"
)
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    /**
     * Service for handling customer-related business logic.
     */
    private final ICustomerService customerService;

    /**
     * Creates a new customer based on the provided CustomerRequest DTO.
     *
     * @param request the CustomerRequest DTO containing customer data
     * @return a ResponseEntity containing the created CustomerResponse DTO and HTTP status
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> create(
            @Valid @RequestBody CustomerRequest request
    ) {

        CustomerResponse response = customerService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Customer created successfully"));

    }

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer
     * @return a ResponseEntity containing the CustomerResponse DTO and HTTP status
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> findById(
            @PathVariable Long id
    ) {
        CustomerResponse response = customerService.findById(id);

        return ResponseEntity.ok(
                ApiResponse.success(response, "Customer retrieved successfully")
        );
    }

    /**
     * Retrieves all customers.
     *
     * @return a ResponseEntity containing a list of CustomerResponse DTOs and HTTP status
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> findAll() {
        List<CustomerResponse> response = customerService.findAll();

        return ResponseEntity.ok(
                ApiResponse.success(response, "Customers retrieved successfully")
        );
    }

    /**
     * Updates an existing customer based on the provided CustomerRequest DTO.
     *
     * @param id      the unique identifier of the customer to be updated
     * @param request the CustomerRequest DTO containing updated customer data
     * @return a ResponseEntity containing the updated CustomerResponse DTO and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request
    ) {
        CustomerResponse response = customerService.update(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(response, "Customer updated successfully")
        );
    }

    /**
     * Deletes a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer to be deleted
     * @return a ResponseEntity containing an ApiResponse indicating success and HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id
    ) {
        customerService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.success(null, "Customer deleted successfully")
        );
    }
    
}

