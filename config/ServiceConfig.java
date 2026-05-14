package com.daniele.monitor.config;

import java.util.List;

public class ServiceConfig {
    private String name;
    private String url;
    private List<String> dependencies;

    public ServiceConfig(String name, String url, List<String> dependencies) {
        this.name = name;
        this.url = url;
        this.dependencies = dependencies;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getDependencies() {
        return dependencies;
    }
}
