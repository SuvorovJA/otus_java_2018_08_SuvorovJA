# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание
ДЗ-16: MessageServer

Cревер из ДЗ-15 разделить на три приложения:
- [ ] MessageServer
- [ ] Frontend
- [ ] DBServer

- [ ] Запускать Frontend и DBServer из MessageServer.

- [ ] Сделать MessageServer сокет-сервером, Frontend и DBServer клиентами.

- [ ] Пересылать сообщения с Frontend на DBService через MessageServer.

- [ ] Запустить приложение с двумя фронтендами (на разных портах)* и двумя датабазными серверами.

* если у вас запуск веб приложения в контейнере, то MessageServer может копировать root.war в контейнеры при старте 
---
- [x] SocketTransferService, модуль используемый во всех трёх приложениях.
  - [x] psvm test
  

##### Материалы

##### Решение

test run of SocketTransferService
``` 
/usr/lib/jvm/java-10-oracle/bin/java ...
01:25:03.968 [main] INFO ru.otus.sua.L16.sts.SocketTransferServiceServer - STS-SERVER: started in server mode
01:25:03.968 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketListener - Socket listener for 'STS-SERVER' started on port: 11121
.
01:25:05.211 [main] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - STS-CLIENT: started in client mode
01:25:05.211 [pool-2-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - STS-CLIENT: Attempt connect to their: 'localhost:11121'
01:25:05.222 [pool-2-thread-1] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=localhost/127.0.0.1,port=11121,localport=36212]' started.
01:25:05.222 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Worker for socket 'Socket[addr=/127.0.0.1,port=36212,localport=11121]' started.
01:25:05.223 [pool-2-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - STS-CLIENT: Succesfull connected to 'localhost:11121'
01:25:05.223 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketListener - STS-SERVER: New socket established: 'ru.otus.sua.L16.sts.SocketWorkerImpl@313f1c24'
.
.
check by client: PingMsg(time=1548872706445, label=FromServer)
check by client: null
.
.
check by server: PingMsg(time=1548872708968, label=FromClient)
check by server: null
.
01:25:11.438 [main] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Socket worker was shutdown.
01:25:11.439 [pool-3-thread-2] ERROR ru.otus.sua.L16.sts.SocketWorkerImpl - receiveMessage(): 'Socket closed'
01:25:11.439 [main] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Socket worker was shutdown.
01:25:11.439 [pool-3-thread-1] ERROR ru.otus.sua.L16.sts.SocketWorkerImpl - sendMessage(): 'null'
01:25:11.439 [pool-4-thread-1] ERROR ru.otus.sua.L16.sts.SocketWorkerImpl - sendMessage(): 'null'
01:25:11.440 [main] INFO ru.otus.sua.L16.sts.SocketWorkerImpl - Socket worker was shutdown.
01:25:11.441 [main] INFO ru.otus.sua.L16.sts.SocketListener - SocketListener was shutdown
01:25:11.441 [pool-1-thread-1] INFO ru.otus.sua.L16.sts.SocketListener - Trouble on bind socket listener: 'STS-SERVER':'11121' caused 'Socket closed'
.
01:25:12.675 [main] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: started in client mode
01:25:12.676 [pool-5-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Attempt connect to their: 'localhost:13121'
01:25:12.676 [pool-5-thread-1] WARN ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Trouble on create client socket to 'localhost:13121' caused 'В соединении отказано (Connection refused)'
01:25:13.677 [pool-5-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Attempt connect to their: 'localhost:13121'
01:25:13.678 [pool-5-thread-1] WARN ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Trouble on create client socket to 'localhost:13121' caused 'В соединении отказано (Connection refused)'
.
01:25:14.679 [pool-5-thread-1] INFO ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Attempt connect to their: 'localhost:13121'
01:25:14.680 [pool-5-thread-1] WARN ru.otus.sua.L16.sts.SocketTransferServiceClient - NEVER-CLIENT: Trouble on create client socket to 'localhost:13121' caused 'В соединении отказано (Connection refused)'
.
.

Process finished with exit code 0

```