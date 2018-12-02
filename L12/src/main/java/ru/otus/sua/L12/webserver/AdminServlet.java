package ru.otus.sua.L12.webserver;

import lombok.extern.slf4j.Slf4j;
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

        String requestAction = Objects.toString(
                request.getParameter(WebserverConfiguration.ADMIN_PAGE_ACTION), "undef");

        Map<String, Object> pageVariables = new HashMap<>();
        switch (requestAction) {
            case (WebserverConfiguration.ADMIN_PAGE_ACTION_ADDUSER):
                saveNewUser(request, pageVariables);
                break;
            case (WebserverConfiguration.ADMIN_PAGE_ACTION_GETUSERLIST):
                setAllUsersList(request, pageVariables);
                break;
            case (WebserverConfiguration.ADMIN_PAGE_ACTION_GETUSERNAME):
                setUserNameById(request, pageVariables);
                break;
        }

        setUserCounter(pageVariables);

        response.getWriter().println(
                ServletHelper.getPage(WebserverConfiguration.ADMIN_PAGE_TEMPLATE, authLogin, pageVariables));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authLogin = ServletHelper.readLoginFromSession(request);
        log.info("Attempt get-access to admin page as: {} ", authLogin);
        Map<String, Object> pageVariables = new HashMap<>();
        if (authLogin.equals(WebserverConfiguration.DEFAULT_ADMIN_USER_NAME)) setUserCounter(pageVariables);
        response.getWriter().println(
                ServletHelper.getPage(WebserverConfiguration.ADMIN_PAGE_TEMPLATE, authLogin, pageVariables));
    }

    private void setUserNameById(HttpServletRequest request, Map<String, Object> pageVariables) {
        String userId = Objects.toString(
                request.getParameter(WebserverConfiguration.FINDUSER_FORM_ID_PARAMETER_NAME), "");
        int id = 0;

        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            log.warn("Incorrect Id number format");
            pageVariables.put(WebserverConfiguration.FOUND_USER_NAME_TEMPLATE_VARIABLE_NAME,
                    "<strong>Incorrect Id number format</strong>");
            return;
        }

        try {
            String name = WebserverConfiguration.getDb().load(id, UserDataSet.class).getName();
            log.info("Found user name: {} for id: {}", name, id);
            pageVariables.put(WebserverConfiguration.FOUND_USER_NAME_TEMPLATE_VARIABLE_NAME, name);
        } catch (SQLException | NullPointerException e) {
            log.warn("Can't find user by id: {}", id);
            pageVariables.put(WebserverConfiguration.FOUND_USER_NAME_TEMPLATE_VARIABLE_NAME,
                    "<strong>Can't find user by id: " + id + "</strong>");
        }
    }

    private void setAllUsersList(HttpServletRequest request, Map<String, Object> pageVariables) {
        List<UserDataSet> users;
        try {
            users = WebserverConfiguration.getDb().loadAll(UserDataSet.class);
            log.info("Loaded {} users records", users.size());
        } catch (SQLException e) {
            log.warn("Can't load all users");
            pageVariables.put(WebserverConfiguration.USER_LIST_FETCH_STATUS_TEMPLATE_VARIABLE_NAME,
                    "<strong>Fail fetching list.</strong>");
            return;
        }

        if (users.isEmpty()) {
            log.info("No any users");
            pageVariables.put(WebserverConfiguration.USER_LIST_FETCH_STATUS_TEMPLATE_VARIABLE_NAME,
                    "<strong>No any users.</strong>");
            return;
        }

        pageVariables.put(WebserverConfiguration.LIST_OF_USERS_TEMPLATE_VARIABLE_NAME,
                users);

    }

    private void saveNewUser(HttpServletRequest request, Map<String, Object> pageVariables) {

        String userName = Objects.toString(
                request.getParameter(WebserverConfiguration.ADDUSER_FORM_NAME_PARAMETER_NAME), "");
        if (userName.isEmpty()) {
            pageVariables.put(WebserverConfiguration.USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME,
                    "<strong>User name is required</strong>");
            return;
        }

        String userAge = Objects.toString(
                request.getParameter(WebserverConfiguration.ADDUSER_FORM_AGE_PARAMETER_NAME), "");
        int age = 0;
        try {
            if (!userAge.isEmpty()) age = Integer.parseInt(userAge);
        } catch (NumberFormatException e) {
            log.warn("Incorrect Age number format");
        }

        String userPhone = Objects.toString(
                request.getParameter(WebserverConfiguration.ADDUSER_FORM_PHONE_PARAMETER_NAME), "");

        String userAddress = Objects.toString(
                request.getParameter(WebserverConfiguration.ADDUSER_FORM_ADDRESS_PARAMETER_NAME), "");

        UserDataSet user1 = new UserDataSet(userName, age,
                Arrays.asList(new PhoneDataSet(userPhone)),
                new AddressDataSet(userAddress));

        try {
            WebserverConfiguration.getDb().save(user1);
            log.info("Created new user: {}", user1);
            pageVariables.put(WebserverConfiguration.USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME,
                    "<strong>OK</strong>");
        } catch (SQLException e) {
            log.error("Can't create new user");
            pageVariables.put(WebserverConfiguration.USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME,
                    "<strong>FAIL</strong>");
        }

    }

    private void setUserCounter(Map<String, Object> pageVariables) {
        try {
            long count = WebserverConfiguration.getDb().count(UserDataSet.class);
            log.info("In database {} users", count);
            pageVariables.put(WebserverConfiguration.USERCOUNTER_TEMPLATE_VARIABLE_NAME, count);
        } catch (SQLException e) {
            log.error("Can't count users");
            pageVariables.put(WebserverConfiguration.USERCOUNTER_TEMPLATE_VARIABLE_NAME,
                    "Can't count users");
        }
    }
}


