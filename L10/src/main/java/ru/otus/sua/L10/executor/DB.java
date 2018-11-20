package ru.otus.sua.L10.executor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L10.database.ConnectionUtils;
import ru.otus.sua.L10.entity.DataSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Slf4j
public class DB implements DBService {


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
    public String getName(long id, Class clazz) {
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
    public <T extends DataSet> void save(T entity) {
        Executor exec = new Executor(getConnection());
        long id = exec.execUpdate(SqlStatementBuilder.dataInsertion(entity), (resultSet) -> {
        });
        entity.setId(id);
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        Executor exec = new Executor(getConnection());
        return exec.execQuery(SqlStatementBuilder.selectionAllById(id, clazz), new ResultHandlerValued() {
            @Override
            public <T extends DataSet> T handle(ResultSet result) throws SQLException {
                result.next();
                T entity;
                try {
                    entity = (T) clazz.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("Fail obj instantiating");
                }
                for (Field field : clazz.getDeclaredFields()) {
                    try {
                        field.setAccessible(true);
                        field.set(entity, result.getObject(field.getName()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Fail obj filling");
                    }
                }
                entity.setId(result.getLong("id"));
                if (entity.getId() != id) throw new RuntimeException("Fail on obj id");
                return entity;
            }
        });
    }

    @Override
    public void close() {
        ConnectionUtils.closeQuietly(connection);
    }


}
