package io.github.nguyenquephong13062003.customer_service.repository;

import io.github.nguyenquephong13062003.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Customer entities.
 * This interface extends JpaRepository to provide CRUD operations and custom query methods.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds a customer by their email address.
     *
     * @param email the email address of the customer to find
     * @return an Optional containing the found Customer, or empty if no customer is found
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Checks if a customer exists with the given email address.
     *
     * @param email the email address to check for existence
     * @return true if a customer exists with the given email, false otherwise
     */
    boolean existsByEmail(String email);

}
