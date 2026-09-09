package io.github.nguyenquephong13062003.customer_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequestDTO;
import io.github.nguyenquephong13062003.customer_service.dto.request.LoginRequestDTO;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponseDTO;
import io.github.nguyenquephong13062003.customer_service.service.ICustomerService;

import java.util.List;

/**
 * REST controller for customer operations.
 */
@RestController
@ConditionalOnProperty(
    name = "app.customer-api-v1.enabled",
    havingValue = "true"
)
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerControllerV1 {

    private final ICustomerService customerService;

    /**
     * Registers a new customer.
     *
     * @param request registration request
     * @return created customer
     */
    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> register(
            @Valid @RequestBody CustomerRequestDTO request
    ) {

        CustomerResponseDTO response =
                customerService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Finds a customer by ID.
     *
     * @param id customer ID
     * @return customer response
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                customerService.findById1(id)
        );
    }

    /**
     * Returns all customers.
     *
     * @return list of customers
     */
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {

        return ResponseEntity.ok(
                customerService.findAll1()
        );
    }

    /**
     * Updates a customer.
     *
     * @param id customer ID
     * @param request update request
     * @return updated customer
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO request
    ) {

        return ResponseEntity.ok(
                customerService.update(id, request)
        );
    }

    /**
     * Deletes a customer.
     *
     * @param id customer ID
     * @return empty response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Authenticates a customer.
     *
     * @param request login request
     * @return authenticated customer
     */
    @PutMapping("/login")
    public ResponseEntity<CustomerResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request
    ) {

        return ResponseEntity.ok(
                customerService.login(request)
        );
    }

}