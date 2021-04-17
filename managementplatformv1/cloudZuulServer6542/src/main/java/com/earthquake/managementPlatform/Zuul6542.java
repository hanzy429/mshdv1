package com.earthquake.managementPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Zuul6542 {
    public static void main(String[] args) {
        SpringApplication.run(Zuul6542.class,args);
    }
}
