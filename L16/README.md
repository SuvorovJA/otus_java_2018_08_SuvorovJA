# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание
ДЗ-16: MessageServer

Cревер из ДЗ-15 разделить на три приложения:
- [x] MessageServer
- [x] Frontend
- [x] DBServer
---
- [ ] Запускать Frontend и DBServer из MessageServer. ( если у вас запуск веб приложения в контейнере, то MessageServer может копировать root.war в контейнеры при старте) 
- [x] Сделать MessageServer сокет-сервером, Frontend и DBServer клиентами.
- [x] Пересылать сообщения с Frontend на DBService через MessageServer.
---
- [ ] ~Запустить приложение с двумя фронтендами (на разных портах)* и двумя датабазными серверами.~
---
- [x] SocketTransferService, модуль используемый во всех трёх приложениях.
  - [x] psvm test
  

##### Материалы

##### Решение

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