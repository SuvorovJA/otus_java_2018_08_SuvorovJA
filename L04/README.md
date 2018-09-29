# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

ДЗ 05. Тестовый фреймворк на аннотациях

:black_square_button: Написать свой тестовый фреймворк. 

:ballot_box_with_check: Поддержать аннотации @Test, @Before, @After.

Запускать вызовом статического метода с
1. :ballot_box_with_check: именем класса с тестами,
2. :ballot_box_with_check: именем package в котором надо найти и запустить тесты 


##### Решение
```

Run with class:
   doClass(): Class: ru.otus.sua.L04.testable.Testable
   doClass(): Methods @Before: BeforeAnnotated
   doClass(): Methods @Test: TestAnnotated, TestAnnotatedMustFailed, TestAnnotatedOther
   doClass(): Methods @After: AfterAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedMustFailed([])
   executeMetod(): >>>FAIL<<<
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedOther([int arg0, java.lang.String arg1])
   executeMetod(): >>>SKIP<<< (method have parameters. not realized.)
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
Run with package name:
   doPackage(): ru.otus.sua.L04.testable
   doClass(): Class: ru.otus.sua.L04.testable.PackageTestableNoTests
   doClass(): Methods @Before: 
   doClass(): Methods @Test: 
   doClass(): Methods @After: 
   doClass(): Class: ru.otus.sua.L04.testable.PackageTestable
   doClass(): Methods @Before: BeforeAnnotated
   doClass(): Methods @Test: TestAnnotated, TestAnnotatedMustFailed, TestAnnotatedOther
   doClass(): Methods @After: AfterAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.PackageTestable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.PackageTestable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedMustFailed([])
   executeMetod(): >>>FAIL<<<
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.PackageTestable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedOther([int arg0, java.lang.String arg1])
   executeMetod(): >>>SKIP<<< (method have parameters. not realized.)
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   doClass(): Class: ru.otus.sua.L04.testable.Testable
   doClass(): Methods @Before: BeforeAnnotated
   doClass(): Methods @Test: TestAnnotated, TestAnnotatedMustFailed, TestAnnotatedOther
   doClass(): Methods @After: AfterAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedMustFailed([])
   executeMetod(): >>>FAIL<<<
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedOther([int arg0, java.lang.String arg1])
   executeMetod(): >>>SKIP<<< (method have parameters. not realized.)
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<

Process finished with exit code 0

```