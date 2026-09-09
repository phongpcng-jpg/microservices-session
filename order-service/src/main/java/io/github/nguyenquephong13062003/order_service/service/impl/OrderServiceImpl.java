package io.github.nguyenquephong13062003.order_service.service.impl;

import io.github.nguyenquephong13062003.order_service.client.ProductServiceClient;
import io.github.nguyenquephong13062003.order_service.common.exception.ProductServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.nguyenquephong13062003.order_service.dto.request.OrderRequest;
import io.github.nguyenquephong13062003.order_service.dto.response.OrderResponse;
import io.github.nguyenquephong13062003.order_service.entity.Order;
import io.github.nguyenquephong13062003.order_service.mapper.OrderMapper;
import io.github.nguyenquephong13062003.order_service.repository.OrderRepository;
import io.github.nguyenquephong13062003.order_service.service.IOrderService;

import java.util.List;

/**
 * Default implementation of OrderService.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements IOrderService {

    /**
     * Repository for order data access.
     */
    private final OrderRepository orderRepository;

    /**
     * Mapper for converting between Order entities and DTOs.
     */
    private final OrderMapper orderMapper;

    /**
     * Client for discovering and communicating with Product Service.
     */
    private final ProductServiceClient productServiceClient;

    @Override
    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = orderMapper.toEntity(request);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Order not found: " + id
                        )
                );

        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    public List<OrderResponse> findByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponse update(Long id, OrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Order not found: " + id
                        )
                );

        orderMapper.updateEntity(order, request);

        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Order not found: " + id
            );
        }

        orderRepository.deleteById(id);
    }

    @Override
    public String findProductById(Long productId) {
        try {
            return productServiceClient.findProductById(productId);
        } catch (ProductServiceUnavailableException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ProductServiceUnavailableException(
                    "Product Service is unavailable",
                    exception
            );
        }
    }

}
