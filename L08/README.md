# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-08. ATM Department
Написать приложение ATM Department:
- [x] Приложение может содержать несколько ATM
- [x] Department может собирать сумму остатков со всех ATM
- [x] Department может _инициировать событие_ – восстановить состояние всех ATM до начального. (начальные состояния у разных ATM могут быть разными) 
- [x] Department REPL
- [x] L07 dependency

##### Решение

``` 
Initial Cartridge1 capacity: {N10=1000}
Initial Cartridge2 capacity: {N5000=120}
23:59:02,624 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
23:59:02,627 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.groovy]
23:59:02,630 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [file:/home/lsua/DEV/OTUS/java-dev/project/L08/target/classes/logback.xml]
23:59:02,631 |-WARN in ch.qos.logback.classic.LoggerContext[default] - Resource [logback.xml] occurs multiple times on the classpath.
23:59:02,631 |-WARN in ch.qos.logback.classic.LoggerContext[default] - Resource [logback.xml] occurs at [file:/home/lsua/DEV/OTUS/java-dev/project/L07/target/classes/logback.xml]
23:59:02,631 |-WARN in ch.qos.logback.classic.LoggerContext[default] - Resource [logback.xml] occurs at [file:/home/lsua/DEV/OTUS/java-dev/project/L08/target/classes/logback.xml]
23:59:02,761 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - debug attribute not set
23:59:02,762 |-INFO in ch.qos.logback.core.joran.action.TimestampAction - Using current interpretation time, i.e. now, as time reference.
23:59:02,836 |-INFO in ch.qos.logback.core.joran.action.TimestampAction - Adding property to the context with key="byDay" and value="20181103T235902" to the LOCAL scope
23:59:02,837 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
23:59:02,845 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [STDOUT]
23:59:02,854 |-INFO in ch.qos.logback.core.joran.action.NestedComplexPropertyIA - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
23:59:02,884 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender of type [ch.qos.logback.core.FileAppender]
23:59:02,892 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [FILE]
23:59:02,895 |-INFO in ch.qos.logback.core.joran.action.NestedComplexPropertyIA - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
23:59:02,897 |-INFO in ch.qos.logback.core.FileAppender[FILE] - File property is set to [/home/lsua/DEV/OTUS/java-dev/project/L08/logs/L08-20181103T235902.txt]
23:59:02,898 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to DEBUG
23:59:02,898 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [FILE] to Logger[ROOT]
23:59:02,899 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [STDOUT] to Logger[ROOT]
23:59:02,899 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
23:59:02,900 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@47542153 - Registering current configuration as safe fallback point

---------------------------------------------------------------------
Oracle Nashorn 10.0.2
ECMAScript ECMA - 262 Edition 5.1

ATM-DEPARTMENT SIMULATOR INTERFACE
Commands:
 help  - this help
 dep.balance() - sum of money in ATMs
 dep.available() - list of cartridges contents on ATMs
 dep.list()  - list ATMs
 dep.notify("RESET") - send event to all ATMs
 atmN.help() - help for N'th ATM
 quit | dep.exit() - for exit;

> dep.available()
{N5000=120},
{N10=1000}
> atm1.get(440)
WITHDRAWED: {N10=44}
> atm2.get(55000)
WITHDRAWED: {N5000=11}
> dep.available()
{N5000=109},
{N10=956}
> dep.notify("RESET"
<eval>:1:18 Expected , but found eof
dep.notify("RESET"
                  ^ in <eval> at line number 1 at column number 18
> dep.notify("RESET")
23:59:58.466 [main] INFO  ru.otus.sua.L08.atm.AdvancedAtm - ATM reset done.
23:59:58.468 [main] INFO  ru.otus.sua.L08.atm.AdvancedAtm - ATM reset done.
OK
> dep.available()
{N5000=120},
{N10=1000}
> atm2.get(5000)
WITHDRAWED: {N5000=1}
> atm1.get(740)
WITHDRAWED: {N10=74}
> dep.available()
{N5000=119},
{N10=926}
> dep.notify("RECET")
UNKNOWN EVENT
> dep.notify("RESET")
00:00:59.674 [main] INFO  ru.otus.sua.L08.atm.AdvancedAtm - ATM reset done.
00:00:59.675 [main] INFO  ru.otus.sua.L08.atm.AdvancedAtm - ATM reset done.
OK
> dep.available()
{N5000=120},
{N10=1000}
> 
```



```
$ mvn deploy:deploy-file -Durl=file:///home/lsua/DEV/OTUS/java-dev/project/repo/ -Dfile=L07/target/L07.jar -DgroupId=ru.otus.sua -DartifactId=L07 -Dpackaging=jar -Dversion=2018-08

register repo in pom.xml
```


``` 
Initial Cartridge1 capacity: {N10=1000}
Initial Cartridge2 capacity: {N5000=120}
---------------------------------------------------------------------
Oracle Nashorn 10.0.2
ECMAScript ECMA - 262 Edition 5.1

ATM-DEPARTMENT SIMULATOR INTERFACE
Commands:
 help  - this help
 dep.balance() - sum of money in ATMs
 dep.available() - list of cartridges contents on ATMs
 dep.list()  - list ATMs
 atmN.help() - help for N'th ATM
 quit | dep.exit() - for exit;

> dep.balance()
610000
> dep.list()
atm2,
atm1
> dep.available()
{N5000=120},
{N10=1000}
> atm1.balance()
10000
> atm2.balance()
600000
> atm1.available()
{N10=1000}
> atm2.available()
{N5000=120}
> atm2.help()
ATM SIMULATOR INTERFACE
Commands:
 help | atm.help() - this help
 quit | atm.exit() - for exit;
 atm.put("N1000/3,N100/2, ...") - put to ATM Nominal/Quantity pairs
 atm.get(1000) - get sum from ATM
 atm.available() - show how many Money in ATM
 atm.balance() - show total sum of Money in ATM
 atm.setValute("RUR") - change Valute for operations.
> atm2.put("N5000/50")
ADDED: {N5000=50}= 250000RUR
> dep.balance()
860000
> 
```

