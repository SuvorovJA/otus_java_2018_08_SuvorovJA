package ru.otus.sua.L11.dbservice;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.SessionImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.sua.L11.dbservice.database.HibernateFactorySessionHolder;
import ru.otus.sua.L11.entity.AddressDataSet;
import ru.otus.sua.L11.entity.PhoneDataSet;
import ru.otus.sua.L11.entity.UberUserDataSet;
import ru.otus.sua.L11.entity.UserDataSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;

@Slf4j
public class DBServiceHibernateImplTest {

    private UserDataSet user1 = new UserDataSet();
    private UserDataSet user2 = new UserDataSet();
    private UserDataSet user3 = new UserDataSet();
    private UberUserDataSet uber1 = new UberUserDataSet();
    private DBServiceHibernateImpl db;

    @After
    public void cleanDb() throws Exception {
        db.close();
    }

    @Before
    public void setUp() throws Exception {

        user1 = new UserDataSet("Test One", 11,
                Arrays.asList(new PhoneDataSet("+5 5555 555 555", user1)),
                new AddressDataSet("pr.Lenina 1", user1));
        user2 = new UserDataSet("Test Two", 22,
                Arrays.asList(new PhoneDataSet("+5 5555 555 550", user2)),
                new AddressDataSet("pr.Lenina 111", user2));
        user3 = new UserDataSet("Test Three", 33,
                Arrays.asList(new PhoneDataSet("+5 5555 555 505", user3)),
                new AddressDataSet("str.Vano 10", user3));
        user1.getPhones().forEach(phoneDataSet -> phoneDataSet.setUser(user1));
        user2.getPhones().forEach(phoneDataSet -> phoneDataSet.setUser(user2));
        user3.getPhones().forEach(phoneDataSet -> phoneDataSet.setUser(user3));
        user1.getAddress().setUser(user1);
        user2.getAddress().setUser(user2);
        user3.getAddress().setUser(user3);

        uber1.setName("Uber U");
        uber1.setAge(100);
        uber1.setArmedBy("pistol");
        uber1.setPhones(Arrays.asList(new PhoneDataSet("000", uber1),
                new PhoneDataSet("345", uber1),
                new PhoneDataSet("999", uber1)));
        uber1.setAddress(new AddressDataSet("USSR", uber1));

        HibernateFactorySessionHolder.createNewSessionFactory();
        db = new DBServiceHibernateImpl();
        log.info(db.getMetaData());

        db.save(user1);
        db.save(user2);
        db.save(user3);
        db.save(uber1);

    }


    @Test
    public void load() throws Exception {
        otbivka("LOAD");
        test(user1, 1);
        test(user2, 2);
        test(user3, 3);

        otbivka("GET NAME FIELD");
        log.info(">>>>>>> origin: " + user3.toString());
        String loadedName3 = db.getName(3, UserDataSet.class);
        log.info(">>>>>>> loaded: " + loadedName3);
        assertEquals(user3.getName(), loadedName3);

        otbivka("DB NON-EXISTENT ID");
        UserDataSet loaded33 = db.load(33, UserDataSet.class);
        log.info(Objects.toString(loaded33, "null"));
        assertNull(loaded33);

        otbivka("SAVED BUT MODIFIED OBJ");
        user1.setId(53);
        log.info(">>>>>>> origin: " + user1.toString());
        UserDataSet loaded53 = db.load(1, UserDataSet.class);
        log.info(">>>>>>> loaded: " + loaded53);
        assertNotEquals(user1, loaded53);

        otbivka("CHILD OBJ");
        log.info(">>>>>>> origin: " + uber1.toString());
        long savedId = uber1.getId();
        UberUserDataSet loadedU = db.load(savedId, UberUserDataSet.class);
        log.info(">>>>>>> loaded: " + loadedU);
        assertEquals(uber1, loadedU);
        otbivka("");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void createTables() throws SQLException {
        db.createTables(UserDataSet.class);
    }

    @Test
    public void getByName() throws Exception {
        otbivka("FIND OBJ BY NAME FIELD");
        log.info(">>>>>>> origin: " + user2.toString());
        UserDataSet loaded = db.getByName("Test Two", UserDataSet.class);
        log.info(">>>>>>> loaded: " + loaded.toString());
        assertEquals(user2, loaded);
    }

    private void test(UserDataSet userDataSet, int id) throws SQLException {
        log.info(">>>>>>> origin: " + userDataSet.toString());
        UserDataSet loaded1 = db.load(id, UserDataSet.class);
        log.info(">>>>>>> loaded: " + loaded1.toString());
        assertEquals(userDataSet, loaded1);
        otbivka("");
    }

    private void otbivka(String msg) {
        log.info("---------------------------------" + msg + "---------------------------------");
    }

    private void showH2Console() {
        Connection connection = ((SessionImpl) HibernateFactorySessionHolder.getSession()).connection();
        try {
            org.h2.tools.Server.startWebServer(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}