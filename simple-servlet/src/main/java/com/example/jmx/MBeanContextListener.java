package com.example.jmx;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MBeanContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
            ObjectName name = new ObjectName("com.example.jmx:type=JsonCheck");
            JsonCheck mbean = new JsonCheck(); 
            mbs.registerMBean(mbean, name);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            throw new IllegalStateException("Cannot deploy the application", e);
        } 
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
