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
/usr/lib/jvm/java-10-oracle/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:/home/lsua/bin/idea-IC-182.4129.33/lib/idea_rt.jar=45195:/home/lsua/bin/idea-IC-182.4129.33/bin -Dfile.encoding=UTF-8 -classpath /home/lsua/bin/idea-IC-182.4129.33/lib/idea_rt.jar:/home/lsua/bin/idea-IC-182.4129.33/plugins/junit/lib/junit-rt.jar:/home/lsua/bin/idea-IC-182.4129.33/plugins/junit/lib/junit5-rt.jar:/home/lsua/DEV/OTUS/java-dev/project/L10/target/test-classes:/home/lsua/DEV/OTUS/java-dev/project/L10/target/classes:/home/lsua/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/home/lsua/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/home/lsua/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/lsua/.m2/repository/org/projectlombok/lombok/1.18.2/lombok-1.18.2.jar:/home/lsua/.m2/repository/com/h2database/h2/1.4.197/h2-1.4.197.jar:/home/lsua/.m2/repository/junit/junit/4.12/junit-4.12.jar:/home/lsua/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 -junit4 ru.otus.sua.L10.executor.DAOTest,load
18:23:03.186 [main] INFO ru.otus.sua.L10.executor.DAOTest - 
	Connected to:	jdbc:h2:mem:test
	DB name:	H2
	DB version:	1.4.197 (2018-03-18)
	Driver:		H2 JDBC Driver
18:23:03.189 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - CREATE TABLE IF NOT EXISTS USERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(255) ,AGE INT(3) );
18:23:03.219 [main] INFO ru.otus.sua.L10.executor.Executor - query: CREATE TABLE IF NOT EXISTS USERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(255) ,AGE INT(3) );	result(upd): 0
18:23:03.220 [main] INFO ru.otus.sua.L10.executor.SqlStatementBuilder - INSERT INTO  USERDATASET (NAME,AGE) VALUES ('%s','%s');
18:23:03.253 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test One','11');	returnedId=1
18:23:03.255 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test Two','22');	returnedId=2
18:23:03.256 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test Three','33');	returnedId=3
18:23:03.256 [main] INFO ru.otus.sua.L10.executor.DAOTest - ---------------------------------
18:23:03.273 [main] INFO ru.otus.sua.L10.executor.DAOTest - origin: UserDataSet(id=1;name=Test One;age=11)
18:23:03.290 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:23:03.291 [main] INFO ru.otus.sua.L10.executor.DAOTest - loaded: UserDataSet(id=1;name=Test One;age=11)
18:23:03.291 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:23:03.292 [main] INFO ru.otus.sua.L10.executor.DAOTest - ---------------------------------
18:23:03.292 [main] INFO ru.otus.sua.L10.executor.DAOTest - origin: UserDataSet(id=2;name=Test Two;age=22)
18:23:03.293 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 2
18:23:03.294 [main] INFO ru.otus.sua.L10.executor.DAOTest - loaded: UserDataSet(id=2;name=Test Two;age=22)
18:23:03.295 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 2
18:23:03.295 [main] INFO ru.otus.sua.L10.executor.DAOTest - ---------------------------------
18:23:03.296 [main] INFO ru.otus.sua.L10.executor.DAOTest - origin: UserDataSet(id=3;name=Test Three;age=33)
18:23:03.297 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 3
18:23:03.297 [main] INFO ru.otus.sua.L10.executor.DAOTest - loaded: UserDataSet(id=3;name=Test Three;age=33)
18:23:03.298 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 3
18:23:03.298 [main] INFO ru.otus.sua.L10.executor.DAOTest - ---------------------------------
18:23:03.299 [main] INFO ru.otus.sua.L10.executor.DAOTest - origin: UserDataSet(id=3;name=Test Three;age=33)
18:23:03.301 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=3
18:23:03.302 [main] INFO ru.otus.sua.L10.executor.DAOTest - loaded: Test Three
18:23:03.302 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=3
18:23:03.302 [main] INFO ru.otus.sua.L10.executor.DAOTest - ---------------------------------
18:23:03.303 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=33
18:23:03.304 [main] ERROR ru.otus.sua.L10.executor.Executor - Нет данных
No data is available [2000-197]
18:23:03.304 [main] INFO ru.otus.sua.L10.executor.DAOTest - null
18:23:03.304 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM USERDATASET WHERE ID=33
18:23:03.304 [main] ERROR ru.otus.sua.L10.executor.Executor - Нет данных
No data is available [2000-197]
18:23:03.304 [main] INFO ru.otus.sua.L10.executor.DAOTest - ---------------------------------
18:23:03.305 [main] INFO ru.otus.sua.L10.executor.DAOTest - origin: UserDataSet(id=53;name=Test One;age=11)
18:23:03.305 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:23:03.306 [main] INFO ru.otus.sua.L10.executor.DAOTest - loaded: UserDataSet(id=1;name=Test One;age=11)
18:23:03.307 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM USERDATASET WHERE ID = 1
18:23:03.307 [main] INFO ru.otus.sua.L10.executor.DAOTest - ---------------------------------

Process finished with exit code 0

```

