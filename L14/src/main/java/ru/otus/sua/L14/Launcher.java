package ru.otus.sua.L14;


import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.sua.L14.dbservice.DBService;
import ru.otus.sua.L14.dbservice.DBServiceHibernateImpl;
import ru.otus.sua.L14.webserver.JettyServerWrapper;
import ru.otus.sua.L14.webserver.WebserverConfiguration;


public class Launcher {
    public static void main(String[] args) throws Exception {

        DBService dbService = new DBServiceHibernateImpl();
        ServletContextHandler config = WebserverConfiguration.getConfiguration();
        JettyServerWrapper server = new JettyServerWrapper(dbService, config);
        server.start();
        server.join();
    }
}
