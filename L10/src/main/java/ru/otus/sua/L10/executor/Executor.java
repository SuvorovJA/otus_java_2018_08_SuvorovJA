package ru.otus.sua.L10.executor;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.sua.L10.entity.DataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
public class Executor {
    private static Logger log = LoggerFactory.getLogger(Executor.class);
    private final Connection connection;

    public void execQuery(String query, ResultHandler handler) {
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            log(query, result);
            handler.handle(result);
        } catch (SQLException e) {
            log.error(e.getMessage());
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

    public int execUpdate(String query, ResultHandler handler) {
        int id = -1;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.getResultSet();
            ResultSet ids = stmt.getGeneratedKeys();
            if (ids.next()) {
                id = ids.getInt(1);
            } else {
                log.error("No returned id");
                // throw ?
            }
            log(query, result, id);
            handler.handle(result);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return id;
    }


    public int execUpdate(String update)  {
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
        if (result.rowUpdated() || result.rowDeleted()  || result.rowInserted()) res = "\tresult(upd/del/ins): " + result.rowUpdated() + "/" + result.rowDeleted() + "/" + result.rowInserted();
        log.info("query: " + query + res);
    }

    private void log(String query, ResultSet result, int id) throws SQLException {
        String res = "";
        if (result != null) res = "\tresult(upd/del/ins): " + result.rowUpdated() + "/" + result.rowDeleted() + "/" + result.rowInserted();
        log.info("query: " + query + res + "\treturnedId=" + id);
    }
}
