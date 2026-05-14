package com.daniele.monitor.controller;

import com.daniele.monitor.model.ServiceStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthController {

    @GetMapping("/payment/health")
    public ServiceStatus paymentHealth(){
        return new ServiceStatus("payment","UP",LocalDateTime.now());
    }

    @GetMapping("/database/health")
    public ServiceStatus databaseHealth(){
        return new ServiceStatus("database","UP",LocalDateTime.now());
    }

    @GetMapping("/auth/health")
    public ServiceStatus authHealth(){
        return new ServiceStatus("auth","UP",LocalDateTime.now());
    }

}
