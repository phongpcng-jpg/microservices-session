package io.github.nguyenquephong13062003.customer_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a customer entity in the system.
 * This class is mapped to the "customers" table in the database.
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    /**
     * The unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The full name of the customer.
     */
    @Column(nullable = false, length = 150)
    private String fullName;

    /**
     * The email address of the customer.
     * This field is unique and cannot be null.
     */
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    /**
     * The password of the customer.
     * This field cannot be null and has a maximum length of 255 characters.
     */
    @Column(nullable = false, length = 255)
    private String password;

    /**
     * The address of the customer.
     * This field has a maximum length of 500 characters.
     */
    @Column(length = 500)
    private String address;

}
