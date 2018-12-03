package ru.otus.sua.L12.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.sua.L12.dbservice.DBService;

public class JettyServerWrapper {

    Server server;

    public JettyServerWrapper(DBService dbService, ServletContextHandler context) {
        server = new Server(WebserverConfiguration.PORT);
        context.getServletContext().setAttribute(WebserverConfiguration.DBSERVICE, dbService);
        server.setHandler(context);
    }

    public void start() throws Exception {
        server.start();
    }

    public void join() throws InterruptedException {
        server.join();
    }

}
