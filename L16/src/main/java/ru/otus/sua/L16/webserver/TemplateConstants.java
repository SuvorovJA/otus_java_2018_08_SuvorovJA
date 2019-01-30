package ru.otus.sua.L16.webserver;

class TemplateConstants {
    static final String HTML_TEMPLATES_DIR = "WEB-INF/templates";
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
}
