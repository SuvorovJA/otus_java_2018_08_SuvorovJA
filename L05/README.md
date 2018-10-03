# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ 04.  Измерение активности GC

:ballot_box_with_check:: Написать приложение, которое следит за сборками мусора и пишет в лог количество сборок каждого типа
(young, old) и время которое ушло на сборки в минуту.

- [ ] Добиться OutOfMemory в этом приложении через медленное подтекание по памяти (например добавлять
элементы в List и удалять только половину).

- [ ]: Настроить приложение (можно добавлять Thread.sleep(...)) так чтобы оно падало с OOM примерно через 5 минут
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