# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-08. ATM Department
Написать приложение ATM Department:
- [x] Приложение может содержать несколько ATM
- [x] Department может собирать сумму остатков со всех ATM
- [ ] Department может _инициировать событие_ – восстановить состояние всех ATM до начального. (начальные состояния у разных ATM могут быть разными) 
- [x] Department REPL
- [x] L07 dependency

##### Решение

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

