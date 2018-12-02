# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-12: Веб сервер
- [x] Встроить веб сервер в приложение из ДЗ-11.

- [x] Сделать админскую страницу (доступ админу), 
  - [x] login (произвольный)
  - [x] на которой можно добавить пользователя, 
  - [x] получить имя пользователя по id 
  - [x] и получить количество пользователей в базе. 
    - [x] напечатать список пользователей

##### Материалы

[What is difference between ServletContextHandler.setResourceBase and ResourceHandler.setResourceBase when using Jetty embedded container?](https://stackoverflow.com/questions/28418449/what-is-difference-between-servletcontexthandler-setresourcebase-and-resourcehan/28419106#28419106)

[Display static HTML file from resources folder in Jetty Serverlet doGet method](https://stackoverflow.com/questions/34273533/display-static-html-file-from-resources-folder-in-jetty-serverlet-doget-method)

[Freemarker Language Reference](https://freemarker.apache.org/docs/ref.html)

##### Решение

``` 
/usr/lib/jvm/java-10-oracle/bin/java -javaagent:/home/lsua/bin/idea-IU-182.4323.46/lib/idea_rt.jar=39843:/home/lsua/bin/idea-IU-182.4323.46/bin -Dfile.encoding=UTF-8 -classpath /home/lsua/DEV/OTUS/java-dev/project/L12/target/classes:/home/lsua/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/home/lsua/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/home/lsua/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/lsua/.m2/repository/org/apache/commons/commons-collections4/4.2/commons-collections4-4.2.jar:/home/lsua/.m2/repository/javax/xml/bind/jaxb-api/2.3.1/jaxb-api-2.3.1.jar:/home/lsua/.m2/repository/javax/activation/javax.activation-api/1.2.0/javax.activation-api-1.2.0.jar:/home/lsua/.m2/repository/org/hibernate/hibernate-core/5.3.7.Final/hibernate-core-5.3.7.Final.jar:/home/lsua/.m2/repository/org/jboss/logging/jboss-logging/3.3.2.Final/jboss-logging-3.3.2.Final.jar:/home/lsua/.m2/repository/javax/persistence/javax.persistence-api/2.2/javax.persistence-api-2.2.jar:/home/lsua/.m2/repository/org/javassist/javassist/3.23.1-GA/javassist-3.23.1-GA.jar:/home/lsua/.m2/repository/net/bytebuddy/byte-buddy/1.8.17/byte-buddy-1.8.17.jar:/home/lsua/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar:/home/lsua/.m2/repository/org/jboss/spec/javax/transaction/jboss-transaction-api_1.2_spec/1.1.1.Final/jboss-transaction-api_1.2_spec-1.1.1.Final.jar:/home/lsua/.m2/repository/org/jboss/jandex/2.0.5.Final/jandex-2.0.5.Final.jar:/home/lsua/.m2/repository/com/fasterxml/classmate/1.3.4/classmate-1.3.4.jar:/home/lsua/.m2/repository/org/dom4j/dom4j/2.1.1/dom4j-2.1.1.jar:/home/lsua/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.4.Final/hibernate-commons-annotations-5.0.4.Final.jar:/home/lsua/.m2/repository/com/h2database/h2/1.4.197/h2-1.4.197.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-server/9.4.14.v20181114/jetty-server-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-http/9.4.14.v20181114/jetty-http-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-util/9.4.14.v20181114/jetty-util-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-io/9.4.14.v20181114/jetty-io-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-webapp/9.4.14.v20181114/jetty-webapp-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-xml/9.4.14.v20181114/jetty-xml-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-servlet/9.4.14.v20181114/jetty-servlet-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/eclipse/jetty/jetty-security/9.4.14.v20181114/jetty-security-9.4.14.v20181114.jar:/home/lsua/.m2/repository/org/freemarker/freemarker/2.3.28/freemarker-2.3.28.jar:/home/lsua/.m2/repository/javax/servlet/javax.servlet-api/4.0.1/javax.servlet-api-4.0.1.jar ru.otus.sua.L12.Launcher
22:11:58.001 [main] INFO  org.hibernate.Version - HHH000412: Hibernate Core {5.3.7.Final}
22:11:58.006 [main] INFO  org.hibernate.cfg.Environment - HHH000206: hibernate.properties not found
22:11:58.188 [main] INFO  o.h.annotations.common.Version - HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
22:11:58.336 [main] WARN  o.hibernate.orm.connections.pooling - HHH10001002: Using Hibernate built-in connection pool (not for production use!)
22:11:58.346 [main] INFO  o.hibernate.orm.connections.pooling - HHH10001005: using driver [org.h2.Driver] at URL [jdbc:h2:mem:test;DB_CLOSE_DELAY=-1]
22:11:58.347 [main] INFO  o.hibernate.orm.connections.pooling - HHH10001001: Connection properties: {password=sa, user=sa}
22:11:58.347 [main] INFO  o.hibernate.orm.connections.pooling - HHH10001003: Autocommit mode: false
22:11:58.350 [main] INFO  o.h.e.j.c.i.DriverManagerConnectionProviderImpl - HHH000115: Hibernate connection pool size: 20 (min=1)
22:11:58.565 [main] INFO  org.hibernate.dialect.Dialect - HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
Hibernate: drop table address if exists
22:11:59.552 [main] INFO  org.hibernate.orm.connections.access - HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@26bbe604] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: drop table phones if exists
Hibernate: drop table users if exists
Hibernate: create table address (id bigint generated by default as identity, street varchar(255), user_id bigint not null, primary key (id))
22:11:59.555 [main] INFO  org.hibernate.orm.connections.access - HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@1e0a864d] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: create table phones (id bigint generated by default as identity, phone varchar(255), user_id bigint not null, primary key (id))
Hibernate: create table users (DTYPE varchar(31) not null, id bigint generated by default as identity, age integer not null, name varchar(255), armedBy varchar(255), primary key (id))
Hibernate: alter table address add constraint UK_7rod8a71yep5vxasb0ms3osbg unique (user_id)
Hibernate: alter table address add constraint FK6i66ijb8twgcqtetl8eeeed6v foreign key (user_id) references users
Hibernate: alter table phones add constraint FKmg6d77tgqfen7n1g763nvsqe3 foreign key (user_id) references users
22:11:59.589 [main] INFO  o.h.t.s.internal.SchemaCreatorImpl - HHH000476: Executing import script 'org.hibernate.tool.schema.internal.exec.ScriptSourceInputNonExistentImpl@22ebccb9'
22:11:59.694 [main] INFO  org.eclipse.jetty.util.log - Logging initialized @2468ms to org.eclipse.jetty.util.log.Slf4jLog
22:11:59.718 [main] INFO  r.o.s.L.w.WebserverConfiguration - WebRoot is: file:/home/lsua/DEV/OTUS/java-dev/project/L12/target/classes/web/static_content/
22:11:59.756 [main] INFO  org.eclipse.jetty.server.Server - jetty-9.4.14.v20181114; built: 2018-11-14T21:20:31.478Z; git: c4550056e785fb5665914545889f21dc136ad9e6; jvm 10.0.2+13
22:11:59.795 [main] INFO  org.eclipse.jetty.server.session - DefaultSessionIdManager workerName=node0
22:11:59.795 [main] INFO  org.eclipse.jetty.server.session - No SessionScavenger set, using defaults
22:11:59.796 [main] INFO  org.eclipse.jetty.server.session - node0 Scavenging every 660000ms
22:11:59.828 [main] INFO  o.e.j.server.handler.ContextHandler - Started o.e.j.s.ServletContextHandler@e38f0b7{/,file:///home/lsua/DEV/OTUS/java-dev/project/L12/target/classes/web/static_content/,AVAILABLE}
22:11:59.876 [main] INFO  o.e.jetty.server.AbstractConnector - Started ServerConnector@2f382a5e{HTTP/1.1,[http/1.1]}{0.0.0.0:8090}
22:11:59.877 [main] INFO  org.eclipse.jetty.server.Server - Started @2651ms
22:12:03.192 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: anonymous
22:12:04.704 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: anonymous
22:12:04.704 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt get-access to admin page as: anonymous 
22:12:08.949 [qtp1609509548-24] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: anonymous
22:12:14.837 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: user
22:12:18.828 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: user
22:12:18.829 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt get-access to admin page as: user 
22:12:23.797 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: anonymous
22:12:23.809 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: anonymous
22:12:32.723 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: admin
22:12:35.741 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:12:35.741 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt get-access to admin page as: admin 
22:12:35.776 [qtp1609509548-18] INFO  o.h.h.i.QueryTranslatorFactoryInitiator - HHH000397: Using ASTQueryTranslatorFactory
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:12:35.875 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - In database 0 users
22:12:44.644 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:12:44.644 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: select userdatase0_.id as id2_2_, userdatase0_.age as age3_2_, userdatase0_.name as name4_2_, userdatase0_.armedBy as armedBy5_2_, userdatase0_.DTYPE as DTYPE1_2_ from users userdatase0_
22:12:44.654 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - Loaded 0 users records
22:12:44.654 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - No any users
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:12:44.655 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - In database 0 users
22:12:58.470 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:12:58.470 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: select userdatase0_.id as id2_2_0_, userdatase0_.age as age3_2_0_, userdatase0_.name as name4_2_0_, userdatase0_.armedBy as armedBy5_2_0_, userdatase0_.DTYPE as DTYPE1_2_0_, addressdat1_.id as id1_0_1_, addressdat1_.street as street2_0_1_, addressdat1_.user_id as user_id3_0_1_ from users userdatase0_ left outer join address addressdat1_ on userdatase0_.id=addressdat1_.user_id where userdatase0_.id=?
22:12:58.484 [qtp1609509548-22] WARN  r.o.sua.L12.webserver.AdminServlet - Can't find user by id: 2
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:12:58.491 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - In database 0 users
22:13:06.728 [qtp1609509548-28] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:13:06.729 [qtp1609509548-28] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:13:06.734 [qtp1609509548-28] INFO  r.o.sua.L12.webserver.AdminServlet - In database 0 users
22:13:32.989 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:13:32.989 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: insert into users (id, age, name, DTYPE) values (null, ?, ?, 'UserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
22:13:33.034 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - Created new user: UserDataSet(super=DataSet(id=1), name=User 1, age=33, phones=[PhoneDataSet(super=DataSet(id=1), phone=234 567)], address=AddressDataSet(super=DataSet(id=1), street=LongStreet))
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:13:33.072 [qtp1609509548-22] INFO  r.o.sua.L12.webserver.AdminServlet - In database 1 users
22:14:03.259 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:14:03.260 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: insert into users (id, age, name, DTYPE) values (null, ?, ?, 'UserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
22:14:03.271 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - Created new user: UserDataSet(super=DataSet(id=2), name=Second John, age=11, phones=[PhoneDataSet(super=DataSet(id=2), phone=123 345)], address=AddressDataSet(super=DataSet(id=2), street=ShortStreet))
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:14:03.272 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - In database 2 users
22:14:08.688 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:14:08.688 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
22:14:08.693 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.AdminServlet - Found user name: User 1 for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:14:08.694 [qtp1609509548-23] INFO  r.o.sua.L12.webserver.AdminServlet - In database 2 users
22:14:17.526 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:14:17.527 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
22:14:17.532 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - Found user name: Second John for id: 2
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:14:17.533 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.AdminServlet - In database 2 users
22:14:23.760 [qtp1609509548-24] INFO  r.o.sua.L12.webserver.ServletHelper - Logged in session as: admin
22:14:23.760 [qtp1609509548-24] INFO  r.o.sua.L12.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: select userdatase0_.id as id2_2_, userdatase0_.age as age3_2_, userdatase0_.name as name4_2_, userdatase0_.armedBy as armedBy5_2_, userdatase0_.DTYPE as DTYPE1_2_ from users userdatase0_
22:14:23.764 [qtp1609509548-24] INFO  r.o.sua.L12.webserver.AdminServlet - Loaded 2 users records
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
22:14:23.765 [qtp1609509548-24] INFO  r.o.sua.L12.webserver.AdminServlet - In database 2 users
22:14:41.318 [qtp1609509548-18] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: anonymous
22:14:41.327 [qtp1609509548-28] INFO  r.o.sua.L12.webserver.ServletHelper - New logged in as: anonymous

```