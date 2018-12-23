# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-13: Многопоточная сортировка

- [x] Написать приложение, которое сортирует массив чисел в 4 потоках с использованием библиотеки или без нее. 

##### Материалы


##### Решение

``` 
ARR_SIZE=200000
MAX_SORTER_THREADS=4
printing TAIL_LENGTH=22
randoms from -10 to 10


 last 22 elements:
... -7 3 -3 10 8 -4 -7 -4 7 1 -9 -2 -10 -9 1 -6 -5 -3 5 -1 -2 -2

Split ranges
 Array length: 200000
 Threads: 4
chunk for SortThread1: from=0, to=49999, length=50000
chunk for SortThread2: from=50000, to=99999, length=50000
chunk for SortThread3: from=100000, to=149999, length=50000
chunk for SortThread4: from=150000, to=199999, length=50000
17:41:11.294 [SortThread3] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:41:11.308 [SortThread1] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:41:11.305 [SortThread2] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:41:11.309 [SortThread4] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:41:22.933 [SortThread2] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:41:23.020 [SortThread4] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:41:23.164 [SortThread3] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:41:23.268 [SortThread1] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:41:23.269 [main] INFO ru.otus.sua.L13.ParallelSorter - End all threads.
merge 4 arrays to 2 arrays.
last 2 arrays merge.

 last 22 elements:
... 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10

```


``` 
ARR_SIZE=119304647
MAX_SORTER_THREADS=4
printing TAIL_LENGTH=22
randoms from -10 to 10


 last 22 elements:
... 4 -5 0 0 -3 2 3 -6 3 9 -6 -1 -6 -1 8 6 10 -10 8 6 8 0

Split ranges
 Array length: 119304647
 Threads: 4
chunk for SortThread1: from=0, to=29826161, length=29826162
chunk for SortThread2: from=29826162, to=59652323, length=29826162
chunk for SortThread3: from=59652324, to=89478485, length=29826162
chunk for SortThread4: from=89478486, to=119304646, length=29826161
17:17:27.633 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort start.
17:17:27.633 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort start.
17:17:27.633 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort start.
17:17:27.633 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort start.
17:17:28.860 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort end.
17:17:28.877 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort end.
17:17:28.914 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort end.
17:17:28.934 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort end.
17:17:28.934 [main] INFO ru.otus.sua.L13.ParallelSorter - End all threads.
merge 4 arrays to 2 arrays.
last 2 arrays merge.

 last 22 elements:
... 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10

```


``` 
ARR_SIZE=10
MAX_SORTER_THREADS=4
printing TAIL_LENGTH=10
randoms from 1 to 9


 last 10 elements:
... 1 4 2 7 5 1 9 9 5 5

Split ranges
 Array length: 10
 Threads: 4
chunk for SortThread1: from=0, to=2, length=3
chunk for SortThread2: from=3, to=5, length=3
chunk for SortThread3: from=6, to=7, length=2
chunk for SortThread4: from=8, to=9, length=2
01:07:15.855 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:07:15.865 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:07:15.855 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:07:15.870 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:07:15.858 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:07:15.871 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:07:15.858 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:07:15.871 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort end.
End all threads.
merge 4 arrays to 2 arrays.
last 2 arrays merge.

 last 10 elements:
... 1 1 2 4 5 5 5 7 9 9
```

``` 
ARR_SIZE=11
MAX_SORTER_THREADS=5
printing TAIL_LENGTH=11
randoms from 1 to 9


 last 11 elements:
... 6 9 7 8 9 4 2 9 9 1 2

Split ranges
 Array length: 11
 Threads: 5
chunk for SortThread1: from=0, to=2, length=3
chunk for SortThread2: from=3, to=4, length=2
chunk for SortThread3: from=5, to=6, length=2
chunk for SortThread4: from=7, to=8, length=2
chunk for SortThread5: from=9, to=10, length=2
01:09:06.263 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:09:06.271 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:09:06.263 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:09:06.271 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:09:06.263 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:09:06.272 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:09:06.263 [SortThread5] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:09:06.273 [SortThread5] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:09:06.261 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:09:06.273 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort end.
End all threads.
merge 5 arrays to 3 arrays.
merge 3 arrays to 2 arrays.
last 2 arrays merge.

 last 11 elements:
... 1 2 2 4 6 7 8 9 9 9 9
```

``` 
ARR_SIZE=3
MAX_SORTER_THREADS=4
printing TAIL_LENGTH=22
randoms from 1 to 3


 last 3 elements:
... 3 3 2

Split ranges
 Array length: 3
 Threads: 4
chunk for SortThread1: from=0, to=0, length=1
chunk for SortThread2: from=1, to=1, length=1
chunk for SortThread3: from=2, to=2, length=1
chunk for SortThread4: from=3, to=2, length=0
01:24:21.392 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:24:21.403 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:24:21.391 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:24:21.406 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:24:21.390 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:24:21.406 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:24:21.405 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:24:21.407 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:24:21.407 [main] INFO ru.otus.sua.L13.Launcher - End all threads.
merge 4 arrays to 2 arrays.
last 2 arrays merge.

 last 3 elements:
... 2 3 3

```


``` 
ARR_SIZE=1111
MAX_SORTER_THREADS=5
printing TAIL_LENGTH=22
randoms from -100 to 99


 last 22 elements:
... 55 -22 24 14 66 -78 43 -83 -81 57 -49 67 47 -46 19 -74 -80 -96 -48 -1 39 61

Split ranges
 Array length: 1111
 Threads: 5
chunk for SortThread1: from=0, to=222, length=223
chunk for SortThread2: from=223, to=444, length=222
chunk for SortThread3: from=445, to=666, length=222
chunk for SortThread4: from=667, to=888, length=222
chunk for SortThread5: from=889, to=1110, length=222
01:10:44.711 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:10:44.725 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:10:44.722 [SortThread5] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:10:44.726 [SortThread5] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:10:44.721 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:10:44.727 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:10:44.716 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:10:44.728 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:10:44.716 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:10:44.729 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort end.
End all threads.
merge 5 arrays to 3 arrays.
merge 3 arrays to 2 arrays.
last 2 arrays merge.

 last 22 elements:
... 95 95 96 96 97 97 97 98 98 98 98 98 98 98 98 99 99 99 99 99 99 99

```

``` 
ARR_SIZE=111113
MAX_SORTER_THREADS=2
printing TAIL_LENGTH=22
randoms from -1010 to 1009


 last 22 elements:
... 802 981 -94 -546 558 -228 997 -847 -146 460 372 -630 340 563 -513 -858 -686 -259 -215 -170 428 -357

Split ranges
 Array length: 111113
 Threads: 2
chunk for SortThread1: from=0, to=55556, length=55557
chunk for SortThread2: from=55557, to=111112, length=55556
01:23:10.360 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:23:10.357 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort start.
01:23:10.423 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:23:10.428 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort end.
01:23:10.428 [main] INFO ru.otus.sua.L13.Launcher - End all threads.
last 2 arrays merge.

 last 22 elements:
... 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009 1009


```