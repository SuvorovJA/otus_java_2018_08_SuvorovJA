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


``` 
$ java -jar target/L01.1-small.jar 
 guava hash: 611437766
 jdk hash:611437735
 obj hash:611437735
$ ll target/*jar
    4300 сен  3 12:39 target/L01.1.jar
 2985243 сен  3 12:39 target/L01.1-jar-with-dependencies.jar
    6769 сен  3 12:39 target/L01.1-small.jar

```
    
    
