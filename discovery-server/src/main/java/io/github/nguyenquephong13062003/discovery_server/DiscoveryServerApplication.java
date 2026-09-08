package io.github.nguyenquephong13062003.discovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * The DiscoveryServerApplication class is the entry point for the Spring Boot application.
 * It is annotated with @SpringBootApplication to indicate that it is a Spring Boot application
 * and @EnableEurekaServer to enable the Eureka Server functionality.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

	/**
	 * The main method is the entry point of the application.
	 * It uses SpringApplication.run() to launch the Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

}
