package ru.otus.sua.L15.webserver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.saveLoginToSession(request, WebserverConstants.DEFAULT_USER_NAME);
        response.sendRedirect(request.getContextPath() + WebserverConstants.LOGIN_SERVLET_PATH);
    }


}
