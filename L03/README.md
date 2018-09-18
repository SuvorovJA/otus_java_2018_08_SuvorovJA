# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ 03. MyArrayList

~~Написать свою реализацию ArrayList на основе массива.~~ 
~~Проверить, что на ней работают методы java.util.Collections~~ 

>Написать свою реализацию ArrayList на основе массива.
>`class MyArrayList<T> implements List<T>{...}`
>Проверить, что на ней работают методы
>`addAll(Collection<? super T> c, T... elements)`
>`static <T> void copy(List<? super T> dest, List<? extends T> src)`
>`static <T> void sort(List<T> list, Comparator<? super T> c)`
>из `java.util.Collections`

##### Решение
```
  ArrayList<Integer> jli: [1333, 1444, 1555, 2333, 2444, 2555]
MyArrayList<Integer> mli: [3333, 3444, 3555]
mli.clear():      []
mli.addAll(jli):      [1333, 1444, 1555, 2333, 2444, 2555]
mli.indexOf(1555):    2
mli.subList(2,4):     [1555, 2333]
mli.remove(1555):     [1333, 1444, 2333, 2444, 2555]
mli.containsAll(jli): false
mli.removeAll(jli):   []
mli.addAll(Arrays.asList()): [3, 5, 7, 9, 0, 1555, 0, 1444, 1444, 9, 4]
mli.retainAll(jli);   [1555, 1444, 1444]


Collections test

MyArrayList<String>  mls:     [s933, s144, s655]
MyArrayList<String> mls2:     [, , , , , , , , , ] mls2.size()=10
Collections.addAll(mls, "x-one","b-two"): [s933, s144, s655, x-one, b-two]
Collections.copy(mls2, mls):  [s933, s144, s655, x-one, b-two, , , , , ]
Collections.sort(mls2):       [, , , , , b-two, s144, s655, s933, x-one]


Iterator remove() test

MyArrayList<String>  mls:     [s933, s144, s655, x-one, b-two]
item=s933
item=s144
item=s655 removed
item=x-one
item=b-two
MyArrayList<String>  mls:     [s933, s144, x-one, b-two]


.forEach() test: s933,s144,x-one,b-two,

```