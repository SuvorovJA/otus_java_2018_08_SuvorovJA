# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-06: my cache engine

- [x] Напишите свой cache engine с soft references 
  - [x] SoftHashMap
  - [x] Cache interface
  - [x] Cache implementation
  - [x] Testing

##### Решение

```
23:24:28 main@Main:13 - All tests with cache size =10
23:24:28 main@Main:15 - Test Eternal Cache
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - value is Test10
23:24:28 main@Main:56 - value is Test11
23:24:28 main@Main:56 - value is Test12
23:24:28 main@Main:56 - value is Test13
23:24:28 main@Main:56 - value is Test14
23:24:28 main@Main:56 - value is Test15
23:24:28 main@Main:56 - value is Test16
23:24:28 main@Main:56 - value is Test17
23:24:28 main@Main:56 - value is Test18
23:24:28 main@Main:56 - value is Test19
23:24:28 main@Main:19 - Hits=10 Miss=10
23:24:28 main@Main:25 - Test Life Timing Cache ms=555
23:24:28 main@Main:56 - value is Test0
23:24:28 main@Main:56 - value is Test1
23:24:28 main@Main:56 - value is Test2
23:24:28 main@Main:56 - value is Test3
23:24:28 main@Main:56 - value is Test4
23:24:28 main@Main:56 - value is Test5
23:24:28 main@Main:56 - value is Test6
23:24:28 main@Main:56 - value is Test7
23:24:28 main@Main:56 - value is Test8
23:24:28 main@Main:56 - value is Test9
23:24:28 main@Main:29 - sleepint for ms=550
23:24:28 main@Main:56 - value is Test0
23:24:28 main@Main:56 - value is Test1
23:24:28 main@Main:56 - value is Test2
23:24:28 main@Main:56 - value is Test3
23:24:28 main@Main:56 - value is Test4
23:24:28 main@Main:56 - value is Test5
23:24:28 main@Main:56 - value is Test6
23:24:28 main@Main:56 - value is Test7
23:24:28 main@Main:56 - missing
23:24:28 main@Main:56 - missing
23:24:28 main@Main:32 - Hits=18 Miss=2
23:24:28 main@Main:38 - Test Idle Timing Cache ms=500
23:24:28 main@Main:56 - value is Test0
23:24:28 main@Main:56 - value is Test1
23:24:28 main@Main:56 - value is Test2
23:24:28 main@Main:56 - value is Test3
23:24:28 main@Main:56 - value is Test4
23:24:28 main@Main:56 - value is Test5
23:24:28 main@Main:56 - value is Test6
23:24:28 main@Main:56 - value is Test7
23:24:28 main@Main:56 - value is Test8
23:24:28 main@Main:56 - value is Test9
23:24:28 main@Main:42 - sleepint for ms=490
23:24:29 main@Main:56 - value is Test0
23:24:29 main@Main:56 - value is Test1
23:24:29 main@Main:56 - value is Test2
23:24:29 main@Main:56 - value is Test3
23:24:29 main@Main:56 - value is Test4
23:24:29 main@Main:56 - value is Test5
23:24:29 main@Main:56 - value is Test6
23:24:29 main@Main:56 - value is Test7
23:24:29 main@Main:56 - value is Test8
23:24:29 main@Main:56 - value is Test9
23:24:29 main@Main:45 - sleepint for ms=980
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:56 - missing
23:24:30 main@Main:48 - Hits=20 Miss=10
```

```
Experiment for processQueue(), в лог удаляемый ключ
#21:47:04 main@Main:13 - All tests with cache size =100000
$ java10 -Xmx59M -Xms59M -jar target/L06.jar |tee  log.txt | grep === 
$ cat log.txt | grep === | wc -l
69538 - ключей очищено в SoftHashMap после garbage collector
```


