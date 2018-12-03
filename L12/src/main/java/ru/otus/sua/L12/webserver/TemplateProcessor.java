package ru.otus.sua.L12.webserver;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Slf4j
class TemplateProcessor {

    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassLoaderForTemplateLoading(TemplateProcessor.class.getClassLoader(), TemplateConstants.HTML_TEMPLATES_DIR);
        configuration.setDefaultEncoding("UTF-8");
    }

    static String getPage(String page, String login, Map<String, Object> additionalPageVariables) {
        Map<String, Object> pageVariables = new HashMap<>();

        if (additionalPageVariables != null && !additionalPageVariables.isEmpty())
            pageVariables.putAll(additionalPageVariables);

        pageVariables.put(TemplateConstants.LOGIN_TEMPLATE_VARIABLE_NAME, login);

        pageVariables.put(TemplateConstants.LOGGED_STATUS_TEMPLATE_VARIABLE_NAME,
                (!login.equals(WebserverConfiguration.DEFAULT_USER_NAME)));

        pageVariables.put(TemplateConstants.ADMIN_LOGGED_STATUS_TEMPLATE_VARIABLE_NAME,
                (login.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME)));

        return getPage(page, pageVariables);
    }

    private static String getPage(String filename, Map<String, Object> data) {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException | IOException e) {
            TemplateProcessor.log.error(e.getMessage());
            return "TEMPLATE_LOAD_ERR: look logfile.";
        }
    }
}