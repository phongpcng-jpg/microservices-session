package io.github.nguyenquephong13062003.product_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.github.nguyenquephong13062003.product_service.dto.request.ProductRequest;
import io.github.nguyenquephong13062003.product_service.dto.response.ProductResponse;
import io.github.nguyenquephong13062003.product_service.service.IProductService;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link ProductController}.
 */
@WebMvcTest(ProductController.class)
class ProductControllerTest {

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
     * Mocked service for product operations.
     */
    @MockitoBean
    private IProductService productService;

    /**
     * Tests the creation of a product and expects a 201 Created response.
     */
    @Test
    void create_shouldReturn201() throws Exception {

        ProductRequest request = new ProductRequest(
                "Laptop",
                BigDecimal.valueOf(1500),
                10,
                "Gaming laptop"
        );

        ProductResponse response = new ProductResponse(
                1L,
                "Laptop",
                BigDecimal.valueOf(1500),
                10,
                "Gaming laptop"
        );

        when(productService.create(any(ProductRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Laptop"));
    }

    /**
     * Tests the creation of a product with invalid data and expects a 400 Bad Request response.
     */
    @Test
    void create_shouldReturn400WhenValidationFails() throws Exception {

        ProductRequest request = new ProductRequest(
                "",
                BigDecimal.ZERO,
                -1,
                "Invalid"
        );

        mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
    
}