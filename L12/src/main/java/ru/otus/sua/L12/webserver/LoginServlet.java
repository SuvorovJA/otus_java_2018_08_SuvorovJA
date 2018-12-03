package ru.otus.sua.L12.webserver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestLogin = Objects.toString(
                request.getParameter(TemplateConstants.LOGIN_FORM_LOGIN_PARAMETER_NAME),
                WebserverConfiguration.DEFAULT_USER_NAME);

        ServletHelper.saveLoginToSession(request, requestLogin);
        ServletHelper.setOK(response);
        response.getWriter().println(
                ServletHelper.getPage(request,TemplateConstants.LOGIN_PAGE_TEMPLATE, requestLogin, null));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String previousLogin = ServletHelper.readLoginFromSession(request);
        response.getWriter().println(
                ServletHelper.getPage(request,TemplateConstants.LOGIN_PAGE_TEMPLATE, previousLogin, null));
    }

}
