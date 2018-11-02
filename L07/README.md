# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-07. Написать эмулятор АТМ (банкомата).

Объект класса АТМ должен уметь
- [x] принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
- [x] выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
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
ADDED: {N2000=2, N10=3}= 4030RUR
> atm.available()
{N5000=10, N2000=2, N1000=103, N500=91, N200=1, N100=100, N50=111, N10=1104}
> atm.setValute("RUR")
NOT IMPLEMENTED IN THAT ATM
> quit 


> atm.get(1000)
WITHDRAWED: {N1000=1}
> atm.available()
{N5000=10, N1000=99, N500=91, N200=1, N100=100, N50=111, N10=1101}
> atm.get(739020)
ERR: Impossible Withdraw. Big sum.
> atm.get(7390)
WITHDRAWED: {N5000=1, N1000=2, N200=1, N100=1, N50=1, N10=4}
> atm.available()
{N5000=9, N1000=97, N500=91, N200=0, N100=99, N50=110, N10=1097}
> atm.balance()
213870
> atm.get(213869)
ERR: Impossible Withdraw. Not a round number.
> atm.get(213870)
WITHDRAWED: {N5000=9, N1000=97, N500=91, N100=99, N50=110, N10=1097}
> atm.balance()
0
> atm.available()
{N5000=0, N1000=0, N500=0, N200=0, N100=0, N50=0, N10=0}
> atm.put("N1000/3,N100/2z")
ERR: incorrect quantity
> atm.put("N1000/3,N100/2,N2000/1000")
ADDED: {N2000=1000, N1000=3, N100=2}= 2003200RUR
>  atm.balance()
2003200
> atm.available()
{N5000=0, N2000=1000, N1000=3, N500=0, N200=0, N100=2, N50=0, N10=0}
> quit 


> atm.balance()
222260
> atm.get(222260)
WITHDRAWED: {N5000=10, N1000=100, N500=91, N200=1, N100=100, N50=111, N10=1101}
> atm.available()
{N5000=0, N1000=0, N500=0, N200=0, N100=0, N50=0, N10=0}
> atm.put("N1000/1")
ADDED: {N1000=1}= 1000RUR
> atm.available()
{N5000=0, N1000=1, N500=0, N200=0, N100=0, N50=0, N10=0}
> atm.get(10)
ERR: Impossible Withdraw. Not a round number or small sum.
> quit
```