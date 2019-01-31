package ru.otus.sua.L16;

import ru.otus.sua.L16.backendservice.BackendService;
import ru.otus.sua.L16.backendservice.MessagesystemHandlerForDbService;
import ru.otus.sua.L16.dbservice.DBService;
import ru.otus.sua.L16.entity.AddressDataSet;
import ru.otus.sua.L16.entity.PhoneDataSet;
import ru.otus.sua.L16.entity.UserDataSet;

import java.sql.SQLException;
import java.util.Arrays;

public class StandaloneDB {

    private static BackendService forDbService;

    public static void main(String[] args) throws SQLException {
        // start dbService
        forDbService = new MessagesystemHandlerForDbService();
        forDbService.start();
        populateDb();
    }

    private static void populateDb() throws SQLException {
        DBService db = forDbService.getDbService();
        final UserDataSet user1;
        final UserDataSet user2;
        user1 = createNewUser(
                "Test One", 11,
                "+5 5555 555 555",
                "pr.Lenina 1");
        user2 = createNewUser(
                "Test Two", 22,
                "+5 5555 555 550",
                "pr.Lenina 111");
        db.save(user1);
        db.save(user2);
    }


    private static UserDataSet createNewUser(String userName, int userAge, String userPhone, String userAddress) {
        return new UserDataSet(userName, userAge,
                Arrays.asList(new PhoneDataSet(userPhone)),
                new AddressDataSet(userAddress));
    }


}
