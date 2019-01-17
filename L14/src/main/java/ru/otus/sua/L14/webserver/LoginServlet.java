package ru.otus.sua.L14.webserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    @Autowired
    private TemplateProcessor templateProcessor;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestLogin = Objects.toString(
                request.getParameter(TemplateConstants.LOGIN_FORM_LOGIN_PARAMETER_NAME),
                WebserverConstants.DEFAULT_USER_NAME);

        ServletHelper.saveLoginToSession(request, requestLogin);
        ServletHelper.setOK(response);
        response.getWriter().println(
                templateProcessor.getPage(TemplateConstants.LOGIN_PAGE_TEMPLATE, requestLogin, null));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String previousLogin = ServletHelper.readLoginFromSession(request);
        ServletHelper.setOK(response);
        response.getWriter().println(
                templateProcessor.getPage(TemplateConstants.LOGIN_PAGE_TEMPLATE, previousLogin, null));
    }

}
