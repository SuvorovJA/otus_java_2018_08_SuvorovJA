package ru.otus.sua.L15.webserver;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L15.starting.Startup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@NoArgsConstructor
@ApplicationScoped
public class TemplateProcessor implements Startup {

    private Configuration configuration;


    String getPage(String page, String login, Map<String, Object> additionalPageVariables) {
        Map<String, Object> pageVariables = new HashMap<>();

        if (additionalPageVariables != null && !additionalPageVariables.isEmpty())
            pageVariables.putAll(additionalPageVariables);

        pageVariables.put(TemplateConstants.LOGIN_TEMPLATE_VARIABLE_NAME,
                login);
        pageVariables.put(TemplateConstants.LOGGED_STATUS_TEMPLATE_VARIABLE_NAME,
                (!login.equals(WebserverConstants.DEFAULT_USER_NAME)));
        pageVariables.put(TemplateConstants.ADMIN_LOGGED_STATUS_TEMPLATE_VARIABLE_NAME,
                (login.equals(WebserverConstants.DEFAULT_ADMIN_USER_NAME)));

        return getPage(page, pageVariables);
    }

    private String getPage(String filename, Map<String, Object> data) {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException | IOException e) {
            TemplateProcessor.log.error(e.getMessage());
            return "TEMPLATE_LOAD_ERR: look logfile.";
        }
    }

    @Override
    public void start() {
        log.info("TEMLATE-PROCESSOR STARTED");
    }

    @PostConstruct
    public void start(ServletContext context) {
        log.info("TEMLATE-PROCESSOR CONFIGURED");
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setServletContextForTemplateLoading(context, TemplateConstants.HTML_TEMPLATES_DIR);
        configuration.setDefaultEncoding("UTF-8");
    }

    @Override
    public void stop() {

    }
}