package ru.otus.sua.L10.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import ru.otus.sua.L10.database.ConnectionUtils;
import ru.otus.sua.L10.entity.UberUserDataSet;
import ru.otus.sua.L10.entity.UserDataSet;

import java.sql.SQLException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Slf4j
public class DBTest {

    private UserDataSet user1 = new UserDataSet();
    private UserDataSet user2 = new UserDataSet();
    private UserDataSet user3 = new UserDataSet();
    private UberUserDataSet uber1 = new UberUserDataSet();
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
        uber1.setName("Uber U");
        uber1.setAge(100);
        uber1.setArmedBy("pistol");


        db = new DB(ConnectionUtils.getConnection());
        log.info(db.getMetaData());

        db.createTables(UserDataSet.class);
        db.createTables(UberUserDataSet.class);

        db.save(user1);
        db.save(user2);
        db.save(user3);
        db.save(uber1);
    }

    @Test
    public void load() {

        otbivka();

        test(user1, 1);
        test(user2, 2);
        test(user3, 3);

        log.info("origin: " + user3.toString());
        String loadedName3 = db.getName(3, UserDataSet.class);
        log.info("loaded: " + loadedName3);
        assertEquals(user3.getName(), loadedName3);
        otbivka();

        UserDataSet loaded33 = db.load(33, UserDataSet.class);
        log.info(Objects.toString(loaded33,"null"));
        assertNotEquals(user3.getName(), loaded33);
        otbivka();

        user1.setId(53);
        log.info("origin: " + user1.toString());
        UserDataSet loaded53 = db.load(1, UserDataSet.class);
        log.info("loaded: " + loaded53);
        assertNotEquals(user1, loaded53);
        otbivka();

        log.info("origin: " + uber1.toString());
        UberUserDataSet loadedU = db.load(1, UberUserDataSet.class);
        log.info("loaded: " + loadedU);
        assertEquals(uber1, loadedU);
        otbivka();


        db.close();
        //showH2Console();
    }

    private void test(UserDataSet userDataSet, int id) {
        log.info("origin: " + userDataSet.toString());
        UserDataSet loaded1 = db.load(id, UserDataSet.class);
        log.info("loaded: " + loaded1.toString());
        assertEquals(userDataSet, loaded1);
        otbivka();
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