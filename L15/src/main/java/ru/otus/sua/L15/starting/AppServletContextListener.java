package ru.otus.sua.L15.starting;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L15.webserver.TemplateProcessor;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class AppServletContextListener implements ServletContextListener {

    @Inject
    private TemplateProcessor templateProcessor;

    public AppServletContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        templateProcessor.start(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
