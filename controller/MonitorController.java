package com.daniele.monitor.controller;

import com.daniele.monitor.model.ServiceStatus;
import com.daniele.monitor.service.MonitoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorController {
    private final MonitoringService monitoringService;
    public MonitorController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/monitor")
    public List<ServiceStatus> monitor() {
        return monitoringService.getLatestResults();
    }
}
