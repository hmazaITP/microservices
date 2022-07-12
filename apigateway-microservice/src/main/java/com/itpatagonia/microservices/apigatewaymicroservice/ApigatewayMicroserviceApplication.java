package com.itpatagonia.microservices.apigatewaymicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApigatewayMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayMicroserviceApplication.class, args);
	}

}
