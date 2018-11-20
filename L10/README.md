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
01:15:44.600 [main] INFO ru.otus.sua.L10.executor.DBTest - 
	Connected to:	jdbc:h2:mem:test
	DB name:	H2
	DB version:	1.4.197 (2018-03-18)
	Driver:		H2 JDBC Driver
01:15:44.624 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - CREATE TABLE IF NOT EXISTS USERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(255) ,AGE INT(3) );
01:15:44.670 [main] INFO ru.otus.sua.L10.executor.Executor - query: CREATE TABLE IF NOT EXISTS USERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(255) ,AGE INT(3) );	result(upd): 0
01:15:44.670 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - CREATE TABLE IF NOT EXISTS UBERUSERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,ARMEDBY VARCHAR(255) ,NAME VARCHAR(255) ,AGE INT(3) );
01:15:44.673 [main] INFO ru.otus.sua.L10.executor.Executor - query: CREATE TABLE IF NOT EXISTS UBERUSERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,ARMEDBY VARCHAR(255) ,NAME VARCHAR(255) ,AGE INT(3) );	result(upd): 0
01:15:44.674 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - INSERT INTO  USERDATASET (name,age) VALUES ('%s','%s');
01:15:44.712 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (name,age) VALUES ('Test One','11');	returnedId=1
01:15:44.714 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (name,age) VALUES ('Test Two','22');	returnedId=2
01:15:44.715 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (name,age) VALUES ('Test Three','33');	returnedId=3
01:15:44.715 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - INSERT INTO  UBERUSERDATASET (armedBy,name,age) VALUES ('%s','%s','%s');
01:15:44.717 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  UBERUSERDATASET (armedBy,name,age) VALUES ('pistol','Uber U','100');	returnedId=1
01:15:44.717 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
01:15:44.734 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=1;name=Test One;age=11)
01:15:44.751 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
01:15:44.752 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=1;name=Test One;age=11)
01:15:44.752 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
01:15:44.752 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=2;name=Test Two;age=22)
01:15:44.753 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 2
01:15:44.754 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=2;name=Test Two;age=22)
01:15:44.754 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
01:15:44.754 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=3;name=Test Three;age=33)
01:15:44.755 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 3
01:15:44.755 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=3;name=Test Three;age=33)
01:15:44.755 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
01:15:44.755 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=3;name=Test Three;age=33)
01:15:44.757 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=3
01:15:44.758 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: Test Three
01:15:44.758 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
01:15:44.759 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 33
01:15:44.759 [main] ERROR ru.otus.sua.L10.executor.Executor - Нет данных
No data is available [2000-197]
01:15:44.759 [main] INFO ru.otus.sua.L10.executor.DBTest - null
01:15:44.759 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
01:15:44.760 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UserDataSet(id=53;name=Test One;age=11)
01:15:44.760 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
01:15:44.761 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UserDataSet(id=1;name=Test One;age=11)
01:15:44.761 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------
01:15:44.788 [main] INFO ru.otus.sua.L10.executor.DBTest - origin: UberUserDataSet(id=1;name=Uber U;age=100;armedBy=pistol)
01:15:44.789 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM UBERUSERDATASET WHERE ID = 1
01:15:44.790 [main] INFO ru.otus.sua.L10.executor.DBTest - loaded: UberUserDataSet(id=1;name=Uber U;age=100;armedBy=pistol)
01:15:44.790 [main] INFO ru.otus.sua.L10.executor.DBTest - ---------------------------------

Process finished with exit code 0

```

