package ru.otus.sua.L10.executor;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.sua.L10.database.ConnectionUtils;
import ru.otus.sua.L10.entity.DataSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class DAO implements DBService {

    private static Logger log = LoggerFactory.getLogger(DAO.class);

    private final Connection connection;

    @Override
    public String getMetaData() {
        try {
            return "\n" +
                    "\tConnected to:\t" + connection.getMetaData().getURL() + "\n" +
                    "\tDB name:\t" + connection.getMetaData().getDatabaseProductName() + "\n" +
                    "\tDB version:\t" + connection.getMetaData().getDatabaseProductVersion() + "\n" +
                    "\tDriver:\t\t" + connection.getMetaData().getDriverName();
        } catch (SQLException e) {
            log.error(e.getSQLState());
            return "";
        }
    }

    @Override
    public void createTables(Class clazz) {
        Executor exec = new Executor(getConnection());
        exec.execUpdateCount(SqlStatementBuilder.tableCreation(clazz));
    }

    @Override
    public String getUserName(long id, Class clazz) {
        Executor exec = new Executor(getConnection());
        final String[] name = new String[1];
        exec.execQuery(SqlStatementBuilder.selectionNameById(id, clazz),
                result -> {
                    result.next();
                    name[0] = result.getString("name");
                });
        return name[0];
    }

    @Override
    public <T extends DataSet> void save(T user) {
        Executor exec = new Executor(getConnection());
        long id = exec.execUpdate(SqlStatementBuilder.dataInsertion(user), (resultSet) -> {
        });
        user.setId(id);
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        Executor exec = new Executor(getConnection());
        return exec.execQuery(SqlStatementBuilder.selectionAllById(id, clazz), new ResultHandlerValued() {
            @Override
            public <T extends DataSet> T handle(ResultSet result) throws SQLException {
                result.next();
                T user;
                try {
                    user = (T) clazz.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("Fail obj instantiating");
                }
                for (Field field : clazz.getDeclaredFields()) {
                    try {
                        field.setAccessible(true);
                        field.set(user, result.getObject(field.getName()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Fail obj filling");
                    }
                }
                user.setId(result.getLong("id"));
                if (user.getId() != id) throw new RuntimeException("Fail on obj id");
                return user;
            }
        });
    }

    @Override
    public void close() {
        ConnectionUtils.closeQuietly(connection);
    }


}
