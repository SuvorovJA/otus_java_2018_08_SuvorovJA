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

        AdminServletAction action = new AdminServletAction(request);

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
        AdminServletAction action = new AdminServletAction(request);
        if (authLogin.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME)) action.setUserCounter();
        response.getWriter().println(
                TemplateProcessor.getPage(TemplateConstants.ADMIN_PAGE_TEMPLATE, authLogin, action.getTemplateVariables()));
    }


    @Slf4j
    static class AdminServletAction {

        private HttpServletRequest request;
        private Map<String, Object> pageVariables = new HashMap<>();
        private DBService dbService;

        AdminServletAction(HttpServletRequest request) {
            this.request = request;
            dbService = (DBService) request.getServletContext().getAttribute(WebserverConfiguration.DB_SERVICE_CONTEXT_PARAMETER_NAME);
        }

        Map<String, Object> getTemplateVariables() {
            return pageVariables;
        }

        void setUserNameById() {
            String userId = Objects.toString(request.getParameter(TemplateConstants.FINDUSER_FORM_ID_PARAMETER_NAME), "");
            int id;

            try {
                id = Integer.parseInt(userId);
            } catch (NumberFormatException e) {
                log.warn("Incorrect Id number format");
                pageVariables.put(TemplateConstants.FOUND_USER_NAME_TEMPLATE_VARIABLE_NAME, "<strong>Incorrect Id number format</strong>");
                return;
            }

            try {
                String name = dbService.load(id, UserDataSet.class).getName();
                log.info("Found user name: {} for id: {}", name, id);
                pageVariables.put(TemplateConstants.FOUND_USER_NAME_TEMPLATE_VARIABLE_NAME, name);
            } catch (SQLException | NullPointerException e) {
                log.warn("Can't find user by id: {}", id);
                pageVariables.put(TemplateConstants.FOUND_USER_NAME_TEMPLATE_VARIABLE_NAME, "<strong>Can't find user by id: " + id + "</strong>");
            }
        }

        void setAllUsersList() {
            List<UserDataSet> users;
            try {
                users = dbService.loadAll(UserDataSet.class);
                log.info("Loaded {} users records", users.size());
            } catch (SQLException e) {
                log.warn("Can't load all users");
                pageVariables.put(TemplateConstants.USER_LIST_FETCH_STATUS_TEMPLATE_VARIABLE_NAME, "<strong>Fail fetching list.</strong>");
                return;
            }

            if (users.isEmpty()) {
                log.info("No any users");
                pageVariables.put(TemplateConstants.USER_LIST_FETCH_STATUS_TEMPLATE_VARIABLE_NAME, "<strong>No any users.</strong>");
                return;
            }

            pageVariables.put(TemplateConstants.LIST_OF_USERS_TEMPLATE_VARIABLE_NAME, users);

        }

        void saveNewUser() {

            String userName = Objects.toString(request.getParameter(TemplateConstants.ADDUSER_FORM_NAME_PARAMETER_NAME), "");
            if (userName.isEmpty()) {
                pageVariables.put(TemplateConstants.USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME, "<strong>User name is required</strong>");
                return;
            }

            String userAge = Objects.toString(request.getParameter(TemplateConstants.ADDUSER_FORM_AGE_PARAMETER_NAME), "");
            int age = 0;
            try {
                if (!userAge.isEmpty()) age = Integer.parseInt(userAge);
            } catch (NumberFormatException e) {
                log.warn("Incorrect Age number format");
                pageVariables.put(TemplateConstants.USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME, "<strong>Incorrect Age number format</strong>");
                return;
            }

            String userPhone = Objects.toString(request.getParameter(TemplateConstants.ADDUSER_FORM_PHONE_PARAMETER_NAME), "");
            String userAddress = Objects.toString(request.getParameter(TemplateConstants.ADDUSER_FORM_ADDRESS_PARAMETER_NAME), "");

            UserDataSet user1 = new UserDataSet(userName, age,
                    Arrays.asList(new PhoneDataSet(userPhone)),
                    new AddressDataSet(userAddress));

            try {
                dbService.save(user1);
                log.info("Created new user: {}", user1);
                pageVariables.put(TemplateConstants.USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME, "<strong>OK</strong>");
            } catch (SQLException e) {
                log.error("Can't create new user");
                pageVariables.put(TemplateConstants.USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME, "<strong>FAIL</strong>");
            }

        }

        void setUserCounter() {
            try {
                long count = dbService.count(UserDataSet.class);
                log.info("In database {} users", count);
                pageVariables.put(TemplateConstants.USERCOUNTER_TEMPLATE_VARIABLE_NAME, count);
            } catch (SQLException e) {
                log.error("Can't count users");
                pageVariables.put(TemplateConstants.USERCOUNTER_TEMPLATE_VARIABLE_NAME, "Can't count users");
            }
        }

    }

}


