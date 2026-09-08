package io.github.nguyenquephong13062003.customer_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequest;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponse;
import io.github.nguyenquephong13062003.customer_service.service.ICustomerService;

import java.util.List;

/**
 * REST controller for managing customer-related operations.
 * This controller provides endpoints for creating, retrieving, updating, and deleting customer data.
 */
@RestController
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
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.create(request));
    }

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer
     * @return a ResponseEntity containing the retrieved CustomerResponse DTO and HTTP status
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(customerService.findById(id));
    }

    /**
     * Retrieves all customers.
     *
     * @return a ResponseEntity containing a list of CustomerResponse DTOs and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    /**
     * Updates an existing customer identified by their unique identifier with the provided CustomerRequest DTO.
     *
     * @param id      the unique identifier of the customer to be updated
     * @param request the CustomerRequest DTO containing updated customer data
     * @return a ResponseEntity containing the updated CustomerResponse DTO and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {

        return ResponseEntity.ok(
                customerService.update(id, request)
        );
    }

    /**
     * Deletes a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer to be deleted
     * @return a ResponseEntity with HTTP status indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }
    
}

