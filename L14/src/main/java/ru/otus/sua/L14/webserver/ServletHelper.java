package ru.otus.sua.L14.webserver;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
class ServletHelper {

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

}
