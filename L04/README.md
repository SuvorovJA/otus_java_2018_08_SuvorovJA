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
   doClass(): Methods: BeforeAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   doClass(): Methods: TestAnnotated, TestAnnotatedMustFailed, TestAnnotatedOther
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): TestAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedMustFailed([])
   executeMetod(): >>>FAIL<<<
   executeMetod(): TestAnnotatedOther([int arg0, java.lang.String arg1])
   executeMetod(): >>>SKIP<<< (method have parameters. not realized.)
   doClass(): Methods: AfterAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
Run with package name:
   doPackage(): ru.otus.sua.L04.testable
   doClass(): Class: ru.otus.sua.L04.testable.PackageTestableNoTests
   doClass(): no @Before methods
   doClass(): no @Test methods
   doClass(): no @After methods
   doClass(): Class: ru.otus.sua.L04.testable.PackageTestable
   doClass(): Methods: BeforeAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.PackageTestable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   doClass(): Methods: TestAnnotated, TestAnnotatedMustFailed, TestAnnotatedOther
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.PackageTestable()]
   executeMetod(): TestAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedMustFailed([])
   executeMetod(): >>>FAIL<<<
   executeMetod(): TestAnnotatedOther([int arg0, java.lang.String arg1])
   executeMetod(): >>>SKIP<<< (method have parameters. not realized.)
   doClass(): Methods: AfterAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.PackageTestable()]
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   doClass(): Class: ru.otus.sua.L04.testable.Testable
   doClass(): Methods: BeforeAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): BeforeAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   doClass(): Methods: TestAnnotated, TestAnnotatedMustFailed, TestAnnotatedOther
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): TestAnnotated([])
   executeMetod(): >>>SUCCESS<<<
   executeMetod(): TestAnnotatedMustFailed([])
   executeMetod(): >>>FAIL<<<
   executeMetod(): TestAnnotatedOther([int arg0, java.lang.String arg1])
   executeMetod(): >>>SKIP<<< (method have parameters. not realized.)
   doClass(): Methods: AfterAnnotated
   getInstance(): Constructors: [public ru.otus.sua.L04.testable.Testable(int), public ru.otus.sua.L04.testable.Testable()]
   executeMetod(): AfterAnnotated([])
   executeMetod(): >>>SUCCESS<<<


```