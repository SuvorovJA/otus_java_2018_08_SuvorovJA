package ru.otus.sua.L10.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ExternalDatasource {

    private static final String EXTERNAL_DATABASE_DRIVER_NAME = "";
    private static final String EXTERNAL_SOURCE_NAME = "java:comp/env/jdbc/test";

    private static Logger log = LoggerFactory.getLogger(ExternalDatasource.class);

    private static DataSource ds;
    private static InitialContext ctx;

    static {
        setNamingContext();
        createDatasource();
        startDatabase();
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static void startDatabase() {
        try {
            Class.forName(EXTERNAL_DATABASE_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            System.exit(1);
        }
    }
    private static void createDatasource() {
        try {
            ds = (DataSource) ctx.lookup(EXTERNAL_SOURCE_NAME);
            if (ds == null) throw new SQLException("Not Found DataSource");
        } catch (SQLException | NamingException e) {
            log.error(e.getMessage());
            System.exit(2);
        }
    }

    private static void setNamingContext() {
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            log.error(e.getMessage());
            System.exit(3);
        }
    }
}