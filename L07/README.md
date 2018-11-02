# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-07. Написать эмулятор АТМ (банкомата).

Объект класса АТМ должен уметь
- [ ] принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
- [ ] выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
- [ ] выдавать сумму остатка денежных средств 
  - [ ] total вид
  - [ ] пономинальный вид

- [x] простой REPL интерфейс (консоль). 
  commands:
  - [x] exit, help, available, get, put ,setValute

##### Решение

``` 
Oracle Nashorn 10.0.2
ECMAScript ECMA - 262 Edition 5.1

ATM SIMULATOR INTERFACE
Commands:
 help | atm.help() - this help
 quit | atm.exit() - for exit;
 atm.put("N1000/3,N100/2, ...") - put to ATM Nominal/Quantity pairs
 atm.get(1000) - get sum from ATM
 available | atm.available() - show how many Money in ATM
 atm.setValute("RUR") - change Valute for operations.
> atm.get(1000)
NOT IMPLEMENTED
> available
AVAILABLE: 
> atm.put("N1000/3,N100/2")
NOT IMPLEMENTED
> atm.setValute("RUR")
NOT IMPLEMENTED
> 
```