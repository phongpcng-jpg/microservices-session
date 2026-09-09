package io.github.nguyenquephong13062003.product_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.nguyenquephong13062003.product_service.dto.request.ProductRequest;
import io.github.nguyenquephong13062003.product_service.dto.response.ProductResponse;
import io.github.nguyenquephong13062003.product_service.entity.Product;
import io.github.nguyenquephong13062003.product_service.mapper.ProductMapper;
import io.github.nguyenquephong13062003.product_service.repository.ProductRepository;
import io.github.nguyenquephong13062003.product_service.service.IProductService;

import java.util.List;

/**
 * Implementation of the IProductService interface for managing products.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements IProductService {

    /**
     * Repository for accessing product data.
     */
    private final ProductRepository productRepository;

    /**
     * Mapper for converting between ProductRequest, ProductResponse, and Product entities.
     */
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product product = productMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found: " + id
                        )
                );

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found: " + id
                        )
                );

        productMapper.updateEntity(product, request);

        return productMapper.toResponse(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Product not found: " + id
            );
        }

        productRepository.deleteById(id);
    }
    
}
