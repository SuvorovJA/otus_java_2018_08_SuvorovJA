package ru.otus.sua.L11.dbservice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L11.dbservice.dao.UserDataSetDAO;
import ru.otus.sua.L11.dbservice.database.HibernateFactorySessionHolder;
import ru.otus.sua.L11.entity.DataSet;
import ru.otus.sua.L11.entity.UserDataSet;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Data
@Slf4j
public class DBServiceHibernateImpl implements DBService {

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
            UserDataSet result = (UserDataSet) load(id,clazz);
            return result.getName();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public <T extends DataSet> void save(T entity) throws SQLException {
        entity.setId((Long) (new UserDataSetDAO(entity.getClass())).create(entity));
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        return (T) (new UserDataSetDAO(clazz)).read(id);
    }

    @Override
    public void close() throws Exception {

    }
}
