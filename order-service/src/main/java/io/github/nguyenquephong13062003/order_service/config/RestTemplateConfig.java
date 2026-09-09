package io.github.nguyenquephong13062003.order_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for HTTP clients used by Order Service.
 *
 * <p>The {@link LoadBalanced} annotation allows {@link RestTemplate}
 * to resolve a service name through Eureka and distribute requests
 * among available service instances.</p>
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates a load-balanced RestTemplate.
     *
     * @return load-balanced RestTemplate instance
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
