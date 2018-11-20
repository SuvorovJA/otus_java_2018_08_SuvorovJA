package ru.otus.sua.L10.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import ru.otus.sua.L10.database.ConnectionUtils;
import ru.otus.sua.L10.entity.UserDataSet;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Slf4j
public class DBTest {

    private UserDataSet user1 = new UserDataSet();
    private UserDataSet user2 = new UserDataSet();
    private UserDataSet user3 = new UserDataSet();
    private DB db;

    @Before
    public void setUp() throws Exception {
        Class.forName("ru.otus.sua.L10.database.LocalDatasource");

        user1.setAge(11);
        user2.setAge(22);
        user3.setAge(33);
        user1.setName("Test One");
        user2.setName("Test Two");
        user3.setName("Test Three");

        db = new DB(ConnectionUtils.getConnection());
        log.info(db.getMetaData());

        db.createTables(UserDataSet.class);

        db.save(user1);
        db.save(user2);
        db.save(user3);
    }

    @Test
    public void load() {

        otbivka();
        log.info("origin: " + user1.toString());
        log.info("loaded: " + db.load(1, UserDataSet.class).toString());
        assertEquals(user1, db.load(1, UserDataSet.class));
        otbivka();

        log.info("origin: " + user2.toString());
        log.info("loaded: " + db.load(2, UserDataSet.class).toString());
        assertEquals(user2, db.load(2, UserDataSet.class));
        otbivka();

        log.info("origin: " + user3.toString());
        log.info("loaded: " + db.load(3, UserDataSet.class).toString());
        assertEquals(user3, db.load(3, UserDataSet.class));
        otbivka();

        log.info("origin: " + user3.toString());
        log.info("loaded: " + db.getName(3, UserDataSet.class));
        assertEquals(user3.getName(), db.getName(3, UserDataSet.class));
        otbivka();

        log.info(db.getName(33, UserDataSet.class));
        assertNotEquals(user3.getName(), db.getName(33, UserDataSet.class));
        otbivka();

        user1.setId(53);
        log.info("origin: " + user1.toString());
        log.info("loaded: " + db.load(1, UserDataSet.class).toString());
        assertNotEquals(user1, db.load(1, UserDataSet.class));
        otbivka();

        db.close();
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