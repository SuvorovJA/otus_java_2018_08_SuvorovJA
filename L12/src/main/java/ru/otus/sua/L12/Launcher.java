package ru.otus.sua.L12;


import org.eclipse.jetty.server.Server;
import ru.otus.sua.L12.webserver.WebserverConfiguration;


public class Launcher {
    public static void main(String[] args) throws Exception {
        Server server = WebserverConfiguration.getServer();
        server.start();
        server.join();
    }
}
