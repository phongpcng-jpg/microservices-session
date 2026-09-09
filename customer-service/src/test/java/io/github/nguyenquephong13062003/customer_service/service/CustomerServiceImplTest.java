package io.github.nguyenquephong13062003.customer_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequest;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponse;
import io.github.nguyenquephong13062003.customer_service.entity.Customer;
import io.github.nguyenquephong13062003.customer_service.mapper.CustomerMapper;
import io.github.nguyenquephong13062003.customer_service.repository.CustomerRepository;
import io.github.nguyenquephong13062003.customer_service.service.impl.CustomerServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link CustomerServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    /**
     * Mocked repository for customer operations.
     */
    @Mock
    private CustomerRepository customerRepository;

    /**
     * Mocked mapper for converting between customer entities and DTOs.
     */
    @Mock
    private CustomerMapper customerMapper;

    /**
     * Service under test.
     */
    @InjectMocks
    private CustomerServiceImpl customerService;

    /**
     * Tests the creation of a customer.
     */
    @Test
    void create_shouldReturnCustomer() {
        CustomerRequest request = new CustomerRequest(
                "Nguyen Van A",
                "a@example.com",
                "password",
                "Hanoi"
        );

        Customer customer = Customer.builder()
                .id(1L)
                .fullName("Nguyen Van A")
                .email("a@example.com")
                .password("password")
                .address("Hanoi")
                .build();

        CustomerResponse response = new CustomerResponse(
                1L,
                "Nguyen Van A",
                "a@example.com",
                "Hanoi"
        );

        when(customerMapper.toEntity(request)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toResponse(customer)).thenReturn(response);

        CustomerResponse result = customerService.create(request);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Nguyen Van A", result.fullName());

        verify(customerRepository).save(customer);
    }

    /**
     * Tests retrieving a customer by ID.
     */
    @Test
    void findById_shouldReturnCustomer() {
        Customer customer = Customer.builder()
                .id(1L)
                .fullName("Nguyen Van A")
                .email("a@example.com")
                .password("password")
                .address("Hanoi")
                .build();

        CustomerResponse response = new CustomerResponse(
                1L,
                "Nguyen Van A",
                "a@example.com",
                "Hanoi"
        );

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        when(customerMapper.toResponse(customer))
                .thenReturn(response);

        CustomerResponse result = customerService.findById(1L);

        assertEquals(1L, result.id());

        verify(customerRepository).findById(1L);
    }

    /**
     * Tests that an exception is thrown when a customer is not found by ID.
     */
    @Test
    void findById_shouldThrowExceptionWhenNotFound() {
        when(customerRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class,
                () -> customerService.findById(999L)
        );
    }

    /**
     * Tests retrieving all customers.
     */
    @Test
    void findAll_shouldReturnCustomers() {
        Customer customer = Customer.builder()
                .id(1L)
                .fullName("Nguyen Van A")
                .email("a@example.com")
                .password("password")
                .address("Hanoi")
                .build();

        CustomerResponse response = new CustomerResponse(
                1L,
                "Nguyen Van A",
                "a@example.com",
                "Hanoi"
        );

        when(customerRepository.findAll())
                .thenReturn(List.of(customer));

        when(customerMapper.toResponse(customer))
                .thenReturn(response);

        List<CustomerResponse> result = customerService.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().id());
    }

    /**
     * Tests updating a customer.
     */
    @Test
    void delete_shouldDeleteCustomer() {
        when(customerRepository.existsById(1L))
                .thenReturn(true);

        customerService.delete(1L);

        verify(customerRepository).deleteById(1L);
    }

    /**
     * Tests that an exception is thrown when trying to delete a non-existent customer.
     */
    @Test
    void delete_shouldThrowExceptionWhenNotFound() {
        when(customerRepository.existsById(999L))
                .thenReturn(false);

        assertThrows(
                IllegalArgumentException.class,
                () -> customerService.delete(999L)
        );

        verify(customerRepository, never()).deleteById(999L);
    }

}
