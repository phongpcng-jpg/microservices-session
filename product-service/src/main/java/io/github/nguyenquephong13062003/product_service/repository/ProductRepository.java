package io.github.nguyenquephong13062003.product_service.repository;

import io.github.nguyenquephong13062003.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Product entities.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}