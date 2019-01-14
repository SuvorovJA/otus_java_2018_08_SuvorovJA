package ru.otus.sua.L14.webserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.otus.sua.L14.dbservice.DBService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {

    private TemplateProcessor templateProcessor;
    private DBService dbService;

        @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        templateProcessor = context.getBean("templateProcessor",TemplateProcessor.class);
        dbService = context.getBean("dbService",DBService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String authLogin = ServletHelper.readLoginFromSession(request);
        log.info("Attempt post-access to admin page as: {} ", authLogin);
        if (!authLogin.equals(WebserverConstants.DEFAULT_ADMIN_USER_NAME))
            response.sendRedirect(request.getContextPath() + WebserverConstants.LOGIN_SERVLET_PATH);

        AdminServletAction action = new AdminServletAction(dbService, request);
        action.action(request.getParameter(TemplateConstants.ADMIN_PAGE_ACTION));
        action.setUserCounter();

        ServletHelper.setOK(response);
        response.getWriter().println(
                templateProcessor.getPage(TemplateConstants.ADMIN_PAGE_TEMPLATE, authLogin, action.getTemplateVariables()));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authLogin = ServletHelper.readLoginFromSession(request);
        log.info("Attempt get-access to admin page as: {} ", authLogin);

        AdminServletAction action = new AdminServletAction(dbService, request);
        if (authLogin.equals(WebserverConstants.DEFAULT_ADMIN_USER_NAME)) action.setUserCounter();

        ServletHelper.setOK(response);
        response.getWriter().println(
                templateProcessor.getPage(TemplateConstants.ADMIN_PAGE_TEMPLATE, authLogin, action.getTemplateVariables()));
    }

}


