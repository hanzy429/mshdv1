package com.earthquake.managementPlatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PredictionModel7701 {
    public static void main(String[] args) {
        SpringApplication.run(PredictionModel7701.class,args);
    }
}
