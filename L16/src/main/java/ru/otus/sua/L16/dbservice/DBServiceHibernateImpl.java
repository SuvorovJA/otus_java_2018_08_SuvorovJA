package ru.otus.sua.L16.dbservice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.otus.sua.L16.dbservice.dao.UserDataSetDAO;
import ru.otus.sua.L16.dbservice.database.HibernateFactorySessionHolder;
import ru.otus.sua.L16.entity.DataSet;
import ru.otus.sua.L16.entity.UserDataSet;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

@Data
@Slf4j
public class DBServiceHibernateImpl implements DBService {

    private Session session;

    public DBServiceHibernateImpl(){
        session = HibernateFactorySessionHolder.openSession();
    }

    @Override
    public String getMetaData() {
        DatabaseMetaData metaData = HibernateFactorySessionHolder.getMetaData();
        try {
            return "\n" +
                    "\tConnected to:\t" + metaData.getURL() + "\n" +
                    "\tDB name:\t" + metaData.getDatabaseProductName() + "\n" +
                    "\tDB version:\t" + metaData.getDatabaseProductVersion() + "\n" +
                    "\tDriver:\t\t" + metaData.getDriverName();
        } catch (SQLException e) {
            log.error(e.getMessage());
            return "";
        }
    }

    @Override
    public void createTables(Class clazz) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName(long id, Class clazz) throws SQLException {
        if (UserDataSet.class.isAssignableFrom(clazz)) {
            UserDataSet result = (UserDataSet) load(id, clazz);
            return result.getName();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public <T extends DataSet> T getByName(String name, Class clazz) throws SQLException {
        return (T) (new UserDataSetDAO(session,clazz)).findByName(name);
    }

    @Override
    public <T extends DataSet> void save(T entity) throws SQLException {
        (new UserDataSetDAO(session,entity.getClass())).create(entity);
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        return (T) (new UserDataSetDAO(session,clazz)).read(id);
    }

    @Override
    public <T extends DataSet> List<T> loadAll(Class<T> clazz) throws SQLException {
        return  (new UserDataSetDAO(session,clazz)).readAll();
    }

    @Override
    public <T extends DataSet> long count(Class<T> clazz) throws SQLException {
        return  (new UserDataSetDAO(session,clazz)).count();
    }

    @Override
    public void close() throws Exception {
        session.close();
        HibernateFactorySessionHolder.shutdown();
    }


}
