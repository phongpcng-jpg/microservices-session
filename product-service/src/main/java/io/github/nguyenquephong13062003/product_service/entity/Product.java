package io.github.nguyenquephong13062003.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Represents a product entity in the system.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * The unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     */
    @Column(nullable = false, length = 255)
    private String name;

    /**
     * The price of the product.
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    /**
     * The quantity of the product in stock.
     */
    @Column(nullable = false)
    private Integer stockQuantity;

    /**
     * The description of the product.
     */
    @Column(length = 1000)
    private String description;
    
}
