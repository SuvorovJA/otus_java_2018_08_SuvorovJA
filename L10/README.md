# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

- [x] Создайте в базе таблицу с полями: 

    •id bigint(20) NOT NULL auto_increment
     
    •name varchar(255)
    
    •age int(3)
    

- [x] Создайте абстрактный класс DataSet. Поместите long id в DataSet.
 
 
- [x] Добавьте класс UserDataSet (с полями, которые соответствуют таблице) унаследуйте его от DataSet.
 
 
- [x] Напишите Executor, который сохраняет наследников DataSet в базу и читает их из базы по id и классу.
 
    `<T extends DataSet> void save(T user){...}`
    
    `<T extends DataSet> T load(long id, Class<T> clazz){...}`


- [x] Проверьте его работу на UserDataSet



##### Решение

``` 
/usr/lib/jvm/java-10-oracle/bin/java ...
18:56:10.312 [main] INFO ru.otus.sua.L10.executor.DBTest - 
	Connected to:	jdbc:h2:mem:test
	DB name:	H2
	DB version:	1.4.197 (2018-03-18)
	Driver:		H2 JDBC Driver
18:56:10.315 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - CREATE TABLE IF NOT EXISTS USERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(255) ,AGE INT(3) );
18:56:10.349 [main] INFO ru.otus.sua.L10.executor.Executor - query: CREATE TABLE IF NOT EXISTS USERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(255) ,AGE INT(3) );	result(upd): 0
18:56:10.350 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - INSERT INTO  USERDATASET (NAME,AGE) VALUES ('%s','%s');
18:56:10.383 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test One','11');	returnedId=1
18:56:10.384 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test Two','22');	returnedId=2
18:56:10.385 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test Three','33');	returnedId=3
18:56:10.386 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
18:56:10.402 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=1;name=Test One;age=11)
18:56:10.420 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:56:10.421 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=1;name=Test One;age=11)
18:56:10.422 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:56:10.422 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
18:56:10.423 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=2;name=Test Two;age=22)
18:56:10.424 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 2
18:56:10.424 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=2;name=Test Two;age=22)
18:56:10.425 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 2
18:56:10.425 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
18:56:10.426 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=3;name=Test Three;age=33)
18:56:10.427 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 3
18:56:10.427 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=3;name=Test Three;age=33)
18:56:10.428 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 3
18:56:10.428 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
18:56:10.429 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=3;name=Test Three;age=33)
18:56:10.431 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=3
18:56:10.432 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: Test Three
18:56:10.432 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=3
18:56:10.432 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
18:56:10.433 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=33
18:56:10.433 [main] WARN ru.otus.sua.L10.executor.Executor - Нет данных
No data is available [2000-197]
18:56:10.434 [main] INFO ru.otus.sua.L10.executor.DBTest - null
18:56:10.434 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=33
18:56:10.434 [main] WARN ru.otus.sua.L10.executor.Executor - Нет данных
No data is available [2000-197]
18:56:10.434 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
18:56:10.435 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=53;name=Test One;age=11)
18:56:10.435 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:56:10.436 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=1;name=Test One;age=11)
18:56:10.436 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:56:10.437 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------

Process finished with exit code 0

```

