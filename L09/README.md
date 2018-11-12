# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ-09: JSON object writer

- [x] Напишите свой json object writer (object to JSON string) аналогичный gson на основе javax.json или simple-json и Reflection.

- [x] Поддержите массивы объектов и примитивных типов, 

- [x] и коллекции из стандартный библиотерки. 

##### Решение

``` 
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by ru.otus.sua.L09.MyJson.MyJson (file:/home/lsua/DEV/OTUS/java-dev/project/L09/target/classes/) to field java.lang.Integer.digits
WARNING: Please consider reporting this to the maintainers of ru.otus.sua.L09.MyJson.MyJson
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release

  Test content: "ComplexObjects"

Reference Json: {"name":"inside name","array":[2,5,7,0,9],"boxedLong":11111111111,"primiLong":22222222222,"doubleList":[2.1,2.0,3.3,7.2],"simpleObject":{"boo":true,"cha":"S","boxedCha":"S"}}

       My Json: {"name":"inside name","array":[2,5,7,0,9],"boxedLong":11111111111,"primiLong":22222222222,"doubleList":[2.1,2.0,3.3,7.2],"simpleObject":{"boo":true,"cha":"S","boxedCha":"S"}}
----------------------------------------------------------------------------------------------------

  Test content: "Array of SimpleObjects"

Reference Json: [{"boo":false,"cha":"A","boxedCha":"A"},{"boo":true,"cha":"r","boxedCha":"r"},{"boo":false,"cha":"r","boxedCha":"r"},{"boo":true,"cha":"a","boxedCha":"a"},{"boo":true,"cha":"y","boxedCha":"y"}]

       My Json: [{"boo":false,"cha":"A","boxedCha":"A"},{"boo":true,"cha":"r","boxedCha":"r"},{"boo":false,"cha":"r","boxedCha":"r"},{"boo":true,"cha":"a","boxedCha":"a"},{"boo":true,"cha":"y","boxedCha":"y"}]
----------------------------------------------------------------------------------------------------

  Test content: "null"

Reference Json: null

       My Json: null
----------------------------------------------------------------------------------------------------

  Test content: "int"

Reference Json: 123

       My Json: 123
----------------------------------------------------------------------------------------------------

  Test content: "double"

Reference Json: 123.00001

       My Json: 123.00001
----------------------------------------------------------------------------------------------------

  Test content: "char"

Reference Json: "{"

       My Json: "{"
----------------------------------------------------------------------------------------------------

  Test content: "Integer"

Reference Json: 123123

       My Json: 123123
----------------------------------------------------------------------------------------------------

  Test content: "Integer inside Object"

Reference Json: 1231234

       My Json: 1231234
----------------------------------------------------------------------------------------------------

  Test content: "String"

Reference Json: "string"

       My Json: "string"
----------------------------------------------------------------------------------------------------


Process finished with exit code 0

```