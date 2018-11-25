﻿# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

На основе ДЗ10:
- [x] 1. Оформить решение в виде DBService (interface DBService, class DBServiceImpl, UsersDAO, UsersDataSet, ~Executor~)
- [x] 2. _Не меняя_ интерфейс DBSerivice сделать DBServiceHibernateImpl на Hibernate.
- [x] 3. Добавить в UsersDataSet поля:

адрес (OneToOne) 
```
class AddressDataSet{
    private String street;
}
```

и телефон (OneToMany)
```
class PhoneDataSet{
    private String number;
}
```

Добавить соответствущие датасеты и DAO. 


##### Решение

``` 
/usr/lib/jvm/java-10-oracle/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:/home/lsua/bin/idea-IC-182.4129.33/lib/idea_rt.jar=41137:/home/lsua/bin/idea-IC-182.4129.33/bin -Dfile.encoding=UTF-8 -classpath /home/lsua/bin/idea-IC-182.4129.33/lib/idea_rt.jar:/home/lsua/bin/idea-IC-182.4129.33/plugins/junit/lib/junit-rt.jar:/home/lsua/bin/idea-IC-182.4129.33/plugins/junit/lib/junit5-rt.jar:/home/lsua/DEV/OTUS/java-dev/project/L11/target/test-classes:/home/lsua/DEV/OTUS/java-dev/project/L11/target/classes:/home/lsua/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/home/lsua/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/home/lsua/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/lsua/.m2/repository/org/projectlombok/lombok/1.18.2/lombok-1.18.2.jar:/home/lsua/.m2/repository/junit/junit/4.12/junit-4.12.jar:/home/lsua/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/home/lsua/.m2/repository/javax/xml/bind/jaxb-api/2.3.1/jaxb-api-2.3.1.jar:/home/lsua/.m2/repository/javax/activation/javax.activation-api/1.2.0/javax.activation-api-1.2.0.jar:/home/lsua/.m2/repository/org/hibernate/hibernate-core/5.3.7.Final/hibernate-core-5.3.7.Final.jar:/home/lsua/.m2/repository/org/jboss/logging/jboss-logging/3.3.2.Final/jboss-logging-3.3.2.Final.jar:/home/lsua/.m2/repository/javax/persistence/javax.persistence-api/2.2/javax.persistence-api-2.2.jar:/home/lsua/.m2/repository/org/javassist/javassist/3.23.1-GA/javassist-3.23.1-GA.jar:/home/lsua/.m2/repository/net/bytebuddy/byte-buddy/1.8.17/byte-buddy-1.8.17.jar:/home/lsua/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar:/home/lsua/.m2/repository/org/jboss/spec/javax/transaction/jboss-transaction-api_1.2_spec/1.1.1.Final/jboss-transaction-api_1.2_spec-1.1.1.Final.jar:/home/lsua/.m2/repository/org/jboss/jandex/2.0.5.Final/jandex-2.0.5.Final.jar:/home/lsua/.m2/repository/com/fasterxml/classmate/1.3.4/classmate-1.3.4.jar:/home/lsua/.m2/repository/org/dom4j/dom4j/2.1.1/dom4j-2.1.1.jar:/home/lsua/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.4.Final/hibernate-commons-annotations-5.0.4.Final.jar:/home/lsua/.m2/repository/com/h2database/h2/1.4.197/h2-1.4.197.jar:/home/lsua/.m2/repository/org/apache/commons/commons-collections4/4.2/commons-collections4-4.2.jar com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 -junit4 ru.otus.sua.L11.dbservice.DBServiceHibernateImplTest,load
21:45:08.547 [main] INFO  org.hibernate.Version - HHH000412: Hibernate Core {5.3.7.Final}
21:45:08.551 [main] INFO  org.hibernate.cfg.Environment - HHH000206: hibernate.properties not found
21:45:08.701 [main] INFO  o.h.annotations.common.Version - HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
21:45:08.855 [main] WARN  o.hibernate.orm.connections.pooling - HHH10001002: Using Hibernate built-in connection pool (not for production use!)
21:45:08.864 [main] INFO  o.hibernate.orm.connections.pooling - HHH10001005: using driver [org.h2.Driver] at URL [jdbc:h2:~/test]
21:45:08.865 [main] INFO  o.hibernate.orm.connections.pooling - HHH10001001: Connection properties: {password=sa, user=sa}
21:45:08.865 [main] INFO  o.hibernate.orm.connections.pooling - HHH10001003: Autocommit mode: false
21:45:08.868 [main] INFO  o.h.e.j.c.i.DriverManagerConnectionProviderImpl - HHH000115: Hibernate connection pool size: 20 (min=1)
21:45:09.132 [main] INFO  org.hibernate.dialect.Dialect - HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
Hibernate: drop table address if exists
21:45:10.032 [main] INFO  org.hibernate.orm.connections.access - HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@43984213] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: drop table phones if exists
Hibernate: drop table users if exists
Hibernate: create table address (id bigint generated by default as identity, street varchar(255), user_id bigint not null, primary key (id))
21:45:10.053 [main] INFO  org.hibernate.orm.connections.access - HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@6f798482] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: create table phones (id bigint generated by default as identity, phone varchar(255), user_id bigint not null, primary key (id))
Hibernate: create table users (DTYPE varchar(31) not null, id bigint generated by default as identity, age integer not null, name varchar(255), armedBy varchar(255), primary key (id))
Hibernate: alter table address add constraint UK_7rod8a71yep5vxasb0ms3osbg unique (user_id)
Hibernate: alter table address add constraint FK6i66ijb8twgcqtetl8eeeed6v foreign key (user_id) references users
Hibernate: alter table phones add constraint FKmg6d77tgqfen7n1g763nvsqe3 foreign key (user_id) references users
21:45:10.077 [main] INFO  o.h.t.s.internal.SchemaCreatorImpl - HHH000476: Executing import script 'org.hibernate.tool.schema.internal.exec.ScriptSourceInputNonExistentImpl@76b47204'
21:45:10.171 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - 
	Connected to:	jdbc:h2:~/test
	DB name:	H2
	DB version:	1.4.197 (2018-03-18)
	Driver:		H2 JDBC Driver
Hibernate: insert into users (id, age, name, DTYPE) values (null, ?, ?, 'UserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
Hibernate: insert into users (id, age, name, DTYPE) values (null, ?, ?, 'UserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
Hibernate: insert into users (id, age, name, DTYPE) values (null, ?, ?, 'UserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
Hibernate: insert into users (id, age, name, armedBy, DTYPE) values (null, ?, ?, ?, 'UberUserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
21:45:10.236 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ---------------------------------LOAD---------------------------------
21:45:10.308 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> origin: UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1))
Hibernate: select userdatase0_.id as id2_2_0_, userdatase0_.age as age3_2_0_, userdatase0_.name as name4_2_0_, userdatase0_.armedBy as armedBy5_2_0_, userdatase0_.DTYPE as DTYPE1_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users userdatase0_ left outer join address addressdat1_ on userdatase0_.id=addressdat1_.user_id where userdatase0_.id=?
Hibernate: select phones0_.user_id as user_id3_1_0_, phones0_.id as id1_1_0_, phones0_.id as id1_1_1_, phones0_.phone as phone2_1_1_, phones0_.user_id as user_id3_1_1_ from phones phones0_ where phones0_.user_id=?
21:45:10.328 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> loaded: UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1))
21:45:10.331 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ------------------------------------------------------------------
21:45:10.332 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> origin: UserDataSet(super=DataSet(id=2), name=Test Two, age=22, phones=[PhoneDataSet(super=DataSet(id=2), phone=+5 5555 555 550)], address=AddressDataSet(super=DataSet(id=2), street=pr.Lenina 111))
Hibernate: select userdatase0_.id as id2_2_0_, userdatase0_.age as age3_2_0_, userdatase0_.name as name4_2_0_, userdatase0_.armedBy as armedBy5_2_0_, userdatase0_.DTYPE as DTYPE1_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users userdatase0_ left outer join address addressdat1_ on userdatase0_.id=addressdat1_.user_id where userdatase0_.id=?
Hibernate: select phones0_.user_id as user_id3_1_0_, phones0_.id as id1_1_0_, phones0_.id as id1_1_1_, phones0_.phone as phone2_1_1_, phones0_.user_id as user_id3_1_1_ from phones phones0_ where phones0_.user_id=?
21:45:10.339 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> loaded: UserDataSet(super=DataSet(id=2), name=Test Two, age=22, phones=[PhoneDataSet(super=DataSet(id=2), phone=+5 5555 555 550)], address=AddressDataSet(super=DataSet(id=2), street=pr.Lenina 111))
21:45:10.340 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ------------------------------------------------------------------
21:45:10.340 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> origin: UserDataSet(super=DataSet(id=3), name=Test Three, age=33, phones=[PhoneDataSet(super=DataSet(id=3), phone=+5 5555 555 505)], address=AddressDataSet(super=DataSet(id=3), street=str.Vano 10))
Hibernate: select userdatase0_.id as id2_2_0_, userdatase0_.age as age3_2_0_, userdatase0_.name as name4_2_0_, userdatase0_.armedBy as armedBy5_2_0_, userdatase0_.DTYPE as DTYPE1_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users userdatase0_ left outer join address addressdat1_ on userdatase0_.id=addressdat1_.user_id where userdatase0_.id=?
Hibernate: select phones0_.user_id as user_id3_1_0_, phones0_.id as id1_1_0_, phones0_.id as id1_1_1_, phones0_.phone as phone2_1_1_, phones0_.user_id as user_id3_1_1_ from phones phones0_ where phones0_.user_id=?
21:45:10.348 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> loaded: UserDataSet(super=DataSet(id=3), name=Test Three, age=33, phones=[PhoneDataSet(super=DataSet(id=3), phone=+5 5555 555 505)], address=AddressDataSet(super=DataSet(id=3), street=str.Vano 10))
21:45:10.348 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ------------------------------------------------------------------
21:45:10.348 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ---------------------------------GET NAME FIELD---------------------------------
21:45:10.349 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> origin: UserDataSet(super=DataSet(id=3), name=Test Three, age=33, phones=[PhoneDataSet(super=DataSet(id=3), phone=+5 5555 555 505)], address=AddressDataSet(super=DataSet(id=3), street=str.Vano 10))
Hibernate: select userdatase0_.id as id2_2_0_, userdatase0_.age as age3_2_0_, userdatase0_.name as name4_2_0_, userdatase0_.armedBy as armedBy5_2_0_, userdatase0_.DTYPE as DTYPE1_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users userdatase0_ left outer join address addressdat1_ on userdatase0_.id=addressdat1_.user_id where userdatase0_.id=?
21:45:10.355 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> loaded: Test Three
21:45:10.355 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ---------------------------------DB NON-EXISTENT ID---------------------------------
Hibernate: select userdatase0_.id as id2_2_0_, userdatase0_.age as age3_2_0_, userdatase0_.name as name4_2_0_, userdatase0_.armedBy as armedBy5_2_0_, userdatase0_.DTYPE as DTYPE1_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users userdatase0_ left outer join address addressdat1_ on userdatase0_.id=addressdat1_.user_id where userdatase0_.id=?
21:45:10.356 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - null
21:45:10.357 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ---------------------------------SAVED BUT MODIFIED OBJ---------------------------------
21:45:10.357 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> origin: UserDataSet(super=DataSet(id=53), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1))
Hibernate: select userdatase0_.id as id2_2_0_, userdatase0_.age as age3_2_0_, userdatase0_.name as name4_2_0_, userdatase0_.armedBy as armedBy5_2_0_, userdatase0_.DTYPE as DTYPE1_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users userdatase0_ left outer join address addressdat1_ on userdatase0_.id=addressdat1_.user_id where userdatase0_.id=?
Hibernate: select phones0_.user_id as user_id3_1_0_, phones0_.id as id1_1_0_, phones0_.id as id1_1_1_, phones0_.phone as phone2_1_1_, phones0_.user_id as user_id3_1_1_ from phones phones0_ where phones0_.user_id=?
21:45:10.363 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> loaded: UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1))
21:45:10.363 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ---------------------------------CHILD OBJ---------------------------------
21:45:10.365 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> origin: UberUserDataSet(super=UserDataSet(super=DataSet(id=4), name=Uber U, age=100, phones=[PhoneDataSet(super=DataSet(id=4), phone=000), PhoneDataSet(super=DataSet(id=5), phone=345), PhoneDataSet(super=DataSet(id=6), phone=999)], address=AddressDataSet(super=DataSet(id=4), street=USSR)), armedBy=pistol)
Hibernate: select uberuserda0_.id as id2_2_0_, uberuserda0_.age as age3_2_0_, uberuserda0_.name as name4_2_0_, uberuserda0_.armedBy as armedBy5_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users uberuserda0_ inner join address addressdat1_ on uberuserda0_.id=addressdat1_.user_id where uberuserda0_.id=? and uberuserda0_.DTYPE='UberUserDataSet'
Hibernate: select phones0_.user_id as user_id3_1_0_, phones0_.id as id1_1_0_, phones0_.id as id1_1_1_, phones0_.phone as phone2_1_1_, phones0_.user_id as user_id3_1_1_ from phones phones0_ where phones0_.user_id=?
21:45:10.375 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - >>>>>>> loaded: UberUserDataSet(super=UserDataSet(super=DataSet(id=4), name=Uber U, age=100, phones=[PhoneDataSet(super=DataSet(id=4), phone=000), PhoneDataSet(super=DataSet(id=5), phone=345), PhoneDataSet(super=DataSet(id=6), phone=999)], address=AddressDataSet(super=DataSet(id=4), street=USSR)), armedBy=pistol)
21:45:10.375 [main] INFO  r.o.s.L.d.DBServiceHibernateImplTest - ------------------------------------------------------------------

Process finished with exit code 0

```

