package io.github.nguyenquephong13062003.order_service.client;

import io.github.nguyenquephong13062003.order_service.common.exception.ProductServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Client used by Order Service to communicate with Product Service.
 *
 * <p>The Product Service instance is discovered dynamically through
 * Eureka instead of using a hard-coded host and port.</p>
 */
@Component
@RequiredArgsConstructor
public class ProductServiceClient {

    /**
     * Eureka service discovery client.
     */
    private final DiscoveryClient discoveryClient;

    /**
     * Discovers an available Product Service instance and retrieves
     * the product with the specified ID.
     *
     * @param productId product ID
     * @return raw Product Service response
     * @throws IllegalStateException if no Product Service instance is available
     */
    public String findProductById(Long productId) {

        List<ServiceInstance> instances =
                discoveryClient.getInstances("PRODUCT-SERVICE");

        if (instances.isEmpty()) {
            throw new ProductServiceUnavailableException(
                    "No available instance of PRODUCT-SERVICE"
            );
        }

        ServiceInstance instance = instances.getFirst();

        String baseUrl = instance.getUri().toString();

        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        return restClient
                .get()
                .uri("/api/products/{id}", productId)
                .retrieve()
                .body(String.class);
    }
}