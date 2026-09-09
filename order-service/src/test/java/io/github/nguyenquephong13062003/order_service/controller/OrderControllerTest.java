package io.github.nguyenquephong13062003.order_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.github.nguyenquephong13062003.order_service.dto.request.OrderRequest;
import io.github.nguyenquephong13062003.order_service.dto.response.OrderResponse;
import io.github.nguyenquephong13062003.order_service.entity.OrderStatus;
import io.github.nguyenquephong13062003.order_service.service.IOrderService;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link OrderController}.
 */
@WebMvcTest(OrderController.class)
class OrderControllerTest {

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
     * Mocked service for order operations.
     */
    @MockitoBean
    private IOrderService orderService;

    /**
     * Tests the creation of an order and expects a 201 Created response.
     */
    @Test
    void create_shouldReturn201() throws Exception {

        OrderRequest request = new OrderRequest(
                1L,
                BigDecimal.valueOf(250),
                OrderStatus.PENDING
        );

        OrderResponse response = new OrderResponse(
                1L,
                1L,
                LocalDateTime.now(),
                BigDecimal.valueOf(250),
                OrderStatus.PENDING
        );

        when(orderService.create(any(OrderRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.customerId").value(1))
                .andExpect(jsonPath("$.data.status").value("PENDING"));
    }

    /**
     * Tests the creation of an order with invalid data and expects a 400 Bad Request response.
     */
    @Test
    void create_shouldReturn400WhenValidationFails() throws Exception {

        OrderRequest request = new OrderRequest(
                0L,
                BigDecimal.ZERO,
                null
        );

        mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
    
}
