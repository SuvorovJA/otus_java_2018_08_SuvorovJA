# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание
ДЗ-14: WAR

- [x] Собрать war для приложения из ДЗ-12.

- [x] Создавать  DBService как Spring beans, передавать (inject) их в сервлеты.
  - [x] freemarker templateProcessor
  - [ ] L06 кэш 

- [x] Запустить веб приложение во внешнем веб сервере. 

##### Материалы

##### Решение

cache example
``` 

21:49:31.006 [http-nio-8080-exec-26] INFO  r.o.s.L.webserver.AdminServletAction - Created new user: UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:31.007 [http-nio-8080-exec-26] INFO  r.o.s.L.webserver.AdminServletAction - In database 1 users
21:49:35.757 [http-nio-8080-exec-25] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:35.757 [http-nio-8080-exec-25] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: insert into users (id, age, name, DTYPE) values (null, ?, ?, 'UserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
21:49:35.765 [http-nio-8080-exec-25] INFO  r.o.s.L.webserver.AdminServletAction - Created new user: UserDataSet(super=DataSet(id=2), name=eeeeeeeeee, age=0, phones=[PhoneDataSet(super=DataSet(id=2), phone=)], address=AddressDataSet(super=DataSet(id=2), street=))
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:35.765 [http-nio-8080-exec-25] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:42.064 [http-nio-8080-exec-27] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:42.064 [http-nio-8080-exec-27] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:42.072 [http-nio-8080-exec-27] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 0, miss: 1
21:49:42.072 [http-nio-8080-exec-27] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:42.073 [http-nio-8080-exec-27] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:43.692 [http-nio-8080-exec-22] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:43.692 [http-nio-8080-exec-22] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:43.692 [http-nio-8080-exec-22] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 1, miss: 1
21:49:43.692 [http-nio-8080-exec-22] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:43.693 [http-nio-8080-exec-22] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:51.749 [http-nio-8080-exec-31] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:51.749 [http-nio-8080-exec-31] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:51.751 [http-nio-8080-exec-31] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 1, miss: 2
21:49:51.751 [http-nio-8080-exec-31] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:51.752 [http-nio-8080-exec-31] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:54.095 [http-nio-8080-exec-32] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:54.095 [http-nio-8080-exec-32] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:54.096 [http-nio-8080-exec-32] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 1, miss: 3
21:49:54.097 [http-nio-8080-exec-32] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:54.097 [http-nio-8080-exec-32] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:55.116 [http-nio-8080-exec-33] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:55.116 [http-nio-8080-exec-33] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:55.116 [http-nio-8080-exec-33] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 2, miss: 3
21:49:55.116 [http-nio-8080-exec-33] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:55.117 [http-nio-8080-exec-33] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:56.017 [http-nio-8080-exec-34] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:56.017 [http-nio-8080-exec-34] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:56.017 [http-nio-8080-exec-34] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 3, miss: 3
21:49:56.017 [http-nio-8080-exec-34] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:56.018 [http-nio-8080-exec-34] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:58.820 [http-nio-8080-exec-26] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:58.820 [http-nio-8080-exec-26] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:58.822 [http-nio-8080-exec-26] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 3, miss: 4
21:49:58.822 [http-nio-8080-exec-26] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: eeeeeeeeee for id: 2
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:58.823 [http-nio-8080-exec-26] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:49:59.864 [http-nio-8080-exec-35] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:49:59.865 [http-nio-8080-exec-35] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:49:59.865 [http-nio-8080-exec-35] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 4, miss: 4
21:49:59.865 [http-nio-8080-exec-35] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: eeeeeeeeee for id: 2
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:49:59.866 [http-nio-8080-exec-35] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:50:00.910 [http-nio-8080-exec-36] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:50:00.911 [http-nio-8080-exec-36] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:50:00.912 [http-nio-8080-exec-36] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 4, miss: 5
21:50:00.912 [http-nio-8080-exec-36] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: eeeeeeeeee for id: 2
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:50:00.913 [http-nio-8080-exec-36] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:50:02.739 [http-nio-8080-exec-27] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:50:02.739 [http-nio-8080-exec-27] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:50:02.739 [http-nio-8080-exec-27] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 5, miss: 5
21:50:02.739 [http-nio-8080-exec-27] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: eeeeeeeeee for id: 2
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:50:02.740 [http-nio-8080-exec-27] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:50:14.536 [http-nio-8080-exec-31] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:50:14.537 [http-nio-8080-exec-31] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:50:14.537 [http-nio-8080-exec-31] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 1, hits: 5, miss: 6
21:50:14.538 [http-nio-8080-exec-31] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:50:14.538 [http-nio-8080-exec-31] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:50:15.452 [http-nio-8080-exec-38] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:50:15.453 [http-nio-8080-exec-38] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:50:15.454 [http-nio-8080-exec-38] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 2, hits: 5, miss: 7
21:50:15.454 [http-nio-8080-exec-38] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: eeeeeeeeee for id: 2
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:50:15.455 [http-nio-8080-exec-38] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
21:50:16.364 [http-nio-8080-exec-32] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: F66A771B7DACE77EE4E484B5ABB1EDE2 as: admin
21:50:16.364 [http-nio-8080-exec-32] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
21:50:16.364 [http-nio-8080-exec-32] INFO  r.o.s.L.d.CachedDBServiceHibernateImpl - Cache size: 2, hits: 6, miss: 7
21:50:16.364 [http-nio-8080-exec-32] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: adminadmin for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
21:50:16.365 [http-nio-8080-exec-32] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users

```



'Bean' version log on Tomcat9 
``` 
[2019-01-14 01:16:28,730] Artifact L14:war: Artifact is being deployed, please wait...
Hibernate: drop table address if exists
Hibernate: drop table phones if exists
Hibernate: drop table users if exists
14-Jan-2019 13:16:28.761 WARNING [RMI TCP Connection(24)-127.0.0.1] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesJdbc The web application [ROOT] registered the JDBC driver [org.h2.Driver] but failed to unregister it when the web application was stopped. To prevent a memory leak, the JDBC Driver has been forcibly unregistered.
14-Jan-2019 13:16:29.555 INFO [RMI TCP Connection(24)-127.0.0.1] org.apache.jasper.servlet.TldScanner.scanJars At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
13:16:29.649 [RMI TCP Connection(24)-127.0.0.1] INFO  o.s.web.context.ContextLoader - Root WebApplicationContext: initialization started
13:16:29.837 [RMI TCP Connection(24)-127.0.0.1] INFO  org.hibernate.Version - HHH000412: Hibernate Core {5.3.7.Final}
13:16:29.839 [RMI TCP Connection(24)-127.0.0.1] INFO  org.hibernate.cfg.Environment - HHH000206: hibernate.properties not found
13:16:29.952 [RMI TCP Connection(24)-127.0.0.1] INFO  o.h.annotations.common.Version - HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
13:16:30.042 [RMI TCP Connection(24)-127.0.0.1] WARN  o.hibernate.orm.connections.pooling - HHH10001002: Using Hibernate built-in connection pool (not for production use!)
13:16:30.043 [RMI TCP Connection(24)-127.0.0.1] INFO  o.hibernate.orm.connections.pooling - HHH10001005: using driver [org.h2.Driver] at URL [jdbc:h2:mem:test;DB_CLOSE_DELAY=-1]
13:16:30.043 [RMI TCP Connection(24)-127.0.0.1] INFO  o.hibernate.orm.connections.pooling - HHH10001001: Connection properties: {user=sa, password=sa}
13:16:30.044 [RMI TCP Connection(24)-127.0.0.1] INFO  o.hibernate.orm.connections.pooling - HHH10001003: Autocommit mode: false
13:16:30.046 [RMI TCP Connection(24)-127.0.0.1] INFO  o.h.e.j.c.i.DriverManagerConnectionProviderImpl - HHH000115: Hibernate connection pool size: 20 (min=1)
13:16:30.160 [RMI TCP Connection(24)-127.0.0.1] INFO  org.hibernate.dialect.Dialect - HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
Hibernate: drop table address if exists
13:16:30.874 [RMI TCP Connection(24)-127.0.0.1] INFO  org.hibernate.orm.connections.access - HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@56796bc0] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: drop table phones if exists
Hibernate: drop table users if exists
Hibernate: create table address (id bigint generated by default as identity, street varchar(255), user_id bigint not null, primary key (id))
13:16:30.877 [RMI TCP Connection(24)-127.0.0.1] INFO  org.hibernate.orm.connections.access - HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@267416d0] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: create table phones (id bigint generated by default as identity, phone varchar(255), user_id bigint not null, primary key (id))
Hibernate: create table users (DTYPE varchar(31) not null, id bigint generated by default as identity, age integer not null, name varchar(255), armedBy varchar(255), primary key (id))
Hibernate: alter table address add constraint UK_7rod8a71yep5vxasb0ms3osbg unique (user_id)
Hibernate: alter table address add constraint FK6i66ijb8twgcqtetl8eeeed6v foreign key (user_id) references users
Hibernate: alter table phones add constraint FKmg6d77tgqfen7n1g763nvsqe3 foreign key (user_id) references users
13:16:30.893 [RMI TCP Connection(24)-127.0.0.1] INFO  o.h.t.s.internal.SchemaCreatorImpl - HHH000476: Executing import script 'org.hibernate.tool.schema.internal.exec.ScriptSourceInputNonExistentImpl@44a23c3d'
13:16:31.058 [RMI TCP Connection(24)-127.0.0.1] INFO  o.s.web.context.ContextLoader - Root WebApplicationContext initialized in 1407 ms
[2019-01-14 01:16:31,570] Artifact L14:war: Artifact is deployed successfully
[2019-01-14 01:16:31,570] Artifact L14:war: Deploy took 2 840 milliseconds
13:16:44.417 [http-nio-8080-exec-25] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: anonymous
13:16:47.854 [http-nio-8080-exec-24] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: anonymous
13:16:49.725 [http-nio-8080-exec-29] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: anonymous
13:16:50.670 [http-nio-8080-exec-26] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: anonymous
13:16:50.670 [http-nio-8080-exec-26] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt get-access to admin page as: anonymous 
13:16:51.245 [http-nio-8080-exec-28] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: anonymous
13:16:58.587 [http-nio-8080-exec-31] INFO  r.o.sua.L14.webserver.ServletHelper - New logged in as: admin, to session: EB33107C7BCA3610C8C5F083DD14BC79
13:17:00.651 [http-nio-8080-exec-32] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: admin
13:17:00.652 [http-nio-8080-exec-32] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt get-access to admin page as: admin 
13:17:00.693 [http-nio-8080-exec-32] INFO  o.h.h.i.QueryTranslatorFactoryInitiator - HHH000397: Using ASTQueryTranslatorFactory
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
13:17:00.787 [http-nio-8080-exec-32] INFO  r.o.s.L.webserver.AdminServletAction - In database 0 users
13:17:09.230 [http-nio-8080-exec-30] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: admin
13:17:09.230 [http-nio-8080-exec-30] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: insert into users (id, age, name, DTYPE) values (null, ?, ?, 'UserDataSet')
Hibernate: insert into phones (id, phone, user_id) values (null, ?, ?)
Hibernate: insert into address (id, street, user_id) values (null, ?, ?)
13:17:09.274 [http-nio-8080-exec-30] INFO  r.o.s.L.webserver.AdminServletAction - Created new user: UserDataSet(super=DataSet(id=1), name=uuu, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
13:17:09.275 [http-nio-8080-exec-30] INFO  r.o.s.L.webserver.AdminServletAction - In database 1 users
13:17:11.787 [http-nio-8080-exec-34] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: admin
13:17:11.787 [http-nio-8080-exec-34] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
Hibernate: select userdatase0_.id as id2_2_, userdatase0_.age as age3_2_, userdatase0_.name as name4_2_, userdatase0_.armedBy as armedBy5_2_, userdatase0_.DTYPE as DTYPE1_2_ from users userdatase0_
13:17:11.794 [http-nio-8080-exec-34] INFO  r.o.s.L.webserver.AdminServletAction - Loaded 1 users records
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
13:17:11.795 [http-nio-8080-exec-34] INFO  r.o.s.L.webserver.AdminServletAction - In database 1 users
13:17:22.403 [http-nio-8080-exec-36] INFO  r.o.sua.L14.webserver.ServletHelper - Logged in session: EB33107C7BCA3610C8C5F083DD14BC79 as: admin
13:17:22.404 [http-nio-8080-exec-36] INFO  r.o.sua.L14.webserver.AdminServlet - Attempt post-access to admin page as: admin 
13:17:22.411 [http-nio-8080-exec-36] INFO  r.o.s.L.webserver.AdminServletAction - Found user name: uuu for id: 1
Hibernate: select count(userdatase0_.id) as col_0_0_ from users userdatase0_
13:17:22.412 [http-nio-8080-exec-36] INFO  r.o.s.L.webserver.AdminServletAction - In database 1 users

```