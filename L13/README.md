# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-13: Многопоточная сортировка

- [x] Написать приложение, которое сортирует массив чисел в 4 потоках с использованием библиотеки или без нее. 

##### Материалы


##### Решение
inplace sort (growed max arrsize x2).
on massive parallel, is very poor(slow) single threading merging stage.
``` 
ARR_SIZE=238609294
MAX_SORTER_THREADS=4
printing TAIL_LENGTH=22
randoms from 0 to 10


 last 22 elements:
... 7 6 9 0 5 2 1 6 1 0 0 1 7 5 2 5 3 7 8 10 5 5(sum=1193027170)


Split ranges
 Array length: 238609294
 Threads: 4
chunk for SortThread1: from=0, to=59652323, length=59652324
chunk for SortThread2: from=59652324, to=119304647, length=59652324
chunk for SortThread3: from=119304648, to=178956970, length=59652323
chunk for SortThread4: from=178956971, to=238609293, length=59652323
11:42:03.391 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort start.[0,59652323]
11:42:03.391 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort start.[178956971,238609293]
11:42:03.391 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort start.[119304648,178956970]
11:42:03.391 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort start.[59652324,119304647]
11:42:05.334 [SortThread3] INFO ru.otus.sua.L13.SorterImpl - Sort end.
11:42:05.385 [SortThread2] INFO ru.otus.sua.L13.SorterImpl - Sort end.
11:42:05.577 [SortThread4] INFO ru.otus.sua.L13.SorterImpl - Sort end.
11:42:05.591 [SortThread1] INFO ru.otus.sua.L13.SorterImpl - Sort end.
11:42:05.591 [main] INFO ru.otus.sua.L13.ParallelSorter - End all threads.
merge 2 ranges from 4 [119304648,178956970] and [178956971,238609293]
merge 2 ranges from 3 [59652324,119304647] and [119304648,238609293]
merge 2 ranges from 2 [0,59652323] and [59652324,238609293]

 last 22 elements:
... 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10(sum=1193027170)


```

bubble with massive parallel on 2M array
``` 

ARR_SIZE=2000000
MAX_SORTER_THREADS=81
printing TAIL_LENGTH=22
randoms from -10 to 10


 last 22 elements:
... -3 4 2 -6 -2 2 -5 -4 -9 6 2 -4 0 -6 10 -6 -10 7 5 -1 -10 9

Split ranges
 Array length: 2000000
 Threads: 81
chunk for SortThread1: from=0, to=24691, length=24692
chunk for SortThread2: from=24692, to=49383, length=24692
chunk for SortThread3: from=49384, to=74075, length=24692
chunk for SortThread4: from=74076, to=98767, length=24692
chunk for SortThread5: from=98768, to=123459, length=24692
chunk for SortThread6: from=123460, to=148151, length=24692
chunk for SortThread7: from=148152, to=172843, length=24692
chunk for SortThread8: from=172844, to=197535, length=24692
chunk for SortThread9: from=197536, to=222227, length=24692
chunk for SortThread10: from=222228, to=246919, length=24692
chunk for SortThread11: from=246920, to=271611, length=24692
chunk for SortThread12: from=271612, to=296303, length=24692
chunk for SortThread13: from=296304, to=320995, length=24692
chunk for SortThread14: from=320996, to=345687, length=24692
chunk for SortThread15: from=345688, to=370379, length=24692
chunk for SortThread16: from=370380, to=395071, length=24692
chunk for SortThread17: from=395072, to=419763, length=24692
chunk for SortThread18: from=419764, to=444455, length=24692
chunk for SortThread19: from=444456, to=469147, length=24692
chunk for SortThread20: from=469148, to=493839, length=24692
chunk for SortThread21: from=493840, to=518531, length=24692
chunk for SortThread22: from=518532, to=543223, length=24692
chunk for SortThread23: from=543224, to=567915, length=24692
chunk for SortThread24: from=567916, to=592607, length=24692
chunk for SortThread25: from=592608, to=617299, length=24692
chunk for SortThread26: from=617300, to=641991, length=24692
chunk for SortThread27: from=641992, to=666683, length=24692
chunk for SortThread28: from=666684, to=691375, length=24692
chunk for SortThread29: from=691376, to=716067, length=24692
chunk for SortThread30: from=716068, to=740758, length=24691
chunk for SortThread31: from=740759, to=765449, length=24691
chunk for SortThread32: from=765450, to=790140, length=24691
chunk for SortThread33: from=790141, to=814831, length=24691
chunk for SortThread34: from=814832, to=839522, length=24691
chunk for SortThread35: from=839523, to=864213, length=24691
chunk for SortThread36: from=864214, to=888904, length=24691
chunk for SortThread37: from=888905, to=913595, length=24691
chunk for SortThread38: from=913596, to=938286, length=24691
chunk for SortThread39: from=938287, to=962977, length=24691
chunk for SortThread40: from=962978, to=987668, length=24691
chunk for SortThread41: from=987669, to=1012359, length=24691
chunk for SortThread42: from=1012360, to=1037050, length=24691
chunk for SortThread43: from=1037051, to=1061741, length=24691
chunk for SortThread44: from=1061742, to=1086432, length=24691
chunk for SortThread45: from=1086433, to=1111123, length=24691
chunk for SortThread46: from=1111124, to=1135814, length=24691
chunk for SortThread47: from=1135815, to=1160505, length=24691
chunk for SortThread48: from=1160506, to=1185196, length=24691
chunk for SortThread49: from=1185197, to=1209887, length=24691
chunk for SortThread50: from=1209888, to=1234578, length=24691
chunk for SortThread51: from=1234579, to=1259269, length=24691
chunk for SortThread52: from=1259270, to=1283960, length=24691
chunk for SortThread53: from=1283961, to=1308651, length=24691
chunk for SortThread54: from=1308652, to=1333342, length=24691
chunk for SortThread55: from=1333343, to=1358033, length=24691
chunk for SortThread56: from=1358034, to=1382724, length=24691
chunk for SortThread57: from=1382725, to=1407415, length=24691
chunk for SortThread58: from=1407416, to=1432106, length=24691
chunk for SortThread59: from=1432107, to=1456797, length=24691
chunk for SortThread60: from=1456798, to=1481488, length=24691
chunk for SortThread61: from=1481489, to=1506179, length=24691
chunk for SortThread62: from=1506180, to=1530870, length=24691
chunk for SortThread63: from=1530871, to=1555561, length=24691
chunk for SortThread64: from=1555562, to=1580252, length=24691
chunk for SortThread65: from=1580253, to=1604943, length=24691
chunk for SortThread66: from=1604944, to=1629634, length=24691
chunk for SortThread67: from=1629635, to=1654325, length=24691
chunk for SortThread68: from=1654326, to=1679016, length=24691
chunk for SortThread69: from=1679017, to=1703707, length=24691
chunk for SortThread70: from=1703708, to=1728398, length=24691
chunk for SortThread71: from=1728399, to=1753089, length=24691
chunk for SortThread72: from=1753090, to=1777780, length=24691
chunk for SortThread73: from=1777781, to=1802471, length=24691
chunk for SortThread74: from=1802472, to=1827162, length=24691
chunk for SortThread75: from=1827163, to=1851853, length=24691
chunk for SortThread76: from=1851854, to=1876544, length=24691
chunk for SortThread77: from=1876545, to=1901235, length=24691
chunk for SortThread78: from=1901236, to=1925926, length=24691
chunk for SortThread79: from=1925927, to=1950617, length=24691
chunk for SortThread80: from=1950618, to=1975308, length=24691
chunk for SortThread81: from=1975309, to=1999999, length=24691
17:46:42.842 [SortThread31] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread2] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.888 [SortThread41] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.870 [SortThread35] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.867 [SortThread39] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.906 [SortThread44] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.842 [SortThread26] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.842 [SortThread30] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.843 [SortThread28] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.914 [SortThread45] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.842 [SortThread32] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.927 [SortThread46] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.935 [SortThread47] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.842 [SortThread33] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.946 [SortThread48] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.842 [SortThread34] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.841 [SortThread1] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread19] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.955 [SortThread49] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread15] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread14] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread6] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.971 [SortThread50] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread5] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread3] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread29] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread4] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread7] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread9] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread10] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread11] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread36] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread13] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread25] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread16] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread8] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread17] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread18] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread20] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread21] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread22] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread23] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread27] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.872 [SortThread24] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.879 [SortThread42] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.873 [SortThread12] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.055 [SortThread51] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.063 [SortThread52] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.087 [SortThread53] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.105 [SortThread54] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.855 [SortThread37] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.143 [SortThread55] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.163 [SortThread56] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:42.867 [SortThread38] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.199 [SortThread57] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.239 [SortThread58] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.267 [SortThread59] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.311 [SortThread60] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.351 [SortThread61] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.386 [SortThread62] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.427 [SortThread63] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.470 [SortThread64] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.503 [SortThread65] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.555 [SortThread66] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.587 [SortThread67] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.643 [SortThread68] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.685 [SortThread69] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.730 [SortThread70] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.771 [SortThread71] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.811 [SortThread72] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.875 [SortThread73] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.908 [SortThread74] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.959 [SortThread75] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:43.995 [SortThread76] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:44.047 [SortThread77] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:44.091 [SortThread78] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:44.139 [SortThread79] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:44.188 [SortThread80] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:44.255 [SortThread40] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:44.257 [SortThread43] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:46:44.573 [SortThread81] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort start.
17:47:07.372 [SortThread11] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:08.385 [SortThread71] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:08.492 [SortThread14] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:08.639 [SortThread35] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:08.769 [SortThread33] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:09.234 [SortThread10] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:09.337 [SortThread76] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:10.186 [SortThread50] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:11.174 [SortThread30] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:11.653 [SortThread44] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.033 [SortThread53] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.048 [SortThread54] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.056 [SortThread6] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.094 [SortThread56] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.394 [SortThread24] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.545 [SortThread18] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.620 [SortThread49] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.678 [SortThread39] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.816 [SortThread12] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:12.943 [SortThread25] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.060 [SortThread28] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.191 [SortThread78] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.209 [SortThread59] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.212 [SortThread26] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.243 [SortThread23] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.252 [SortThread1] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.332 [SortThread38] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.470 [SortThread13] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.508 [SortThread75] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.521 [SortThread9] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.539 [SortThread36] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.771 [SortThread52] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.781 [SortThread61] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.835 [SortThread17] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.887 [SortThread48] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:13.890 [SortThread63] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.086 [SortThread27] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.135 [SortThread7] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.189 [SortThread46] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.229 [SortThread58] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.236 [SortThread66] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.303 [SortThread43] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.317 [SortThread70] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.334 [SortThread67] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.388 [SortThread69] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.404 [SortThread47] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.431 [SortThread5] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.467 [SortThread34] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.483 [SortThread77] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.587 [SortThread45] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.630 [SortThread16] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.640 [SortThread68] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.641 [SortThread60] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.654 [SortThread40] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.682 [SortThread64] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.684 [SortThread42] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.696 [SortThread3] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.762 [SortThread20] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.777 [SortThread4] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.781 [SortThread8] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.819 [SortThread73] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.839 [SortThread62] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.842 [SortThread32] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.863 [SortThread37] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.909 [SortThread19] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.918 [SortThread72] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.920 [SortThread57] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.976 [SortThread22] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:14.978 [SortThread55] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.048 [SortThread15] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.066 [SortThread74] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.097 [SortThread21] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.101 [SortThread80] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.131 [SortThread29] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.161 [SortThread79] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.183 [SortThread51] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.232 [SortThread81] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.269 [SortThread65] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.398 [SortThread31] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.450 [SortThread2] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.524 [SortThread41] INFO ru.otus.sua.L13.SorterBubbleImpl - Sort end.
17:47:15.525 [main] INFO ru.otus.sua.L13.ParallelSorter - End all threads.
merge 81 arrays to 41 arrays.
merge 41 arrays to 21 arrays.
merge 21 arrays to 11 arrays.
merge 11 arrays to 6 arrays.
merge 6 arrays to 3 arrays.
merge 3 arrays to 2 arrays.
last 2 arrays merge.

 last 22 elements:
... 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10

```


bubble in 4 thread on 200K array
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


all next on Arrays.sort() alg
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