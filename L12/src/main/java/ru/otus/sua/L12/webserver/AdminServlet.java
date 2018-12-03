package ru.otus.sua.L12.webserver;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String authLogin = ServletHelper.readLoginFromSession(request);
        log.info("Attempt post-access to admin page as: {} ", authLogin);
        if (!authLogin.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME))
            response.sendRedirect(request.getContextPath() + WebserverConfiguration.LOGIN_SERVLET_PATH);

        AdminServletMethods helper = new AdminServletMethods(request);

        switch (Objects.toString(request.getParameter(TemplateConstants.ADMIN_PAGE_ACTION), "undef")) {
            case (TemplateConstants.ADMIN_PAGE_ACTION_ADDUSER):
                helper.saveNewUser();
                break;
            case (TemplateConstants.ADMIN_PAGE_ACTION_GETUSERLIST):
                helper.setAllUsersList();
                break;
            case (TemplateConstants.ADMIN_PAGE_ACTION_GETUSERNAME):
                helper.setUserNameById();
                break;
        }

        helper.setUserCounter();

        response.getWriter().println(
                ServletHelper.getPage(request, TemplateConstants.ADMIN_PAGE_TEMPLATE, authLogin, helper.getTemplateVariables()));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authLogin = ServletHelper.readLoginFromSession(request);
        log.info("Attempt get-access to admin page as: {} ", authLogin);
        AdminServletMethods helper = new AdminServletMethods(request);
        if (authLogin.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME)) helper.setUserCounter();
        response.getWriter().println(
                ServletHelper.getPage(request, TemplateConstants.ADMIN_PAGE_TEMPLATE, authLogin, helper.getTemplateVariables()));
    }


}


