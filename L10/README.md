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
02:16:19.862 [main] INFO ru.otus.sua.L10.executor.DAOTest - 
	Connected to:	jdbc:h2:mem:test
	DB name:	H2
	DB version:	1.4.197 (2018-03-18)
	Driver:	H2 JDBC Driver
02:16:19.893 [main] INFO ru.otus.sua.L10.executor.Executor - query: CREATE TABLE IF NOT EXISTS USERDATASET (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(255) ,AGE INT(3) );	result(upd): 0
02:16:19.924 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test One','11');	returnedId=1
02:16:19.926 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test Two','22');	returnedId=2
02:16:19.927 [main] INFO ru.otus.sua.L10.executor.Executor - query: INSERT INTO  USERDATASET (NAME,AGE) VALUES ('Test Three','33');	returnedId=3
02:16:19.945 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM UserDataSet WHERE ID = 1
02:16:19.954 [main] INFO ru.otus.sua.L10.executor.DAOTest - UserDataSet(name=Test One, age=11)
02:16:19.955 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM UserDataSet WHERE ID = 1
02:16:19.956 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM UserDataSet WHERE ID = 2
02:16:19.957 [main] INFO ru.otus.sua.L10.executor.DAOTest - UserDataSet(name=Test Two, age=22)
02:16:19.957 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM UserDataSet WHERE ID = 2
02:16:19.959 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM UserDataSet WHERE ID = 3
02:16:19.959 [main] INFO ru.otus.sua.L10.executor.DAOTest - UserDataSet(name=Test Three, age=33)
02:16:19.959 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT * FROM UserDataSet WHERE ID = 3
02:16:19.961 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM UserDataSet WHERE ID=3
02:16:19.962 [main] INFO ru.otus.sua.L10.executor.DAOTest - Test Three
02:16:19.962 [main] INFO ru.otus.sua.L10.executor.Executor - query: SELECT NAME FROM UserDataSet WHERE ID=3


Test ignored.

Test ignored.

Process finished with exit code 0
```

