# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание
ДЗ-16: MessageServer

Cревер из ДЗ-15 разделить на три приложения:
- [x] MessageServer
- [x] Frontend
- [x] DBServer
---
- [x] Запускать Frontend и DBServer из MessageServer. ( если у вас запуск веб приложения в контейнере, то MessageServer может копировать root.war в контейнеры при старте) 
- [x] Сделать MessageServer сокет-сервером, Frontend и DBServer клиентами.
- [x] Пересылать сообщения с Frontend на DBService через MessageServer.
---
- [ ] ~Запустить приложение с двумя фронтендами (на разных портах)* и двумя датабазными серверами.~
---
- [x] SocketTransferService, модуль используемый во всех трёх приложениях.
  - [x] psvm test
  

##### Материалы

##### Решение

сборка
```
    cd ../L16-SocketTransferService/
    mvn install
    cd L16
    mvn package
    cd ../L16-StandaloneDbService/
    mvn package
    cd ../L16-StandaloneMessageService/
    mvn package
  
    /usr/lib/jvm/java-10-oracle/bin/java -jar target/StandaloneMessageService-2018-08.jar

```

запуск, два посковых запроса
``` 
 $ /usr/lib/jvm/java-10-oracle/bin/java -jar target/StandaloneMessageService-2018-08.jar
00:35:16.480 [main] INFO  r.o.s.L.s.SocketTransferServiceServer - STS-for-Front-interact: started in server mode
00:35:16.484 [main] INFO  r.o.s.L.s.SocketTransferServiceServer - STS-for-DB-interact: started in server mode
00:35:16.485 [main] INFO  r.o.s.L.m.MessageSystemImpl - Register new destination: 'Frontend_With_Db_Conversation'. Map of queues length: '1'
00:35:16.486 [pool-3-thread-1] INFO  ru.otus.sua.L16.sts.SocketListener - Socket listener for 'STS-for-Front-interact' started on port: 10001
00:35:16.486 [pool-4-thread-1] INFO  ru.otus.sua.L16.sts.SocketListener - Socket listener for 'STS-for-DB-interact' started on port: 10002
00:35:16.502 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - New Worker 'Frontend_With_Db_Conversation/1' started
00:35:16.504 [main] INFO  r.o.s.L.m.MessageSystemImpl - Subscribe consumer 'class ru.otus.sua.L16.StandaloneMS$$Lambda$31/215219944' for destination 'Frontend_With_Db_Conversation'
00:35:16.505 [main] INFO  r.o.s.L.m.MessageSystemImpl - Subscribe supplier 'class ru.otus.sua.L16.StandaloneMS$$Lambda$32/1043208434' for destination 'Frontend_With_Db_Conversation'
00:35:16.507 [main] INFO  ru.otus.sua.L16.sts.CallbackHandler - Started new worker for polling 'ru.otus.sua.L16.sts.SocketTransferServiceServer@5bd03f44' in period '900' and callback to 'ru.otus.sua.L16.StandaloneMS$$Lambda$33/1661081225@29626d54'
00:35:16.508 [main] INFO  ru.otus.sua.L16.sts.CallbackHandler - Started new worker for polling 'ru.otus.sua.L16.sts.SocketTransferServiceServer@6e4784bc' in period '900' and callback to 'ru.otus.sua.L16.StandaloneMS$$Lambda$34/1882554559@34b7ac2f'
00:35:16.520 [main] INFO  ru.otus.sua.L16.StandaloneMS - Success copy frontend war-file
00:35:16.521 [main] INFO  ru.otus.sua.L16.StandaloneMS - Success run backend process
00:35:16.522 [main] INFO  ru.otus.sua.L16.StandaloneMS - Success run tomcat process
PID: 10051
PID: 10052
00:35:23.899 [pool-4-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=/127.0.0.1,port=55432,localport=10002]' started.
00:35:23.900 [pool-4-thread-1] INFO  ru.otus.sua.L16.sts.SocketListener - STS-for-DB-interact: New socket established: 'ru.otus.sua.L16.sts.SocketWorkerImpl@6f36df96'
00:35:39.694 [pool-3-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=/127.0.0.1,port=52576,localport=10001]' started.
00:35:39.694 [pool-3-thread-1] INFO  ru.otus.sua.L16.sts.SocketListener - STS-for-Front-interact: New socket established: 'ru.otus.sua.L16.sts.SocketWorkerImpl@69a1c905'

00:35:46.767 [pool-8-thread-2] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=/127.0.0.1,port=52576,localport=10001]': '{"destination":"Frontend_With_Db_Conversation","payload":"oiiiiiiiiiiiiiiiiiiiiiiiii","sender":"FrontendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:46.813 [pool-5-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=oiiiiiiiiiiiiiiiiiiiiiiiii, sender=FrontendService, dialogUid=60f1caf5-86ff-4891-87e1-4979300cddb0, isSupplier=false)'
00:35:46.814 [pool-5-thread-1] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Frontend_With_Db_Conversation'
00:35:46.814 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'FrontendService': 'oiiiiiiiiiiiiiiiiiiiiiiiii'
00:35:46.814 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '60f1caf5-86ff-4891-87e1-4979300cddb0' to supplier.
00:35:46.825 [pool-7-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=/127.0.0.1,port=55432,localport=10002]': '{"destination":"Frontend_With_Db_Conversation","payload":"oiiiiiiiiiiiiiiiiiiiiiiiii","sender":"FrontendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'

00:35:47.005 [pool-7-thread-2] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=/127.0.0.1,port=55432,localport=10002]': '{"destination":"Frontend_With_Db_Conversation","payload":"NOT FOUND","sender":"BackendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:47.006 [pool-6-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=NOT FOUND, sender=BackendService, dialogUid=60f1caf5-86ff-4891-87e1-4979300cddb0, isSupplier=true)'
00:35:47.007 [pool-6-thread-1] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Frontend_With_Db_Conversation'
00:35:47.007 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'BackendService': 'NOT FOUND'
00:35:47.007 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '60f1caf5-86ff-4891-87e1-4979300cddb0' to consumer.
00:35:47.008 [pool-8-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=/127.0.0.1,port=52576,localport=10001]': '{"destination":"Frontend_With_Db_Conversation","payload":"NOT FOUND","sender":"BackendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'

00:35:58.766 [pool-8-thread-2] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=/127.0.0.1,port=52576,localport=10001]': '{"destination":"Frontend_With_Db_Conversation","payload":"Test One","sender":"FrontendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:58.768 [pool-5-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=Test One, sender=FrontendService, dialogUid=4021f0e7-febb-4a94-b859-fbd1bcdc7caa, isSupplier=false)'
00:35:58.768 [pool-5-thread-1] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Frontend_With_Db_Conversation'
00:35:58.768 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'FrontendService': 'Test One'
00:35:58.768 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '4021f0e7-febb-4a94-b859-fbd1bcdc7caa' to supplier.
00:35:58.769 [pool-7-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=/127.0.0.1,port=55432,localport=10002]': '{"destination":"Frontend_With_Db_Conversation","payload":"Test One","sender":"FrontendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'

00:35:58.775 [pool-7-thread-2] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=/127.0.0.1,port=55432,localport=10002]': '{"destination":"Frontend_With_Db_Conversation","payload":"UserDataSet(super\u003dDataSet(id\u003d1), name\u003dTest One, age\u003d11, phones\u003d[PhoneDataSet(super\u003dDataSet(id\u003d1), phone\u003d+5 5555 555 555)], address\u003dAddressDataSet(super\u003dDataSet(id\u003d1), street\u003dpr.Lenina 1))","sender":"BackendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:58.777 [pool-6-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1)), sender=BackendService, dialogUid=4021f0e7-febb-4a94-b859-fbd1bcdc7caa, isSupplier=true)'
00:35:58.777 [pool-6-thread-1] INFO  r.o.s.L.m.MessageSystemImpl - Attempt to put message to Destination: 'Frontend_With_Db_Conversation'
00:35:58.777 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Traffic occured from 'BackendService': 'UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1))'
00:35:58.778 [Frontend_With_Db_Conversation/1] INFO  r.o.s.L.m.MessageSystemImpl - Route message for dialog: '4021f0e7-febb-4a94-b859-fbd1bcdc7caa' to consumer.
00:35:58.779 [pool-8-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=/127.0.0.1,port=52576,localport=10001]': '{"destination":"Frontend_With_Db_Conversation","payload":"UserDataSet(super\u003dDataSet(id\u003d1), name\u003dTest One, age\u003d11, phones\u003d[PhoneDataSet(super\u003dDataSet(id\u003d1), phone\u003d+5 5555 555 555)], address\u003dAddressDataSet(super\u003dDataSet(id\u003d1), street\u003dpr.Lenina 1))","sender":"BackendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'


```

log StandaloneDB
``` 
396  [main] INFO  org.hibernate.Version - HHH000412: Hibernate Core {[WORKING]}

2193 [main] INFO  r.o.s.L.s.SocketTransferServiceClient - Backend: started in client mode
2196 [pool-2-thread-1] INFO  r.o.s.L.s.SocketTransferServiceClient - Backend: Attempt connect to their: 'localhost:10002'
2203 [main] INFO  ru.otus.sua.L16.sts.CallbackHandler - Started new worker for polling 'ru.otus.sua.L16.sts.SocketTransferServiceClient@1d207fad' in period '500' and callback to 'ru.otus.sua.L16.backendservice.MessagesystemHandlerForDbService$$Lambda$33/1456265041@72ed9aad'
2233 [pool-2-thread-1] INFO  r.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=localhost/127.0.0.1,port=10002,localport=55432]' started.
2233 [pool-2-thread-1] INFO  r.o.s.L.s.SocketTransferServiceClient - Backend: Succesfull connected to 'localhost:10002'

25164 [pool-4-thread-2] INFO  r.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=localhost/127.0.0.1,port=10002,localport=55432]': '{"destination":"Frontend_With_Db_Conversation","payload":"oiiiiiiiiiiiiiiiiiiiiiiiii","sender":"FrontendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'
25198 [pool-3-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=oiiiiiiiiiiiiiiiiiiiiiiiii, sender=FrontendService, dialogUid=60f1caf5-86ff-4891-87e1-4979300cddb0, isSupplier=false)'
25198 [pool-3-thread-1] INFO  r.o.s.L.b.MessagesystemHandlerForDbService - Used MessageSystem: 'ru.otus.sua.L16.sts.SocketTransferServiceClient@1d207fad' 
25198 [pool-3-thread-1] INFO  r.o.s.L.b.MessagesystemHandlerForDbService - Received request for search in DB: 'oiiiiiiiiiiiiiiiiiiiiiiiii'
25335 [pool-3-thread-1] INFO  r.o.s.L.b.MessagesystemHandlerForDbService - Database search result send back: 'NOT FOUND'
25343 [pool-4-thread-1] INFO  r.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=localhost/127.0.0.1,port=10002,localport=55432]': '{"destination":"Frontend_With_Db_Conversation","payload":"NOT FOUND","sender":"BackendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'

37109 [pool-4-thread-2] INFO  r.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=localhost/127.0.0.1,port=10002,localport=55432]': '{"destination":"Frontend_With_Db_Conversation","payload":"Test One","sender":"FrontendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'
37110 [pool-3-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=Test One, sender=FrontendService, dialogUid=4021f0e7-febb-4a94-b859-fbd1bcdc7caa, isSupplier=false)'
37110 [pool-3-thread-1] INFO  r.o.s.L.b.MessagesystemHandlerForDbService - Used MessageSystem: 'ru.otus.sua.L16.sts.SocketTransferServiceClient@1d207fad' 
37110 [pool-3-thread-1] INFO  r.o.s.L.b.MessagesystemHandlerForDbService - Received request for search in DB: 'Test One'
37113 [pool-3-thread-1] INFO  r.o.s.L.b.MessagesystemHandlerForDbService - Database search result send back: 'UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1))'
37114 [pool-4-thread-1] INFO  r.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=localhost/127.0.0.1,port=10002,localport=55432]': '{"destination":"Frontend_With_Db_Conversation","payload":"UserDataSet(super\u003dDataSet(id\u003d1), name\u003dTest One, age\u003d11, phones\u003d[PhoneDataSet(super\u003dDataSet(id\u003d1), phone\u003d+5 5555 555 555)], address\u003dAddressDataSet(super\u003dDataSet(id\u003d1), street\u003dpr.Lenina 1))","sender":"BackendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'

```

log Tomcat Frontend
``` 
01-Feb-2019 00:35:22.695 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deploying web application archive [/home/lsua/bin/tomcat-9.0.12/webapps/ROOT.war]

SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/home/lsua/bin/tomcat-9.0.12/webapps/ROOT/WEB-INF/lib/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/home/lsua/bin/tomcat-9.0.12/webapps/ROOT/WEB-INF/lib/SocketTransferService-2018-08.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
00:35:24.242 [main] INFO  o.j.w.environment.servletWeldServlet - WELD-ENV-001008: Initialize Weld using ServletContainerInitializer
00:35:24.264 [main] INFO  org.jboss.weld.Version - WELD-000900: 3.0.5 (Final)
00:35:24.578 [main] INFO  org.jboss.weld.Bootstrap - WELD-000101: Transactional services not available. Injection of @Inject UserTransaction not available. Transactional observers will be invoked synchronously.
00:35:24.857 [main] INFO  o.j.weld.environment.servletTomcat - WELD-ENV-001100: Tomcat 7+ detected, CDI injection will be available in Servlets, Filters and Listeners.
00:35:24.967 [main] WARN  org.jboss.weld.Validator - WELD-001469: Method start defined on class ru.otus.sua.L16.webserver.TemplateProcessor is not defined according to the specification. It is annotated with @javax.annotation.PostConstruct but it does not have zero parameters.

00:35:25.118 [main] INFO  r.o.s.L.webserver.TemplateProcessor - TEMLATE-PROCESSOR STARTED
00:35:25.156 [main] INFO  r.o.s.L.webserver.TemplateProcessor - TEMLATE-PROCESSOR CONFIGURED

01-Feb-2019 00:35:30.865 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
01-Feb-2019 00:35:30.883 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["ajp-nio-8009"]
01-Feb-2019 00:35:30.940 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in 8311 ms

00:35:37.872 [http-nio-8080-exec-1] INFO  r.o.sua.L16.webserver.ServletHelper - New logged in as: admin, to session: BC01D3313EF9BB4FD9815CDC62F83C72
00:35:39.549 [http-nio-8080-exec-2] INFO  r.o.sua.L16.webserver.ServletHelper - Logged in session: BC01D3313EF9BB4FD9815CDC62F83C72 as: admin
00:35:39.549 [http-nio-8080-exec-2] INFO  r.o.sua.L16.webserver.AdminServlet - Attempt get-access to admin page as: admin 
00:35:39.687 [http-nio-8080-exec-4] INFO  r.o.s.L.s.SocketTransferServiceClient - Frontend: started in client mode
00:35:39.687 [pool-1-thread-1] INFO  r.o.s.L.s.SocketTransferServiceClient - Frontend: Attempt connect to their: 'localhost:10001'
00:35:39.695 [http-nio-8080-exec-4] INFO  ru.otus.sua.L16.sts.CallbackHandler - Started new worker for polling 'ru.otus.sua.L16.sts.SocketTransferServiceClient@244783e6' in period '500' and callback to 'ru.otus.sua.L16.frontendservice.WebSocketEndpoint$$Lambda$117/1447631306@3a5e49ed'
00:35:39.712 [http-nio-8080-exec-4] INFO  r.o.s.L.f.WebSocketEndpoint - New WS-session opened id: 0
00:35:39.721 [pool-1-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=localhost/127.0.0.1,port=10001,localport=52576]' started.
00:35:39.721 [pool-1-thread-1] INFO  r.o.s.L.s.SocketTransferServiceClient - Frontend: Succesfull connected to 'localhost:10001'

00:35:46.718 [http-nio-8080-exec-5] INFO  r.o.s.L.f.WebSocketEndpoint - Used MessageSystem: 'ru.otus.sua.L16.sts.SocketTransferServiceClient@244783e6'
00:35:46.718 [http-nio-8080-exec-5] INFO  r.o.s.L.f.WebSocketEndpoint - Received string 'oiiiiiiiiiiiiiiiiiiiiiiiii' from session id: 0. Route to message system.
00:35:46.766 [pool-3-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=localhost/127.0.0.1,port=10001,localport=52576]': '{"destination":"Frontend_With_Db_Conversation","payload":"oiiiiiiiiiiiiiiiiiiiiiiiii","sender":"FrontendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:47.009 [pool-3-thread-2] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=localhost/127.0.0.1,port=10001,localport=52576]': '{"destination":"Frontend_With_Db_Conversation","payload":"NOT FOUND","sender":"BackendService","dialogUid":"60f1caf5-86ff-4891-87e1-4979300cddb0","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:47.046 [pool-2-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=NOT FOUND, sender=BackendService, dialogUid=60f1caf5-86ff-4891-87e1-4979300cddb0, isSupplier=true)'
00:35:47.046 [pool-2-thread-1] INFO  r.o.s.L.f.WebSocketEndpoint - Reseived response message 'NOT FOUND' for session '0'

00:35:58.764 [http-nio-8080-exec-7] INFO  r.o.s.L.f.WebSocketEndpoint - Used MessageSystem: 'ru.otus.sua.L16.sts.SocketTransferServiceClient@244783e6'
00:35:58.765 [http-nio-8080-exec-7] INFO  r.o.s.L.f.WebSocketEndpoint - Received string 'Test One' from session id: 0. Route to message system.
00:35:58.766 [pool-3-thread-1] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Send to socket 'Socket[addr=localhost/127.0.0.1,port=10001,localport=52576]': '{"destination":"Frontend_With_Db_Conversation","payload":"Test One","sender":"FrontendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":false,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:58.780 [pool-3-thread-2] INFO  ru.otus.sua.L16.sts.SocketWorkerImpl - Receive from socket 'Socket[addr=localhost/127.0.0.1,port=10001,localport=52576]': '{"destination":"Frontend_With_Db_Conversation","payload":"UserDataSet(super\u003dDataSet(id\u003d1), name\u003dTest One, age\u003d11, phones\u003d[PhoneDataSet(super\u003dDataSet(id\u003d1), phone\u003d+5 5555 555 555)], address\u003dAddressDataSet(super\u003dDataSet(id\u003d1), street\u003dpr.Lenina 1))","sender":"BackendService","dialogUid":"4021f0e7-febb-4a94-b859-fbd1bcdc7caa","isSupplier":true,"className":"ru.otus.sua.L16.sts.entities.Message"}'
00:35:58.782 [pool-2-thread-1] INFO  ru.otus.sua.L16.sts.CallbackHandler - traffic: 'Message(destination=Frontend_With_Db_Conversation, payload=UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1)), sender=BackendService, dialogUid=4021f0e7-febb-4a94-b859-fbd1bcdc7caa, isSupplier=true)'
00:35:58.782 [pool-2-thread-1] INFO  r.o.s.L.f.WebSocketEndpoint - Reseived response message 'UserDataSet(super=DataSet(id=1), name=Test One, age=11, phones=[PhoneDataSet(super=DataSet(id=1), phone=+5 5555 555 555)], address=AddressDataSet(super=DataSet(id=1), street=pr.Lenina 1))' for session '0'

```

test run of SocketTransferService
``` 
/usr/lib/jvm/java-10-oracle/bin/java ...
14:47:00.505 [main] INFO ru.otus.sua.L16.sts.SocketTransferServiceServer - STS-SERVER: started in server mode
14:47:00.505 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketListener - Socket listener for 'STS-SERVER' started on port: 11121
.
14:47:01.749 [main] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - STS-CLIENT: started in client mode
14:47:01.749 [pool-2-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - STS-CLIENT: Attempt connect to their: 'localhost:11121'
14:47:01.758 [pool-2-thread-1] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=localhost/127.0.0.1,port=11121,localport=43294]' started.
14:47:01.759 [pool-2-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - STS-CLIENT: Succesfull connected to 'localhost:11121'
14:47:01.759 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=/127.0.0.1,port=43294,localport=11121]' started.
14:47:01.759 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketListener - STS-SERVER: New socket established: 'ru.otus.sua.L16.sts.SocketWorkerImpl@68f8d5b4'
.
.
check by client: PingMsg(time=1548920822984, label=FromServer)
check by client: null
.
.
check by server: PingMsg(time=1548920825454, label=FromClient)
check by server: null
.
.
by callback: PingMsg(time=1548920829162, label=Check callbacking on arrived message FromClient 0)
by callback: PingMsg(time=1548920829162, label=Check callbacking on arrived message FromClient 1)
by callback: PingMsg(time=1548920829162, label=Check callbacking on arrived message FromClient 2)
by callback: PingMsg(time=1548920829162, label=Check callbacking on arrived message FromClient 3)
by callback: PingMsg(time=1548920829162, label=Check callbacking on arrived message FromClient 4)
.
.
.
.
.
closing:
14:47:15.333 [main] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Socket worker was shutdown.
14:47:15.334 [main] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Socket worker was shutdown.
14:47:15.334 [pool-3-thread-1] ERROR ru.otus.sua.L16.sts.SocketWorkerImpl - sendMessage(): 'null'
14:47:15.334 [pool-4-thread-1] ERROR ru.otus.sua.L16.sts.SocketWorkerImpl - sendMessage(): 'null'
14:47:15.334 [pool-3-thread-2] ERROR ru.otus.sua.L16.sts.SocketWorkerImpl - receiveMessage(): 'Socket closed'
14:47:15.335 [main] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Socket worker was shutdown.
14:47:15.335 [main] INFO ru.otus.sua.L16.sts.SocketListener - SocketListener was shutdown
14:47:15.336 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketListener - Trouble on bind socket listener: 'STS-SERVER':'11121' caused 'Socket closed'
.
14:47:16.571 [main] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: started in client mode
14:47:16.571 [pool-6-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Attempt connect to their: 'localhost:13121'
14:47:16.572 [pool-6-thread-1] WARN ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Trouble on create client socket to 'localhost:13121' caused 'В соединении отказано (Connection refused)'
14:47:17.573 [pool-6-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Attempt connect to their: 'localhost:13121'
14:47:17.575 [pool-6-thread-1] WARN ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Trouble on create client socket to 'localhost:13121' caused 'В соединении отказано (Connection refused)'
.
14:47:18.575 [pool-6-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Attempt connect to their: 'localhost:13121'
14:47:18.576 [pool-6-thread-1] WARN ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Trouble on create client socket to 'localhost:13121' caused 'В соединении отказано (Connection refused)'
.
.

```