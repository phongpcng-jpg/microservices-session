package io.github.nguyenquephong13062003.customer_service.service;

import java.util.List;

import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequest;
import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequestDTO;
import io.github.nguyenquephong13062003.customer_service.dto.request.LoginRequestDTO;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponse;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponseDTO;

/**
 * Service interface for managing customer-related operations.
 * This interface defines the contract for creating, retrieving, updating, and deleting customer data.
 */
public interface ICustomerService {

    /**
     * Creates a new customer based on the provided CustomerRequest DTO.
     *
     * @param request the CustomerRequest DTO containing customer data
     * @return a CustomerResponse DTO representing the created customer
     */
    CustomerResponse create(CustomerRequest request);

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer
     * @return a CustomerResponse DTO representing the retrieved customer
     */
    CustomerResponse findById(Long id);

    /**
     * Retrieves all customers.
     *
     * @return a list of CustomerResponse DTOs representing all customers
     */
    List<CustomerResponse> findAll();

    /**
     * Updates an existing customer identified by their unique identifier with the provided CustomerRequest DTO.
     *
     * @param id      the unique identifier of the customer to be updated
     * @param request the CustomerRequest DTO containing updated customer data
     * @return a CustomerResponse DTO representing the updated customer
     */
    CustomerResponse update(Long id, CustomerRequest request);

    /**
     * Deletes a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer to be deleted
     */
    void delete(Long id);

    /**
     * Registers a new customer based on the provided CustomerRequestDTO.
     *
     * @param request the CustomerRequestDTO containing customer data
     * @return a CustomerResponseDTO representing the registered customer
     */
    CustomerResponseDTO register(CustomerRequestDTO request);

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer
     * @return a CustomerResponseDTO representing the retrieved customer
     */
    CustomerResponseDTO findById1(Long id);

    /**
     * Retrieves all customers.
     *
     * @return a list of CustomerResponseDTOs representing all customers
     */
    List<CustomerResponseDTO> findAll1();

    /**
     * Updates an existing customer identified by their unique identifier with the provided CustomerRequestDTO.
     *
     * @param id      the unique identifier of the customer to be updated
     * @param request the CustomerRequestDTO containing updated customer data
     * @return a CustomerResponseDTO representing the updated customer
     */
    CustomerResponseDTO update(
            Long id,
            CustomerRequestDTO request
    );

    /**
     * Deletes a customer by their unique identifier.
     *
     * @param id the unique identifier of the customer to be deleted
     */
    void delete1(Long id);

    /**
     * Authenticates a customer based on the provided LoginRequestDTO.
     *
     * @param request the LoginRequestDTO containing login credentials
     * @return a CustomerResponseDTO representing the authenticated customer
     */
    CustomerResponseDTO login(LoginRequestDTO request);
    
}
