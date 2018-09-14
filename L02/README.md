# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ 02. Измерение памяти

~~Написать стенд для определения размера объекта.~~
 
~~Определить размер пустой строки и пустых контейнеров.~~ 

~~Определить рост размера контейнера от количества элементов в нем.~~



Например:
```
Object — 8 bytes,
Empty String — 40 bytes
Array — from 12 bytes 
```

##### Решение

```
$mvn package
$cd target
$java -javaagent:L02.1-AgentForMem.jar -jar L02.1-AgentTest.jar

All sizes in Bytes

Primitives(autoboxing):
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by ru.otus.sua.L02.AgentForMem (file:/home/lsua/DEV/OTUS/java-dev/project/L02/target/L02.1-AgentForMem.jar) to field java.lang.Long.value
WARNING: Please consider reporting this to the maintainers of ru.otus.sua.L02.AgentForMem
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
  long: type=Long, plainSize=24, recursiveSize=24
   int: type=Integer, plainSize=16, recursiveSize=16
 short: type=Short, plainSize=16, recursiveSize=16
  byte: type=Byte, plainSize=16, recursiveSize=16
  bool: type=Boolean, plainSize=16, recursiveSize=16

Emptyes:
 new Object(): type=Object, plainSize=16, recursiveSize=16
 pool-string: type=String, plainSize=24, recursiveSize=40
 obj-string: type=String, plainSize=24, recursiveSize=40
 new BigDecimal(0): type=BigDecimal, plainSize=40, recursiveSize=40
 new ArrayList<String>(): type=ArrayList, plainSize=24, recursiveSize=40
 new Byte[0]: type=Byte[], plainSize=16, recursiveSize=16
 new Integer[0]: type=Integer[], plainSize=16, recursiveSize=16
 new Long[0]: type=Long[], plainSize=16, recursiveSize=16

Filled strings and collections:
 pool-string "string": type=String, plainSize=24, recursiveSize=48
 obj-string "string": type=String, plainSize=24, recursiveSize=48
 pool-string "string string string string": type=String, plainSize=24, recursiveSize=72
 obj-string "string string string string": type=String, plainSize=24, recursiveSize=72
 new BigDecimal("999999999999999.999"): type=BigDecimal, plainSize=40, recursiveSize=104
 new ArrayList<String>("1"): type=ArrayList, plainSize=24, recursiveSize=128
 new ArrayList<String>("1","2"): type=ArrayList, plainSize=24, recursiveSize=176
 new ArrayList<String>("1","2","3"): type=ArrayList, plainSize=24, recursiveSize=224
 new ArrayList<String>("1","2","3","44444444444444444"): type=ArrayList, plainSize=24, recursiveSize=288
 new Byte[110]: type=Byte[], plainSize=456, recursiveSize=2216
 new Character[110]: type=Character[], plainSize=456, recursiveSize=2216
 new Integer[110]: type=Integer[], plainSize=456, recursiveSize=2216
 new Long[110]: type=Long[], plainSize=456, recursiveSize=3096

Custom complex object:
 new CustomClass(): type=CustomClass, plainSize=24, recursiveSize=24
 new CustomClass(1000,2000,"Obj Name Obj Name"): type=CustomClass, plainSize=24, recursiveSize=104
 new CustomClass(1000,2000,"Obj Name Obj Name Obj Name Obj Name Obj Name Obj Name"): type=CustomClass, plainSize=24, recursiveSize=136
```


```
$ java -XX:-UseCompressedOops -javaagent:L02.1-AgentForMem.jar -jar L02.1-AgentTest.jar
All sizes in Bytes

Primitives(autoboxing):
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by ru.otus.sua.L02.AgentForMem (file:/home/lsua/DEV/OTUS/java-dev/project/L02/target/L02.1-AgentForMem.jar) to field java.lang.Long.value
WARNING: Please consider reporting this to the maintainers of ru.otus.sua.L02.AgentForMem
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
  long: type=Long, plainSize=24, recursiveSize=24
   int: type=Integer, plainSize=24, recursiveSize=24
 short: type=Short, plainSize=24, recursiveSize=24
  byte: type=Byte, plainSize=24, recursiveSize=24
  bool: type=Boolean, plainSize=24, recursiveSize=24

Emptyes:
 new Object(): type=Object, plainSize=16, recursiveSize=16
 pool-string: type=String, plainSize=32, recursiveSize=56
 obj-string: type=String, plainSize=32, recursiveSize=56
 new BigDecimal(0): type=BigDecimal, plainSize=48, recursiveSize=48
 new ArrayList<String>(): type=ArrayList, plainSize=40, recursiveSize=64
 new Byte[0]: type=Byte[], plainSize=24, recursiveSize=24
 new Integer[0]: type=Integer[], plainSize=24, recursiveSize=24
 new Long[0]: type=Long[], plainSize=24, recursiveSize=24

Filled strings and collections:
 pool-string "string": type=String, plainSize=32, recursiveSize=64
 obj-string "string": type=String, plainSize=32, recursiveSize=64
 pool-string "string string string string": type=String, plainSize=32, recursiveSize=88
 obj-string "string string string string": type=String, plainSize=32, recursiveSize=88
 new BigDecimal("999999999999999.999"): type=BigDecimal, plainSize=48, recursiveSize=128
 new ArrayList<String>("1"): type=ArrayList, plainSize=40, recursiveSize=208
 new ArrayList<String>("1","2"): type=ArrayList, plainSize=40, recursiveSize=272
 new ArrayList<String>("1","2","3"): type=ArrayList, plainSize=40, recursiveSize=336
 new ArrayList<String>("1","2","3","44444444444444444"): type=ArrayList, plainSize=40, recursiveSize=416
 new Byte[110]: type=Byte[], plainSize=904, recursiveSize=3544
 new Character[110]: type=Character[], plainSize=904, recursiveSize=3544
 new Integer[110]: type=Integer[], plainSize=904, recursiveSize=3544
 new Long[110]: type=Long[], plainSize=904, recursiveSize=3544

Custom complex object:
 new CustomClass(): type=CustomClass, plainSize=40, recursiveSize=40
 new CustomClass(1000,2000,"Obj Name Obj Name"): type=CustomClass, plainSize=40, recursiveSize=144
 new CustomClass(1000,2000,"Obj Name Obj Name Obj Name Obj Name Obj Name Obj Name"): type=CustomClass, plainSize=40, recursiveSize=176
```

TODO: ? "WARNING: An illegal reflective access operation has occurred
"
