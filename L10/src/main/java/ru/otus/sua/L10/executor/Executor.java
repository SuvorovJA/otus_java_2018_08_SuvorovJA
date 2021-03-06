package ru.otus.sua.L10.executor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L10.entity.DataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
@Slf4j
public class Executor {

    private final Connection connection;

    public void execQuery(String query, ResultHandler handler) {
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            log(query, result);
            handler.handle(result);
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }

    public <T extends DataSet> T execQuery(String query, ResultHandlerValued handler) {
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            log(query, result);
            return handler.handle(result);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public long execUpdate(String query, ResultHandler handler) {
        long id = -1;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.getResultSet();
            ResultSet ids = stmt.getGeneratedKeys();
            if (ids.next()) {
                id = ids.getLong(1);
            } else {
                log.error("No returned id");
            }
            log(query, result, id);
            handler.handle(result);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return id;
    }


    public int execUpdateCount(String update) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            log(update, stmt);
            return stmt.getUpdateCount();
        } catch (SQLException e) {
            log.error(e.getMessage());
            return 0;
        }

    }

    Connection getConnection() {
        return connection;
    }

    private void log(String update, Statement stmt) throws SQLException {
        log.info("query: " + update + "\tresult(upd): " + stmt.getUpdateCount());
    }

    private void log(String query, ResultSet result) throws SQLException {
        String res = "";
        if (result.rowUpdated() ||
                result.rowDeleted() ||
                result.rowInserted())
            res = "\tresult(upd/del/ins): " +
                    result.rowUpdated() + "/" +
                    result.rowDeleted() + "/" +
                    result.rowInserted();
        log.info("query: " + query + res);
    }

    private void log(String query, ResultSet result, long id) throws SQLException {
        String res = "";
        if (result != null)
            res = "\tresult(upd/del/ins): " +
                    result.rowUpdated() + "/" +
                    result.rowDeleted() + "/" +
                    result.rowInserted();
        log.info("query: " + query + res + "\treturnedId=" + id);
    }
}
