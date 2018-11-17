package ru.otus.sua.L10.database;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatasourceUtils {

    public static final String LOCAL_DATABASE_DRIVER_NAME = "org.h2.Driver";
    public static final String EXTERNAL_DATABASE_DRIVER_NAME = "";
    private static final boolean USE_LOCAL_SOURCE = true;
    private static final String LOCAL_SOURCE_URL = "jdbc:h2:mem:test";
    private static final String LOCAL_SOURCE_LOGIN = "sa";
    private static final String LOCAL_SOURCE_PASSWORD = "sa";
    private static final String EXTERNAL_SOURCE_NAME = "java:comp/env/jdbc/test";
    private static Logger log = LoggerFactory.getLogger(DatasourceUtils.class);

    private static DataSource ds;
    private static InitialContext ctx;

    static {
        setNamingContext();
        createDatasource();
        startLocalDatabase();
        dbInit();
    }

    private static void startLocalDatabase() {
        if (USE_LOCAL_SOURCE) {
            try {
                Class.forName(LOCAL_DATABASE_DRIVER_NAME);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage());
                System.exit(5);
            }
        } else {
            try {
                Class.forName(EXTERNAL_DATABASE_DRIVER_NAME);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage());
                System.exit(6);
            }
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return ds.getConnection();
    }

    private static void dbInit() {
        try {
            Flyway flyway = new Flyway();
            flyway.setDataSource(ds);
            flyway.migrate();
        } catch (FlywayException e) {
            log.error(e.getMessage());
            System.exit(4);
        }
    }

    private static void createDatasource() {
        if (USE_LOCAL_SOURCE) {
            JdbcDataSource jds = new JdbcDataSource();
            jds.setURL(LOCAL_SOURCE_URL);
            jds.setUser(LOCAL_SOURCE_LOGIN);
            jds.setPassword(LOCAL_SOURCE_PASSWORD);
            ds = jds;
        } else {
            try {
                ds = (DataSource) ctx.lookup(EXTERNAL_SOURCE_NAME);
                if (ds == null) throw new SQLException("Not Found DataSource");
            } catch (SQLException | NamingException e) {
                log.error(e.getMessage());
                System.exit(1);
            }
        }
    }

    private static void setNamingContext() {
        if (!USE_LOCAL_SOURCE) {
            try {
                ctx = new InitialContext();
            } catch (NamingException e) {
                log.error(e.getMessage());
                System.exit(3);
            }
        }
    }
}