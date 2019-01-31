package ru.otus.sua.L16.backendservice;

import ru.otus.sua.L16.dbservice.DBService;

public interface BackendService {
    void start();
    DBService getDbService();
}
