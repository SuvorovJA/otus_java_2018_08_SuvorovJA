package ru.otus.sua.L12.webserver;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
class ServletHelper {

    private static final TemplateProcessor templateProcessor = new TemplateProcessor();

    static String readLoginFromSession(HttpServletRequest request) {
        String login = Objects.toString(
                request.getSession().getAttribute(WebserverConfiguration.LOGIN_SESSION_PARAMETER_NAME),
                WebserverConfiguration.DEFAULT_USER_NAME);
        log.info("Logged in session as: {}",login);
        return login;
    }

    static void saveLoginToSession(HttpServletRequest request, String requestLogin) {
        log.info("New logged in as: {}",requestLogin);
        request.getSession().setAttribute(WebserverConfiguration.LOGIN_SESSION_PARAMETER_NAME, requestLogin);
    }

    static void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    static String getPage(String page, String login, Map<String, Object> additionalPageVariables) {
        Map<String, Object> pageVariables = new HashMap<>();

        if (additionalPageVariables != null && !additionalPageVariables.isEmpty())
            pageVariables.putAll(additionalPageVariables);

        pageVariables.put(WebserverConfiguration.LOGIN_TEMPLATE_VARIABLE_NAME, login);

        pageVariables.put(WebserverConfiguration.LOGGED_STATUS_TEMPLATE_VARIABLE_NAME,
                (!login.equals(WebserverConfiguration.DEFAULT_USER_NAME)));

        pageVariables.put(WebserverConfiguration.ADMIN_LOGGED_STATUS_TEMPLATE_VARIABLE_NAME,
                (login.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME)));

        return templateProcessor.getPage(page, pageVariables);
    }

}
