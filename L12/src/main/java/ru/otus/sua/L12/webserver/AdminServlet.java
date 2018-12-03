package ru.otus.sua.L12.webserver;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L12.dbservice.DBService;
import ru.otus.sua.L12.entity.AddressDataSet;
import ru.otus.sua.L12.entity.PhoneDataSet;
import ru.otus.sua.L12.entity.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String authLogin = ServletHelper.readLoginFromSession(request);
        log.info("Attempt post-access to admin page as: {} ", authLogin);
        if (!authLogin.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME))
            response.sendRedirect(request.getContextPath() + WebserverConfiguration.LOGIN_SERVLET_PATH);

        DBService dbService = (DBService) request.getServletContext().getAttribute(WebserverConfiguration.DB_SERVICE_CONTEXT_PARAMETER_NAME);
        AdminServletAction action = new AdminServletAction(dbService, request);

        switch (Objects.toString(request.getParameter(TemplateConstants.ADMIN_PAGE_ACTION), "undef")) {
            case (TemplateConstants.ADMIN_PAGE_ACTION_ADDUSER):
                action.saveNewUser();
                break;
            case (TemplateConstants.ADMIN_PAGE_ACTION_GETUSERLIST):
                action.setAllUsersList();
                break;
            case (TemplateConstants.ADMIN_PAGE_ACTION_GETUSERNAME):
                action.setUserNameById();
                break;
        }

        action.setUserCounter();

        response.getWriter().println(
                TemplateProcessor.getPage(TemplateConstants.ADMIN_PAGE_TEMPLATE, authLogin, action.getTemplateVariables()));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authLogin = ServletHelper.readLoginFromSession(request);
        log.info("Attempt get-access to admin page as: {} ", authLogin);

        DBService dbService = (DBService) request.getServletContext().getAttribute(WebserverConfiguration.DB_SERVICE_CONTEXT_PARAMETER_NAME);
        AdminServletAction action = new AdminServletAction(dbService,request);

        if (authLogin.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME)) action.setUserCounter();
        response.getWriter().println(
                TemplateProcessor.getPage(TemplateConstants.ADMIN_PAGE_TEMPLATE, authLogin, action.getTemplateVariables()));
    }

}


