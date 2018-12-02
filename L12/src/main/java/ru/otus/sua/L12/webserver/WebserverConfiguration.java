package ru.otus.sua.L12.webserver;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import ru.otus.sua.L12.dbservice.DBService;
import ru.otus.sua.L12.dbservice.DBServiceHibernateImpl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class WebserverConfiguration {

    static final String DEFAULT_USER_NAME = "anonymous";
    static final String DEFAULT_ADMIN_USER_NAME = "admin";
    //
    static final String LOGIN_SESSION_PARAMETER_NAME = "login";
    //
    static final String LOGIN_FORM_LOGIN_PARAMETER_NAME = "login";
    static final String LOGIN_FORM_PASSWORD_PARAMETER_NAME = "password";
    static final String ADDUSER_FORM_NAME_PARAMETER_NAME = "name";
    static final String ADDUSER_FORM_AGE_PARAMETER_NAME = "age";
    static final String ADDUSER_FORM_PHONE_PARAMETER_NAME = "phone";
    static final String ADDUSER_FORM_ADDRESS_PARAMETER_NAME = "address";
    static final String FINDUSER_FORM_ID_PARAMETER_NAME = "id";
    //
    static final String ADMIN_PAGE_ACTION_GETUSERLIST = "getUserList";
    static final String ADMIN_PAGE_ACTION_ADDUSER = "addUser";
    static final String ADMIN_PAGE_ACTION_GETUSERNAME = "getUserById";
    static final String ADMIN_PAGE_ACTION = "typeRequest";
    //
    static final String LOGIN_TEMPLATE_VARIABLE_NAME = "login";
    static final String USERCOUNTER_TEMPLATE_VARIABLE_NAME = "countUsers";
    static final String LOGGED_STATUS_TEMPLATE_VARIABLE_NAME = "loggedin";
    static final String LIST_OF_USERS_TEMPLATE_VARIABLE_NAME = "userList";
    static final String ADMIN_LOGGED_STATUS_TEMPLATE_VARIABLE_NAME = "loggedinAsAdmin";
    static final String USER_CREATION_STATUS_TEMPLATE_VARIABLE_NAME = "userCreationResult";
    static final String FOUND_USER_NAME_TEMPLATE_VARIABLE_NAME = "userNameById";
    static final String USER_LIST_FETCH_STATUS_TEMPLATE_VARIABLE_NAME = "getUserListStatus";
    //
    static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    static final String LOGIN_PAGE_TEMPLATE = "login.html";
    static final String HTML_TEMPLATES_DIR = "web/templates";
    //
    static final String LOGIN_SERVLET_PATH = "/login";
    private static final String ADMIN_SERVLET_PATH = "/admin";
    private static final String LOGOUT_SERVLET_PATH = "/logout";
    private static final int PORT = 8090;
    private static final String STATIC_CONTENT = "web/static_content";

    private static DBService db = new DBServiceHibernateImpl();

    public static DBService getDb(){
        return db;
    }

    public static Server getServer() {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        context.setBaseResource(getWebRootResource());
        context.setWelcomeFiles(new String[]{"index.html"});

        context.addServlet(LoginServlet.class, LOGIN_SERVLET_PATH);
        context.addServlet(LogoutServlet.class, LOGOUT_SERVLET_PATH);
        context.addServlet(AdminServlet.class, ADMIN_SERVLET_PATH);

        // Lastly, the default servlet for root content (always needed, to satisfy servlet spec)
        // It is important that this is last.
        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("dirAllowed", "true");
        context.addServlet(holderPwd, "/");

        Server server = new Server(PORT);
        server.setHandler(context);

        return server;

    }

    private static Resource getWebRootResource() {
        ClassLoader classLoader = WebserverConfiguration.class.getClassLoader();
        URL f = classLoader.getResource(STATIC_CONTENT + "/index.html");
        if (f == null) throw new RuntimeException("Unable to find WebRoot directory.");
        Resource resource;
        URI webRootUri;
        try {
            webRootUri = f.toURI().resolve("./").normalize();
            resource = Resource.newResource(webRootUri);
        } catch (MalformedURLException | URISyntaxException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Unable to find WebRoot directory..");
        }
        log.info("WebRoot is: " + webRootUri);
        return resource;
    }
}
