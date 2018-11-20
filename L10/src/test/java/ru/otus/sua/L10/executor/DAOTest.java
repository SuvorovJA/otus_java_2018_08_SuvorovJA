package ru.otus.sua.L10.executor;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.sua.L10.database.ConnectionUtils;
import ru.otus.sua.L10.entity.UserDataSet;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DAOTest {

    private static Logger log = LoggerFactory.getLogger(DAOTest.class);
    private UserDataSet user1 = new UserDataSet();
    private UserDataSet user2 = new UserDataSet();
    private UserDataSet user3 = new UserDataSet();
    private DAO dao;

    @Before
    public void setUp() throws Exception {
        Class.forName("ru.otus.sua.L10.database.LocalDatasource");

        user1.setAge(11);
        user2.setAge(22);
        user3.setAge(33);
        user1.setName("Test One");
        user2.setName("Test Two");
        user3.setName("Test Three");

        dao = new DAO(ConnectionUtils.getConnection());
        log.info(dao.getMetaData());

        dao.createTables(UserDataSet.class);

        dao.save(user1);
        dao.save(user2);
        dao.save(user3);
    }

    @Test
    public void load() {

        otbivka();
        log.info("origin: " + user1.toString());
        log.info("loaded: " + dao.load(1, UserDataSet.class).toString());
        assertEquals(user1, dao.load(1, UserDataSet.class));
        otbivka();

        log.info("origin: " + user2.toString());
        log.info("loaded: " + dao.load(2, UserDataSet.class).toString());
        assertEquals(user2, dao.load(2, UserDataSet.class));
        otbivka();

        log.info("origin: " + user3.toString());
        log.info("loaded: " + dao.load(3, UserDataSet.class).toString());
        assertEquals(user3, dao.load(3, UserDataSet.class));
        otbivka();

        log.info("origin: " + user3.toString());
        log.info("loaded: " + dao.getUserName(3, UserDataSet.class));
        assertEquals(user3.getName(), dao.getUserName(3, UserDataSet.class));
        otbivka();

        log.info(dao.getUserName(33, UserDataSet.class));
        assertNotEquals(user3.getName(), dao.getUserName(33, UserDataSet.class));
        otbivka();

        user1.setId(53);
        log.info("origin: " + user1.toString());
        log.info("loaded: " + dao.load(1, UserDataSet.class).toString());
        assertNotEquals(user1, dao.load(1, UserDataSet.class));
        otbivka();

        dao.close();
        //showH2Console();
    }

    private void otbivka() {
        log.info("---------------------------------");
    }

    private void showH2Console() {
        try {
            org.h2.tools.Server.startWebServer(ConnectionUtils.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}