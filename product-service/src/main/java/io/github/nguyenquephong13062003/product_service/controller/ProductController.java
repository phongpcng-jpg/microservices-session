package io.github.nguyenquephong13062003.product_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.nguyenquephong13062003.product_service.common.response.ApiResponse;
import io.github.nguyenquephong13062003.product_service.dto.request.ProductRequest;
import io.github.nguyenquephong13062003.product_service.dto.response.ProductResponse;
import io.github.nguyenquephong13062003.product_service.service.IProductService;

import java.util.List;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    /**
     * Service for managing products.
     */
    private final IProductService productService;

    /**
     * Creates a new product.
     *
     * @param request the product creation request
     * @return the created product response
     */
    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse response = productService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Product created successfully"));
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID
     * @return the product response
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(
            @PathVariable Long id
    ) {
        ProductResponse response = productService.findById(id);

        return ResponseEntity.ok(
                ApiResponse.success(response, "Product retrieved successfully")
        );
    }

    /**
     * Retrieves all products.
     *
     * @return a list of product responses
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> findAll() {
        List<ProductResponse> response = productService.findAll();

        return ResponseEntity.ok(
                ApiResponse.success(response, "Products retrieved successfully")
        );
    }

    /**
     * Updates an existing product.
     *
     * @param id      the product ID
     * @param request the product update request
     * @return the updated product response
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse response = productService.update(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(response, "Product updated successfully")
        );
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID
     * @return a response indicating the deletion status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id
    ) {
        productService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.success(null, "Product deleted successfully")
        );
    }
    
}
