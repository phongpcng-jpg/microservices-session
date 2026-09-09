package io.github.nguyenquephong13062003.product_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.nguyenquephong13062003.product_service.dto.request.ProductRequest;
import io.github.nguyenquephong13062003.product_service.dto.response.ProductResponse;
import io.github.nguyenquephong13062003.product_service.entity.Product;
import io.github.nguyenquephong13062003.product_service.mapper.ProductMapper;
import io.github.nguyenquephong13062003.product_service.repository.ProductRepository;
import io.github.nguyenquephong13062003.product_service.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link ProductServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    /**
     * Mocked repository for product operations.
     */
    @Mock
    private ProductRepository productRepository;

    /**
     * Mocked mapper for converting between product entities and DTOs.
     */
    @Mock
    private ProductMapper productMapper;

    /**
     * Service under test.
     */
    @InjectMocks
    private ProductServiceImpl productService;

    /**
     * Tests the creation of a product.
     */
    @Test
    void create_shouldReturnProduct() {

        ProductRequest request = new ProductRequest(
                "Laptop",
                BigDecimal.valueOf(1500),
                10,
                "Gaming laptop"
        );

        Product product = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(BigDecimal.valueOf(1500))
                .stockQuantity(10)
                .description("Gaming laptop")
                .build();

        ProductResponse response = new ProductResponse(
                1L,
                "Laptop",
                BigDecimal.valueOf(1500),
                10,
                "Gaming laptop"
        );

        when(productMapper.toEntity(request)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toResponse(product)).thenReturn(response);

        ProductResponse result = productService.create(request);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Laptop", result.name());

        verify(productRepository).save(product);
    }

    /**
     * Tests the retrieval of a product by ID.
     */
    @Test
    void findById_shouldReturnProduct() {

        Product product = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(BigDecimal.valueOf(1500))
                .stockQuantity(10)
                .build();

        ProductResponse response = new ProductResponse(
                1L,
                "Laptop",
                BigDecimal.valueOf(1500),
                10,
                null
        );

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(productMapper.toResponse(product))
                .thenReturn(response);

        ProductResponse result = productService.findById(1L);

        assertEquals(1L, result.id());
        assertEquals("Laptop", result.name());
    }

    /**
     * Tests the retrieval of a product by ID when the product does not exist.
     */
    @Test
    void findById_shouldThrowExceptionWhenNotFound() {

        when(productRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class,
                () -> productService.findById(999L)
        );
    }

    /**
     * Tests the retrieval of all products.
     */
    @Test
    void findAll_shouldReturnProducts() {

        Product product = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(BigDecimal.valueOf(1500))
                .stockQuantity(10)
                .build();

        ProductResponse response = new ProductResponse(
                1L,
                "Laptop",
                BigDecimal.valueOf(1500),
                10,
                null
        );

        when(productRepository.findAll())
                .thenReturn(List.of(product));

        when(productMapper.toResponse(product))
                .thenReturn(response);

        List<ProductResponse> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.getFirst().name());
    }

    /**
     * Tests the deletion of a product.
     */
    @Test
    void delete_shouldDeleteProduct() {

        when(productRepository.existsById(1L))
                .thenReturn(true);

        productService.delete(1L);

        verify(productRepository).deleteById(1L);
    }

    /**
     * Tests that an exception is thrown when trying to delete a non-existent product.
     */
    @Test
    void delete_shouldThrowExceptionWhenNotFound() {

        when(productRepository.existsById(999L))
                .thenReturn(false);

        assertThrows(
                IllegalArgumentException.class,
                () -> productService.delete(999L)
        );

        verify(productRepository, never()).deleteById(999L);
    }
    
}
