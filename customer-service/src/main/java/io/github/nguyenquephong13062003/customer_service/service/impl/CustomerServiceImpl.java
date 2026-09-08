package io.github.nguyenquephong13062003.customer_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequest;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponse;
import io.github.nguyenquephong13062003.customer_service.entity.Customer;
import io.github.nguyenquephong13062003.customer_service.mapper.CustomerMapper;
import io.github.nguyenquephong13062003.customer_service.repository.CustomerRepository;
import io.github.nguyenquephong13062003.customer_service.service.ICustomerService;

import java.util.List;

/**
 * Implementation of the ICustomerService interface for managing customer-related operations.
 * This class provides methods to create, retrieve, update, and delete customer data.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService {

    /**
     * Repository for accessing customer data in the database.
     */
    private final CustomerRepository customerRepository;

    /**
     * Mapper for converting between Customer entity and its corresponding DTOs.
     */
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    public CustomerResponse findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found: " + id));

        return customerMapper.toResponse(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found: " + id));

        customerMapper.updateEntity(customer, request);

        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer not found: " + id);
        }

        customerRepository.deleteById(id);
    }
    
}

