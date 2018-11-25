package ru.otus.sua.L10.database;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class ConnectionUtils {

    public static Connection getConnection() throws SQLException {
        Connection connection = LocalDatasourceH2.getConnection();
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
