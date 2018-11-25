package ru.otus.sua.L10.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultHandler {
    void handle(ResultSet result) throws SQLException;
}
