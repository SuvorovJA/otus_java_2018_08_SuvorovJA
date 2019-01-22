package ru.otus.sua.L15.dbservice.database;

import org.hibernate.cfg.Configuration;
import ru.otus.sua.L15.entity.*;

public class HibernateConfiguration {

    public static Configuration getConfig() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(DataSet.class);
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(UberUserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
//        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "sa");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//        configuration.setProperty("hibernate.connection.useSSL", "false");
//        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return configuration;

    }

}
