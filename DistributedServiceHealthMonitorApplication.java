package com.daniele.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DistributedServiceHealthMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedServiceHealthMonitorApplication.class, args);
    }

}
