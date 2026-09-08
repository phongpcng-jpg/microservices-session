package io.github.nguyenquephong13062003.customer_service.mapper;

import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequest;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponse;
import io.github.nguyenquephong13062003.customer_service.entity.Customer;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Customer entity and its corresponding DTOs.
 * This class provides methods to map CustomerRequest to Customer entity and Customer entity to CustomerResponse.
 */
@Component
public class CustomerMapper {

    /**
     * Converts a CustomerRequest DTO to a Customer entity.
     *
     * @param request the CustomerRequest DTO containing customer data
     * @return a Customer entity populated with data from the request
     */
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .build();
    }

    /**
     * Converts a Customer entity to a CustomerResponse DTO.
     *
     * @param customer the Customer entity to be converted
     * @return a CustomerResponse DTO containing data from the customer entity
     */
    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

    /**
     * Updates an existing Customer entity with data from a CustomerRequest DTO.
     *
     * @param customer the Customer entity to be updated
     * @param request  the CustomerRequest DTO containing updated customer data
     */
    public void updateEntity(Customer customer, CustomerRequest request) {
        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPassword(request.password());
        customer.setAddress(request.address());
    }
    
}
