package ru.otus.sua.L15.starting;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;


// due to @Startup not supported by Tomcat

public interface Startup {

    default void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        start();
    }

    default void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        stop();
    }

    default void start(){}

    default void stop(){}
}
