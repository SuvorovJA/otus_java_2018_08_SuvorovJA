package ru.otus.sua.L10.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    private static Logger log = LoggerFactory.getLogger(ConnectionUtils.class);

    public static Connection getConnection() throws SQLException {
        Connection connection = LocalDatasource.getConnection();
        // Connection connection = ExternalDatasource.getConnection();
        return connection;
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
