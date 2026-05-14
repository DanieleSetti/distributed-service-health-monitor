package com.daniele.monitor.service;

import com.daniele.monitor.config.ServiceConfig;
import com.daniele.monitor.model.ServiceStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonitoringService {
    private final RestTemplate restTemplate = new RestTemplate();
    private List<ServiceStatus> latestResults =  new ArrayList<>();
    private final List<ServiceConfig> services = List.of(
            new ServiceConfig(
                    "payment",
                    "http://localhost:8080/payment/health",
                    List.of("database")
            ),
            new ServiceConfig(
                    "database",
                    "http://localhost:9999/database/health",
                    List.of()
            ),
            new ServiceConfig(
                    "auth",
                    "http://localhost:8080/auth/health",
                    List.of()
            )
    );
        public List<ServiceStatus> checkServices(){
        List<ServiceStatus> results = new ArrayList<>();
        for (ServiceConfig service : services) {
            ServiceStatus status = checkService(service);
            results.add(status);
        }
        applyDependencyPropagation(results);
        return results;
    }
    private ServiceStatus checkService(ServiceConfig service) {
        try {
            return restTemplate.getForObject(service.getUrl(), ServiceStatus.class);
        }
        catch (Exception e){
            logger.error("{} SERVICE DOWN", service.getName().toUpperCase());
            return new ServiceStatus(service.getName(), "DOWN", LocalDateTime.now());
        }
    }
    private void applyDependencyPropagation(List<ServiceStatus> results) {
            for (ServiceStatus service : results) {
                if (service.getService().equals("payment")) {
                    for (ServiceStatus dependency : results) {
                        if (dependency.getService().equals("database") && dependency.getStatus().equals("DOWN")){
                            service.setStatus("DEGRADED");
                            logger.warn(
                                    "{} SERVICE DEGRADED DUE TO {} FAILURE",
                                    service.getService().toUpperCase(),
                                    dependency.getService().toUpperCase()
                            );
                        }
                    }
                }
            }
    }
    @Scheduled(fixedRate = 1000)
    public void monitoringServices() {
        logger.info("Running scheduled health checks...");
        latestResults = checkServices();
    }
    public  List<ServiceStatus> getLatestResults() {
        return latestResults;
    }
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);
}
