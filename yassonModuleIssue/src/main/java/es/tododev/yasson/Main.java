package es.tododev.yasson;

import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        Server jettyServer = new Server();
        ServerConnector connector = new ServerConnector(jettyServer);
        connector.setPort(8080);
        jettyServer.addConnector(connector);

        ResourceConfig resourceConfig = new ResourceConfig(AccountServices.class);
        resourceConfig.register(new JsonBindingFeature());
        resourceConfig.register(new LoggingFeature(LOGGER, LoggingFeature.Verbosity.PAYLOAD_ANY));
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder sh = new ServletHolder(servletContainer);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(sh, "/*");
        jettyServer.setHandler(context);
        jettyServer.start();
        jettyServer.join();
    }

}
