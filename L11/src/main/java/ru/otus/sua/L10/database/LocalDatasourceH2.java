package ru.otus.sua.L10.database;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class LocalDatasourceH2 {

    private static final String LOCAL_DATABASE_DRIVER_NAME = "org.h2.Driver";
    //        private static final String LOCAL_SOURCE_URL = "jdbc:h2:~/test";
    private static final String LOCAL_SOURCE_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String LOCAL_SOURCE_LOGIN = "sa";
    private static final String LOCAL_SOURCE_PASSWORD = "sa";

    private static DataSource ds;

    static {
        createDatasource();
        startLocalDatabase();
    }

    private static void startLocalDatabase() {
        try {
            Class.forName(LOCAL_DATABASE_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            System.exit(1);
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static void createDatasource() {
        JdbcDataSource jds = new JdbcDataSource();
        jds.setURL(LOCAL_SOURCE_URL);
        jds.setUser(LOCAL_SOURCE_LOGIN);
        jds.setPassword(LOCAL_SOURCE_PASSWORD);
        ds = jds;
    }

}