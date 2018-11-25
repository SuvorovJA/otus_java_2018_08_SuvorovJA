package ru.otus.sua.L10.executor;

import ru.otus.sua.L10.entity.DataSet;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultHandlerValued {
    <T extends DataSet> T handle(ResultSet result) throws SQLException;
}
