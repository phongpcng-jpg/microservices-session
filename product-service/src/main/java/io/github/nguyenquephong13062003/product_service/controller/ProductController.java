package io.github.nguyenquephong13062003.product_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param request product creation request
     * @return created product
     */
    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Finds a product by ID.
     *
     * @param id product ID
     * @return product information
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.findById(id)
        );
    }

    /**
     * Returns all products.
     *
     * @return list of products
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {

        return ResponseEntity.ok(
                productService.findAll()
        );
    }

    /**
     * Updates an existing product.
     *
     * @param id product ID
     * @param request product update request
     * @return updated product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                productService.update(id, request)
        );
    }

    /**
     * Deletes a product.
     *
     * @param id product ID
     * @return empty response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
    
}
