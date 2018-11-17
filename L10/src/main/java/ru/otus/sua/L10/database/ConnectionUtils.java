package ru.otus.sua.L10.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    private static Logger log = LoggerFactory.getLogger(ConnectionUtils.class);

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DatasourceUtils.getConnection();
        log.info(getMetadata(connection));
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

    private static String getMetadata(Connection connection) {
        try {
            return "\n" +
                    "\tConnected to:\t" + connection.getMetaData().getURL() + "\n" +
                    "\tDB name:\t" + connection.getMetaData().getDatabaseProductName() + "\n" +
                    "\tDB version:\t" + connection.getMetaData().getDatabaseProductVersion() + "\n" +
                    "\tDriver:\t" + connection.getMetaData().getDriverName();
        } catch (SQLException e) {
            log.error(e.getSQLState());
            return "";
        }
    }
}
