package io.github.nguyenquephong13062003.product_service.mapper;

import org.springframework.stereotype.Component;

import io.github.nguyenquephong13062003.product_service.dto.request.ProductRequest;
import io.github.nguyenquephong13062003.product_service.dto.response.ProductResponse;
import io.github.nguyenquephong13062003.product_service.entity.Product;

/**
 * Mapper class for converting between ProductRequest, ProductResponse, and Product entities.
 */
@Component
public class ProductMapper {

    /**
     * Converts a ProductRequest to a Product entity.
     *
     * @param request product request
     * @return product entity
     */
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .description(request.description())
                .build();
    }

    /**
     * Converts a Product entity to a ProductResponse.
     *
     * @param product product entity
     * @return product response
     */
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getDescription()
        );
    }

    /**
     * Updates an existing Product entity with values from a ProductRequest.
     *
     * @param product the product entity to update
     * @param request the product request containing updated values
     */
    public void updateEntity(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setDescription(request.description());
    }
    
}
