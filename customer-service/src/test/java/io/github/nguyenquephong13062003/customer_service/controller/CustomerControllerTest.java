package io.github.nguyenquephong13062003.customer_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.github.nguyenquephong13062003.customer_service.dto.request.CustomerRequest;
import io.github.nguyenquephong13062003.customer_service.dto.response.CustomerResponse;
import io.github.nguyenquephong13062003.customer_service.service.ICustomerService;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link CustomerController}.
 */
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    /**
     * MockMvc for performing HTTP requests in tests.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * ObjectMapper for converting objects to JSON and vice versa.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Mocked service for customer operations.
     */
    @MockitoBean
    private ICustomerService customerService;

    /**
     * Tests the creation of a customer and expects a 201 Created response.
     */
    @Test
    void create_shouldReturn201() throws Exception {

        CustomerRequest request = new CustomerRequest(
                "Nguyen Van A",
                "a@example.com",
                "password",
                "Hanoi"
        );

        CustomerResponse response = new CustomerResponse(
                1L,
                "Nguyen Van A",
                "a@example.com",
                "Hanoi"
        );

        when(customerService.create(any(CustomerRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message")
                        .value("Customer created successfully"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.fullName")
                        .value("Nguyen Van A"));
    }

    /**
     * Tests the creation of a customer with invalid data and expects a 400 Bad Request response.
     */
    @Test
    void create_shouldReturn400WhenValidationFails() throws Exception {

        CustomerRequest request = new CustomerRequest(
                "",
                "invalid-email",
                "123",
                "Hanoi"
        );

        mockMvc.perform(
                        post("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
    
}
