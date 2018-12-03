package ru.otus.sua.L12.webserver;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
class ServletHelper {

    private static final TemplateProcessor templateProcessor = new TemplateProcessor();

    static String readLoginFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = Objects.toString(
                session.getAttribute(WebserverConfiguration.LOGIN_SESSION_PARAMETER_NAME),
                WebserverConfiguration.DEFAULT_USER_NAME);
        log.info("Logged in session: {} as: {}", session.getId(), login);
        return login;
    }

    static void saveLoginToSession(HttpServletRequest request, String requestLogin) {
        HttpSession session = request.getSession();
        session.setAttribute(WebserverConfiguration.LOGIN_SESSION_PARAMETER_NAME, requestLogin);
        log.info("New logged in as: {}, to session: {}", requestLogin, session.getId());
    }

    static void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
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

        return templateProcessor.getPage(page, pageVariables);
    }


    @Slf4j
    static class TemplateProcessor {

        private Configuration configuration;

        TemplateProcessor() {
            configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), TemplateConstants.HTML_TEMPLATES_DIR);
            configuration.setDefaultEncoding("UTF-8");
        }

        String getPage(String filename, Map<String, Object> data) {
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
}
