# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание
ДЗ 01. Сборка и запуск проекта

* ~~Создать проект под управлением maven в Intellij IDEA.~~
* ~~Добавить зависимость на Google Guava/Apache Commons/библиотеку на ваш выбор.~~
 ~~Использовать библиотечные классы для обработки входных данных.~~
* ~~Задать имя проекта (project_name) в pom.xml~~
* ~~Собрать project_name.jar содержащий все зависимости.~~
* ~~Проверить, что приложение можно запустить из командной строки.~~
 ```
 $java -jar target/L01.1-jar-with-dependencies.jar
  guava hash: 225493288
  jdk hash:225493257
  obj hash:225493257
 ```
* ~~Выложить проект на github.~~
* ~~Создать ветку "obfuscation"~~ 
 ~~изменить в ней pom.xml, так чтобы сборка содержала стадию обфускации байткода.~~ 

  [ProGuard](https://www.guardsquare.com)
  [proguard-maven-plugin](https://wvengen.github.io/proguard-maven-plugin/proguard-mojo.html)
  без обфускации Guava

``` 
    4290 сен  3 02:59 target/L01.1.jar
 2985233 сен  3 02:59 target/L01.1-jar-with-dependencies.jar
    1770 сен  3 02:59 target/L01.1-small.jar    
```

```
$ cd target/lib/ ; ls 
 animal-sniffer-annotations-1.14.jar  checker-qual-2.5.2.jar  
 error_prone_annotations-2.1.3.jar  guava-26.0-jre.jar  j2objc-annotations-1.1.jar  
 jsr305-3.0.2.jar  L01.1.jar  L01.1-small.jar
$ java -jar L01.1.jar 
 guava hash: 466002829
 jdk hash:466002798
 obj hash:466002798
$ java -jar L01.1-small.jar 
 guava hash: 466002829
 jdk hash:466002798
 obj hash:466002798
```
    
    
