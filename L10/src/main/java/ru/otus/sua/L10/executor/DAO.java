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

    private static final String INSERT_USER = "INSERT INTO %s (NAME,AGE) VALUES ('%s','%s')";
    private static final String SELECT_USER = "SELECT * FROM %s WHERE ID = %s";
    private static final String SELECT_USER_NAME = "SELECT NAME FROM %s WHERE ID=%s";

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
    public void createTables(Class clazz)  {
        Executor exec = new Executor(getConnection());
        String query = createSQLforTableCreation(clazz);
        exec.execUpdateCount(query);
    }

    @Override
    public String getUserName(long id, Class clazz) {
        Executor exec = new Executor(getConnection());
        final String[] name = new String[1];
        exec.execQuery(String.format(SELECT_USER_NAME, clazz.getSimpleName(), id),
                result -> {
                    result.next();
                    name[0] = result.getString("name");
                });
        return name[0];
    }

    @Override
    public <T extends DataSet> void save(T user)  {
        Executor exec = new Executor(getConnection());
        long id = exec.execUpdate(createSQLforDataInsertion(user), (resultSet) -> {});
        user.setId(id);
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz)  {
        Executor exec = new Executor(getConnection());
        return exec.execQuery(String.format(SELECT_USER, clazz.getSimpleName(), id), new ResultHandlerValued() {
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
                return user;
            }
        });
    }

    @Override
    public void close() {
        ConnectionUtils.closeQuietly(connection);
    }

    private <T extends DataSet> String createSQLforDataInsertion(T user) {
        StringBuilder sb = new StringBuilder("INSERT INTO  ");
        sb.append(user.getClass().getSimpleName().toUpperCase());
        sb.append(" (");
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) continue;
            sb.append(field.getName().toUpperCase());
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(") VALUES (");
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) continue;
            sb.append("'");
            try {
                sb.append(field.get(user));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Err in createSQLforDataInsertion");
            }
            sb.append("',");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(");");
        return sb.toString();
    }


    private String createSQLforTableCreation(Class clazz) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(clazz.getSimpleName());
        sb.append(" (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,");
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) continue;
            ParsedFieldSimple f = new ParsedFieldSimple(field);
            sb.append(f.getName());
            sb.append(accordanceJavaTypeToSqlType(f));
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(");");
        return sb.toString().toUpperCase();
    }

    private String accordanceJavaTypeToSqlType(ParsedFieldSimple f) {
        if (f.isString()) return " VARCHAR(255) ";
        if (f.isInt()) return " INT(3) ";
        throw new RuntimeException("Unsupported Type");
    }
}
