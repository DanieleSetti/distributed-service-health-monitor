package com.daniele.monitor.model;

import java.time.LocalDateTime;

public class ServiceStatus {
    private String service;
    private String status;
    private LocalDateTime timestamp;

    public ServiceStatus(String service, String status, LocalDateTime timestamp) {
        this.service = service;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getService() {
        return service;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
