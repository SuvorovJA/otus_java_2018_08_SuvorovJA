# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-15: MessageSystem

- [x] Добавить систему обмена сообщениями в веб сервер из ДЗ-12
  - [x] WAR packaging
  - [x] Tomcat target
  - [x] CDI WELD injection
  
- [x] Организовать структуру пакетов без циклических зависимостей. 

- [ x Пересылать сообщения из вебсокета в DBService и обратно. В ДЗ нужно разбить приложение на 4 части: 
  - [x] клиент (на JS в браузере),
  - [x] WebSocket FrontendService на сервере в котором будет по одному объекту вебсокета на клиента,
  - [x] DBService который будет обращаться к базе и
    - [x] без модификации DBService, прослойка из BackendService
  - [x] MessageSystem, которая передает сообщения от FrontendService в DBService и обратно.
  
##### Материалы

[Using CDI/injection in Tomcat Websocket / Serverendpoint](https://stackoverflow.com/questions/51175990/using-cdi-injection-in-tomcat-websocket-serverendpoint)

##### Решение
deploiment app
```
15:16:45.674 [RMI TCP Connection(14)-127.0.0.1] INFO  o.j.w.environment.servletWeldServlet - WELD-ENV-001008: Initialize Weld using ServletContainerInitializer
15:16:45.692 [RMI TCP Connection(14)-127.0.0.1] INFO  org.jboss.weld.Version - WELD-000900: 3.0.5 (Final)
15:16:45.719 [RMI TCP Connection(14)-127.0.0.1] INFO  org.jboss.weld.Bootstrap - WELD-ENV-000020: Using jandex for bean discovery
15:16:45.851 [RMI TCP Connection(14)-127.0.0.1] INFO  org.jboss.weld.Bootstrap - WELD-000101: Transactional services not available. Injection of @Inject UserTransaction not available. Transactional observers will be invoked synchronously.
15:16:46.052 [RMI TCP Connection(14)-127.0.0.1] INFO  o.j.weld.environment.servletTomcat - WELD-ENV-001100: Tomcat 7+ detected, CDI injection will be available in Servlets, Filters and Listeners.
15:16:46.131 [RMI TCP Connection(14)-127.0.0.1] WARN  org.jboss.weld.Validator - WELD-001469: Method start defined on class ru.otus.sua.L15.webserver.TemplateProcessor is not defined according to the specification. It is annotated with @javax.annotation.PostConstruct but it does not have zero parameters.
15:16:46.215 [RMI TCP Connection(14)-127.0.0.1] INFO  r.o.s.L.webserver.TemplateProcessor - TEMLATE-PROCESSOR STARTED
15:16:47.357 [RMI TCP Connection(14)-127.0.0.1] INFO  r.o.s.L.m.MessageSystemImpl - Map of queues length: '1'
15:16:47.358 [RMI TCP Connection(14)-127.0.0.1] INFO  r.o.s.L.m.MessageSystemImpl - Subscide supplier 'class ru.otus.sua.L15.backendservice.MSSupplierForDbService' for destination 'Frontend_With_Db_Conversation'
15:16:47.358 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Worker 'Frontend_With_Db_Conversation/1' started
15:16:47.378 [RMI TCP Connection(14)-127.0.0.1] INFO  r.o.s.L.webserver.TemplateProcessor - TEMLATE-PROCESSOR CONFIGURED
[2019-01-23 03:19:32,200] Artifact L15:war: Artifact is deployed successfully
[2019-01-23 03:19:32,200] Artifact L15:war: Deploy took 3 228 milliseconds
```
first browser
```
15:19:35.371 [http-nio-8080-exec-40] INFO  r.o.sua.L15.webserver.ServletHelper - New logged in as: admin, to session: 0F5AAEB1C51BDD1E7995F6B273DB2A4D
15:19:38.378 [http-nio-8080-exec-31] INFO  r.o.sua.L15.webserver.ServletHelper - Logged in session: 0F5AAEB1C51BDD1E7995F6B273DB2A4D as: admin
15:19:38.378 [http-nio-8080-exec-31] INFO  r.o.sua.L15.webserver.AdminServlet - Attempt get-access to admin page as: admin 
15:19:38.540 [http-nio-8080-exec-31] INFO  r.o.s.L.webserver.AdminServletAction - In database 0 users
15:19:38.598 [http-nio-8080-exec-37] INFO  r.o.s.L.m.MessageSystemImpl - Subscide consumer 'class ru.otus.sua.L15.frontendservice.WebSocketEndpoint' for destination 'Frontend_With_Db_Conversation'
15:19:38.599 [http-nio-8080-exec-37] INFO  r.o.s.L.f.WebSocketEndpoint - New WS-session opened id: 7
15:19:45.282 [http-nio-8080-exec-34] INFO  r.o.sua.L15.webserver.ServletHelper - Logged in session: 0F5AAEB1C51BDD1E7995F6B273DB2A4D as: admin
15:19:45.282 [http-nio-8080-exec-34] INFO  r.o.sua.L15.webserver.AdminServlet - Attempt post-access to admin page as: admin 
15:19:45.326 [http-nio-8080-exec-34] INFO  r.o.s.L.webserver.AdminServletAction - Created new user: UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))
15:19:45.327 [http-nio-8080-exec-34] INFO  r.o.s.L.webserver.AdminServletAction - In database 1 users
15:19:45.332 [http-nio-8080-exec-33] INFO  r.o.s.L.f.WebSocketEndpoint - WS-session closed id: 7
15:19:45.361 [http-nio-8080-exec-36] INFO  r.o.s.L.m.MessageSystemImpl - Subscide consumer 'class ru.otus.sua.L15.frontendservice.WebSocketEndpoint' for destination 'Frontend_With_Db_Conversation'
15:19:45.362 [http-nio-8080-exec-36] INFO  r.o.s.L.f.WebSocketEndpoint - New WS-session opened id: 8
15:20:14.343 [http-nio-8080-exec-42] INFO  r.o.s.L.f.WebSocketEndpoint - Used MessageSystem: 'ru.otus.sua.L15.messagesystem.MessageSystemImpl@7e7181c3'
15:20:14.343 [http-nio-8080-exec-42] INFO  r.o.s.L.f.WebSocketEndpoint - Received string 'admin' from session id: 8. Route to message system.
15:20:14.343 [http-nio-8080-exec-42] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Destination(name=Frontend_With_Db_Conversation, id=1)'
15:20:14.343 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'FrontendService/WSSID8': 'admin'
15:20:14.344 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: 'f3019114-04fe-41a8-9061-7acd3a12a8ac' to supplier.
15:20:14.344 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Used MessageSystem: 'ru.otus.sua.L15.messagesystem.MessageSystemImpl@7e7181c3' 
15:20:14.344 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Received request for search in DB: 'admin'
15:20:14.362 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Database search result send back: 'NOT FOUND'
15:20:14.363 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Destination(name=Frontend_With_Db_Conversation, id=1)'
15:20:14.363 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'BackendService': 'NOT FOUND'
15:20:14.363 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: 'f3019114-04fe-41a8-9061-7acd3a12a8ac' to consumer.
15:20:14.363 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.f.WebSocketEndpoint - Reseived response message 'NOT FOUND' for session '8'
15:20:19.870 [http-nio-8080-exec-31] INFO  r.o.s.L.f.WebSocketEndpoint - Used MessageSystem: 'ru.otus.sua.L15.messagesystem.MessageSystemImpl@7e7181c3'
15:20:19.870 [http-nio-8080-exec-31] INFO  r.o.s.L.f.WebSocketEndpoint - Received string 'adminadmin' from session id: 8. Route to message system.
15:20:19.870 [http-nio-8080-exec-31] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Destination(name=Frontend_With_Db_Conversation, id=1)'
15:20:19.870 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'FrontendService/WSSID8': 'adminadmin'
15:20:19.870 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '26fbe3a0-73a2-4920-a22e-53bbdfba41da' to supplier.
15:20:19.871 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Used MessageSystem: 'ru.otus.sua.L15.messagesystem.MessageSystemImpl@7e7181c3' 
15:20:19.871 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Received request for search in DB: 'adminadmin'
15:20:19.873 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Database search result send back: 'UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))'
15:20:19.873 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Destination(name=Frontend_With_Db_Conversation, id=1)'
15:20:19.873 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'BackendService': 'UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))'
15:20:19.873 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '26fbe3a0-73a2-4920-a22e-53bbdfba41da' to consumer.
15:20:19.873 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.f.WebSocketEndpoint - Reseived response message 'UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))' for session '8'
```
second browser
```
15:28:34.150 [http-nio-8080-exec-42] INFO  r.o.s.L.webserver.AdminServletAction - Loaded 2 users records
15:28:34.151 [http-nio-8080-exec-42] INFO  r.o.s.L.webserver.AdminServletAction - In database 2 users
15:28:34.156 [http-nio-8080-exec-45] INFO  r.o.s.L.f.WebSocketEndpoint - WS-session closed id: c
15:28:34.167 [http-nio-8080-exec-46] INFO  r.o.s.L.m.MessageSystemImpl - Subscide consumer 'class ru.otus.sua.L15.frontendservice.WebSocketEndpoint' for destination 'Frontend_With_Db_Conversation'
15:28:34.167 [http-nio-8080-exec-46] INFO  r.o.s.L.f.WebSocketEndpoint - New WS-session opened id: d
15:28:40.655 [http-nio-8080-exec-47] INFO  r.o.s.L.f.WebSocketEndpoint - Used MessageSystem: 'ru.otus.sua.L15.messagesystem.MessageSystemImpl@7e7181c3'
15:28:40.656 [http-nio-8080-exec-47] INFO  r.o.s.L.f.WebSocketEndpoint - Received string 'adminadmin' from session id: d. Route to message system.
15:28:40.656 [http-nio-8080-exec-47] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Destination(name=Frontend_With_Db_Conversation, id=1)'
15:28:40.656 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'FrontendService/WSSIDd': 'adminadmin'
15:28:40.656 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '003d2963-fd0e-42c3-b790-c3a23a7288d6' to supplier.
15:28:40.656 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Used MessageSystem: 'ru.otus.sua.L15.messagesystem.MessageSystemImpl@7e7181c3' 
15:28:40.656 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Received request for search in DB: 'adminadmin'
15:28:40.658 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.b.MSSupplierForDbService - Database search result send back: 'UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))'
15:28:40.658 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Destination(name=Frontend_With_Db_Conversation, id=1)'
15:28:40.658 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'BackendService': 'UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))'
15:28:40.659 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '003d2963-fd0e-42c3-b790-c3a23a7288d6' to consumer.
15:28:40.659 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.f.WebSocketEndpoint - Reseived response message 'UserDataSet(super=DataSet(id=1), name=adminadmin, age=0, phones=[PhoneDataSet(super=DataSet(id=1), phone=)], address=AddressDataSet(super=DataSet(id=1), street=))' for session 'd'
 
```