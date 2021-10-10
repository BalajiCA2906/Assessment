package com.coffeeshop.servicehub.servicehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHubApplication.class, args);
    }

}
