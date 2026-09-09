package io.github.nguyenquephong13062003.product_service.service;

import java.util.List;

import io.github.nguyenquephong13062003.product_service.dto.request.ProductRequest;
import io.github.nguyenquephong13062003.product_service.dto.response.ProductResponse;

/**
 * Service interface for managing products.
 */
public interface IProductService {

    /**
     * Creates a new product.
     *
     * @param request product creation request
     * @return created product
     */
    ProductResponse create(ProductRequest request);

    /**
     * Finds a product by its ID.
     *
     * @param id product ID
     * @return product response
     */
    ProductResponse findById(Long id);

    /**
     * Retrieves all products.
     *
     * @return list of product responses
     */
    List<ProductResponse> findAll();

    /**
     * Updates an existing product.
     *
     * @param id      product ID
     * @param request product update request
     * @return updated product response
     */
    ProductResponse update(Long id, ProductRequest request);

    /**
     * Deletes a product by its ID.
     *
     * @param id product ID
     */
    void delete(Long id);
    
}
