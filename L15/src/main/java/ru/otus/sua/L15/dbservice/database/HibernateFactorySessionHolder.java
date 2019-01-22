package ru.otus.sua.L15.dbservice.database;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * SessionFactory is an extremely expensive process which involves parsing hibernate
 * configuration/mapping properties and creating database connection pool .Creating a
 * database connection pool requires establishing database connections (i.e creating
 * Connection objects) which has overhead due to the time taken to locate the DB server ,
 * establish a communication channel and exchange information to do authentication.
 * <p>
 * So if you create a SessionFactory for every request , it implies that you are not
 * using database connection pool to serve your request .You have to setup a new connection
 * by the above overheaded process for every request instead of just getting the opened
 * connection from the database connection pool.
 */

@Slf4j
public class HibernateFactorySessionHolder {

    private static SessionFactory sessionFactory;

    static {
        createNewSessionFactory();
    }

    public static void createNewSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            Configuration configuration = HibernateConfiguration.getConfig();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        // TODO ERROR o.h.e.j.c.i.DriverManagerConnectionProviderImpl - Connection leak detected: there are 1 unclosed connections upon shutting down pool jdbc:h2:mem:test;DB_CLOSE_DELAY=-1
        sessionFactory.close();
    }

    public static DatabaseMetaData getMetaData()  {
        Connection connection = ((SessionImpl) openSession()).connection();
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return databaseMetaData;
    }

}


