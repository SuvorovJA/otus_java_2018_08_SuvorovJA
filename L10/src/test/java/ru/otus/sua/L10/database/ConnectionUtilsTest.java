package ru.otus.sua.L10.database;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ConnectionUtilsTest {

    private static Logger log = LoggerFactory.getLogger(ConnectionUtilsTest.class);

    @Before
    public void setUp() throws Exception {
        Class.forName("ru.otus.sua.L10.database.DatasourceUtils");
    }

    @Test
    public void getConnection() {
        try {
            assertNotNull(ConnectionUtils.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void closeQuietly() {
        try {
            ConnectionUtils.closeQuietly(ConnectionUtils.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            fail();
        }
    }
}