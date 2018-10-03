# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ 04.  Измерение активности GC

- [x] Написать приложение, которое следит за сборками мусора и пишет в лог количество сборок каждого типа
(young, old) и время которое ушло на сборки в минуту.

- [x] Добиться OutOfMemory в этом приложении через медленное подтекание по памяти (например добавлять
элементы в List и удалять только половину).

- [x] Настроить приложение (можно добавлять Thread.sleep(...)) так чтобы оно падало с OOM примерно через 5 минут
после начала работы.

- [x] Собрать статистику (количество сборок, время на сборрки) по разным типам GC.



##### Решение
```
$ /usr/lib/jvm/10/bin/java -Xms900m -Xmx900m -jar target/L05.jar 
23:30:23 main@Main:11 - PID=27140@PC-X
23:30:23 main@EveryMinuteGcStat:26 - Garbage Collectors:
23:30:23 main@EveryMinuteGcStat:28 - G1 Young Generation
23:30:23 main@EveryMinuteGcStat:28 - G1 Old Generation
23:30:23 main@EveryMinuteGcStat:30 - 
                 GC statistics. All timings in (ms). About table columns: 
                 YoungCount| YoungTime| OldCount| OldTime| TotalCount| TotalTime|
23:30:23 pool-2-thread-1@SingleBigListWorker:42 - Process of creation and deletion big List<Int> will started after 4567ms.
23:30:23 pool-3-thread-1@SingleBigListWorker:42 - Process of creation and deletion big List<Int> will started after 2345ms.
23:30:26 pool-3-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 7236100
23:30:27 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=3617015
23:30:28 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:30:28 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:30:29 pool-1-thread-1@EveryMinuteGcStat:43 -       10|     1284|        0|        0|       10|     1284|
23:30:30 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=7234030
23:30:33 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=10851045
23:30:33 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:30:36 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=14468060
23:30:39 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=18085075
23:30:42 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=21702090
23:30:43 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:30:43 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:30:45 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=25319105
23:30:47 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:30:48 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=28936120
23:30:51 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=32553135
23:30:54 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=36170150
23:30:57 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=39787165
23:30:58 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:30:58 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:31:00 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=43404180
23:31:02 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:31:03 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=47021195
23:31:06 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=50638210
              ^___________________ ВМЕСТО ООМ МОЛЧА УМЕР pool-3
23:31:12 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:31:12 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:31:16 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:31:27 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:31:27 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:31:29 pool-1-thread-1@EveryMinuteGcStat:43 -       33|     2984|        4|     3500|       47|     7768|
23:31:31 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:31:41 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:31:41 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:31:45 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:31:55 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:31:55 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:32:00 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:32:10 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:32:10 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:32:14 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:32:24 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:32:24 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:32:28 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:32:29 pool-1-thread-1@EveryMinuteGcStat:43 -       42|     4145|        4|     3500|       93|    15413|
^C
```

```
$ /usr/lib/jvm/10/bin/java -Xms900m -Xmx900m -XX:+UseSerialGC -jar target/L05.jar 
23:36:05 main@Main:11 - PID=28951@PC-X
23:36:05 main@EveryMinuteGcStat:26 - Garbage Collectors:
23:36:05 main@EveryMinuteGcStat:28 - Copy
23:36:05 main@EveryMinuteGcStat:28 - MarkSweepCompact
23:36:05 main@EveryMinuteGcStat:30 - 
                 GC statistics. All timings in (ms). About table columns: 
                 YoungCount| YoungTime| OldCount| OldTime| TotalCount| TotalTime|
23:36:05 pool-2-thread-1@SingleBigListWorker:42 - Process of creation and deletion big List<Int> will started after 4567ms.
23:36:05 pool-3-thread-1@SingleBigListWorker:42 - Process of creation and deletion big List<Int> will started after 2345ms.
23:36:08 pool-3-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 7236100
23:36:09 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=3620913
23:36:10 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:36:10 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:36:11 pool-1-thread-1@EveryMinuteGcStat:43 -        3|      998|        0|        0|        3|      998|
23:36:11 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=7241826
23:36:14 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=10862739
23:36:15 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:36:17 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=14483652
23:36:20 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=18104565
23:36:23 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=21725478
23:36:25 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:36:25 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:36:25 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=25346391
23:36:28 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=28967304
23:36:30 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:36:31 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=32588217
23:36:35 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=36209130
23:36:39 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=39830043
23:36:39 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:36:39 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:36:42 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=43450956
23:36:44 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:36:46 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=47071869
23:36:49 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=50692782
              ^___________________
23:36:55 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:36:55 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:36:59 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:37:09 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:37:09 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:37:11 pool-1-thread-1@EveryMinuteGcStat:43 -       11|     1113|        8|    10384|       22|    12495|
23:37:14 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:37:23 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:37:23 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:37:28 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
^C
```

```
$ /usr/lib/jvm/10/bin/java -Xms900m -Xmx900m -XX:+UseParallelGC -XX:+UseParallelOldGC -jar target/L05.jar 
23:39:42 main@Main:11 - PID=29216@PC-X
23:39:43 main@EveryMinuteGcStat:26 - Garbage Collectors:
23:39:43 main@EveryMinuteGcStat:28 - PS MarkSweep
23:39:43 main@EveryMinuteGcStat:28 - PS Scavenge
23:39:43 main@EveryMinuteGcStat:30 - 
                 GC statistics. All timings in (ms). About table columns: 
                 YoungCount| YoungTime| OldCount| OldTime| TotalCount| TotalTime|
23:39:43 pool-2-thread-1@SingleBigListWorker:42 - Process of creation and deletion big List<Int> will started after 4567ms.
23:39:43 pool-3-thread-1@SingleBigListWorker:42 - Process of creation and deletion big List<Int> will started after 2345ms.
23:39:45 pool-3-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 7236100
23:39:46 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=3617106
23:39:50 pool-1-thread-1@EveryMinuteGcStat:43 -        1|     2141|        3|      894|        4|     3035|
23:39:50 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:39:50 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:39:50 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=7234212
23:39:54 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=10851318
23:39:54 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:39:56 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=14468424
23:39:59 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=18085530
23:40:07 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:40:07 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:40:07 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=21702636
23:40:10 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=25319742
23:40:12 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:40:12 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=28936848
23:40:15 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=32553954
23:40:19 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=36171060
23:40:23 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:40:23 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:40:23 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=39788166
23:40:25 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=43405272
23:40:27 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:40:30 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=47022378
23:40:33 pool-3-thread-1@SingleBigListWorker:74 - LeekList<Double>.size()=50639484
              ^___________________
23:40:38 pool-2-thread-1@SingleBigListWorker:46 - Create List<Int> with size = 6091024
23:40:38 pool-2-thread-1@SingleBigListWorker:50 - Wait 4567ms. for list existence before remove its.
23:40:43 pool-2-thread-1@SingleBigListWorker:52 - Delete List<Int>, and wait 9134ms.
23:40:49 pool-1-thread-1@EveryMinuteGcStat:43 -       10|    16691|        4|      899|       18|    20625|
^C
```



```
$ /usr/lib/jvm/10/bin/java -Xms900m -Xmx900m -Xlog:gc:gc0.log -Xlog:gc* -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -jar target/L05.jar | tee console.log

Вывод теперь в gc0.log, gc1.log и console.log
В console.log на строке 627 (32я секунда) 
    виден ООМ третьего пула, приложение при этом не падает.
Дамп присутствует:   
    $ ll *hprof
    -rw------- 1  952303291 окт  3 23:55 java_pid30182.hprof
Можно покрутить задержки и размер для второго экземпляра 
    SingleBigListWorker и добиться ООМа на 5й минуте.

```
