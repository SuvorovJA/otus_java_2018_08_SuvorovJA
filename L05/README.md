# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ 04.  Измерение активности GC

- [x] Написать приложение, которое следит за сборками мусора и пишет в лог количество сборок каждого типа
(young, old) и время которое ушло на сборки в минуту.

- [ ] Добиться OutOfMemory в этом приложении через медленное подтекание по памяти (например добавлять
элементы в List и удалять только половину).

- [ ] Настроить приложение (можно добавлять Thread.sleep(...)) так чтобы оно падало с OOM примерно через 5 минут
после начала работы.

- [x] Собрать статистику (количество сборок, время на сборрки) по разным типам GC.



##### Решение
```
20:09:25 main@Main:11 - PID=14348@PC-X
20:09:25 main@EveryMinuteGcStat:26 - Garbage Collectors:
20:09:25 main@EveryMinuteGcStat:28 - G1 Young Generation
20:09:25 main@EveryMinuteGcStat:28 - G1 Old Generation
20:09:25 main@EveryMinuteGcStat:30 - 
		 GC statistics. All timings in (ms). About table columns: 
		 YoungCount| YoungTime| OldCount| OldTime| TotalCount| TotalTime|
20:09:31 pool-1-thread-1@EveryMinuteGcStat:43 -        0|        0|        0|        0|        0|        0|
20:10:31 pool-1-thread-1@EveryMinuteGcStat:43 -        0|        0|        0|        0|        0|        0|
20:11:31 pool-1-thread-1@EveryMinuteGcStat:43 -        0|        0|        0|        0|        0|        0|
20:12:31 pool-1-thread-1@EveryMinuteGcStat:43 -        0|        0|        0|        0|        0|        0|

```

```22:01:30 main@Main:11 - PID=21794@PC-X
   22:01:30 main@EveryMinuteGcStat:26 - Garbage Collectors:
   22:01:30 main@EveryMinuteGcStat:28 - G1 Young Generation
   22:01:30 main@EveryMinuteGcStat:28 - G1 Old Generation
   22:01:30 main@EveryMinuteGcStat:30 - 
   		 GC statistics. All timings in (ms). About table columns: 
   		 YoungCount| YoungTime| OldCount| OldTime| TotalCount| TotalTime|
   22:01:30 pool-2-thread-1@SingleBigListWorker:46 - Process of creation and deletion big List<Int> will started after 10000ms.
   22:01:36 pool-1-thread-1@EveryMinuteGcStat:43 -        0|        0|        0|        0|        0|        0|
   22:01:40 pool-2-thread-1@SingleBigListWorker:54 - Create List<Int> with size = 6091024
   22:01:40 pool-2-thread-1@SingleBigListWorker:55 - Wait 10000ms. for list existence before remove its.
   22:01:50 pool-2-thread-1@SingleBigListWorker:61 - Delete List<Int>, and wait 20000ms.
   22:02:11 pool-2-thread-1@SingleBigListWorker:54 - Create List<Int> with size = 6091024
   22:02:11 pool-2-thread-1@SingleBigListWorker:55 - Wait 10000ms. for list existence before remove its.
   22:02:21 pool-2-thread-1@SingleBigListWorker:61 - Delete List<Int>, and wait 20000ms.
   22:02:36 pool-1-thread-1@EveryMinuteGcStat:43 -       10|      862|        0|        0|       10|      862|
   22:02:42 pool-2-thread-1@SingleBigListWorker:54 - Create List<Int> with size = 6091024
   22:02:42 pool-2-thread-1@SingleBigListWorker:55 - Wait 10000ms. for list existence before remove its.
   22:02:52 pool-2-thread-1@SingleBigListWorker:61 - Delete List<Int>, and wait 20000ms.
   22:03:13 pool-2-thread-1@SingleBigListWorker:54 - Create List<Int> with size = 6091024
   22:03:13 pool-2-thread-1@SingleBigListWorker:55 - Wait 10000ms. for list existence before remove its.
   22:03:23 pool-2-thread-1@SingleBigListWorker:61 - Delete List<Int>, and wait 20000ms.
   22:03:36 pool-1-thread-1@EveryMinuteGcStat:43 -       18|     1617|        0|        0|       28|     2479|
```