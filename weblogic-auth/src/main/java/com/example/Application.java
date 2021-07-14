package com.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class Application extends ResourceConfig {

    public Application() {
        packages(MyResource.class.getPackage().getName());
        property(ServerProperties.MONITORING_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_REFRESH_INTERVAL, 1);
        setApplicationName("servlet_40_monitoring_1");
      }
}

