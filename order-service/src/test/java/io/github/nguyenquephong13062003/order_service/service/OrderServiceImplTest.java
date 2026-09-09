package io.github.nguyenquephong13062003.order_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.nguyenquephong13062003.order_service.dto.request.OrderRequest;
import io.github.nguyenquephong13062003.order_service.dto.response.OrderResponse;
import io.github.nguyenquephong13062003.order_service.entity.Order;
import io.github.nguyenquephong13062003.order_service.entity.OrderStatus;
import io.github.nguyenquephong13062003.order_service.mapper.OrderMapper;
import io.github.nguyenquephong13062003.order_service.repository.OrderRepository;
import io.github.nguyenquephong13062003.order_service.service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link OrderServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    /**
     * Mocked repository for order operations.
     */
    @Mock
    private OrderRepository orderRepository;

    /**
     * Mocked mapper for converting between order entities and DTOs.
     */
    @Mock
    private OrderMapper orderMapper;

    /**
     * Service under test.
     */
    @InjectMocks
    private OrderServiceImpl orderService;

    /**
     * Tests the creation of an order.
     */
    @Test
    void create_shouldReturnOrder() {

        OrderRequest request = new OrderRequest(
                1L,
                BigDecimal.valueOf(250),
                OrderStatus.PENDING
        );

        Order order = Order.builder()
                .id(1L)
                .customerId(1L)
                .orderDate(LocalDateTime.now())
                .totalAmount(BigDecimal.valueOf(250))
                .status(OrderStatus.PENDING)
                .build();

        OrderResponse response = new OrderResponse(
                1L,
                1L,
                order.getOrderDate(),
                BigDecimal.valueOf(250),
                OrderStatus.PENDING
        );

        when(orderMapper.toEntity(request)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toResponse(order)).thenReturn(response);

        OrderResponse result = orderService.create(request);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(1L, result.customerId());
        assertEquals(OrderStatus.PENDING, result.status());

        verify(orderRepository).save(order);
    }

    /**
     * Tests finding an order by ID.
     */
    @Test
    void findById_shouldReturnOrder() {

        Order order = Order.builder()
                .id(1L)
                .customerId(1L)
                .orderDate(LocalDateTime.now())
                .totalAmount(BigDecimal.valueOf(250))
                .status(OrderStatus.PENDING)
                .build();

        OrderResponse response = new OrderResponse(
                1L,
                1L,
                order.getOrderDate(),
                BigDecimal.valueOf(250),
                OrderStatus.PENDING
        );

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        when(orderMapper.toResponse(order))
                .thenReturn(response);

        OrderResponse result = orderService.findById(1L);

        assertEquals(1L, result.id());
        assertEquals(1L, result.customerId());
    }

    /**
     * Tests finding an order by ID when the order does not exist.
     */
    @Test
    void findById_shouldThrowExceptionWhenNotFound() {

        when(orderRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.findById(999L)
        );
    }

    /**
     * Tests finding orders by customer ID.
     */
    @Test
    void findByCustomerId_shouldReturnOrders() {

        Order order = Order.builder()
                .id(1L)
                .customerId(10L)
                .orderDate(LocalDateTime.now())
                .totalAmount(BigDecimal.valueOf(500))
                .status(OrderStatus.CONFIRMED)
                .build();

        OrderResponse response = new OrderResponse(
                1L,
                10L,
                order.getOrderDate(),
                BigDecimal.valueOf(500),
                OrderStatus.CONFIRMED
        );

        when(orderRepository.findByCustomerId(10L))
                .thenReturn(List.of(order));

        when(orderMapper.toResponse(order))
                .thenReturn(response);

        List<OrderResponse> result =
                orderService.findByCustomerId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.getFirst().customerId());

        verify(orderRepository).findByCustomerId(10L);
    }

    /**
     * Tests deleting an order by ID.
     */
    @Test
    void delete_shouldDeleteOrder() {

        when(orderRepository.existsById(1L))
                .thenReturn(true);

        orderService.delete(1L);

        verify(orderRepository).deleteById(1L);
    }

    /**
     * Tests deleting an order by ID when the order does not exist.
     */
    @Test
    void delete_shouldThrowExceptionWhenNotFound() {

        when(orderRepository.existsById(999L))
                .thenReturn(false);

        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.delete(999L)
        );

        verify(orderRepository, never()).deleteById(999L);
    }
    
}
