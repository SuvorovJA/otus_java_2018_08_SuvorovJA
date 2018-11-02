# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-07. Написать эмулятор АТМ (банкомата).

Объект класса АТМ должен уметь
- [x] принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
- [ ] выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
- [x] выдавать сумму остатка денежных средств 
  - [x] total вид
  - [x] пономинальный вид

- [x] простой REPL интерфейс (консоль). 
  commands:
  - [x] exit, help, available, get, put ,setValute

##### Решение

``` 
cant discharge. (should happen)
Initial Cartridge capacity: {N5000=10, N1000=100, N500=91, N200=1, N100=100, N50=111, N10=1101}
---------------------------------------------------------------------
Oracle Nashorn 10.0.2
ECMAScript ECMA - 262 Edition 5.1

ATM SIMULATOR INTERFACE
Commands:
 help | atm.help() - this help
 quit | atm.exit() - for exit;
 atm.put("N1000/3,N100/2, ...") - put to ATM Nominal/Quantity pairs
 atm.get(1000) - get sum from ATM
 atm.available() - show how many Money in ATM
 atm.balance() - show total sum of Money in ATM
 atm.setValute("RUR") - change Valute for operations.
> atm.available()
{N5000=10, N1000=100, N500=91, N200=1, N100=100, N50=111, N10=1101}
> atm.balance()
222260
> atm.put("N1000/3")
ADDED: {N1000=3}= 3000RUR
> atm.balance()
225260
> atm.available()
{N5000=10, N1000=103, N500=91, N200=1, N100=100, N50=111, N10=1101}
> atm.put("N10/3,N2000/2")
<eval>:1:23 Missing close quote
atm.put("N10/3,N2000/2)
                       ^ in <eval> at line number 1 at column number 23
> atm.put("N10/3,N2000/2")
ADDED: {N2000=2, N10=3}= 4030RUR
> atm.available()
{N5000=10, N2000=2, N1000=103, N500=91, N200=1, N100=100, N50=111, N10=1104}
> atm.setValute("RUR")
NOT IMPLEMENTED
> quit 
```