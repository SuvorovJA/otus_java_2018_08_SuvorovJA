package ru.otus.sua.L04.testable;

import ru.otus.sua.L04.framework.TestException;
import ru.otus.sua.L04.framework.annotations.After;
import ru.otus.sua.L04.framework.annotations.Before;
import ru.otus.sua.L04.framework.annotations.Test;

public class Testable {

    public Testable() {

    }


    public Testable(int i) {

    }

    public void NotAnnotated() {

    }

    @Before
    public void BeforeAnnotated() {

    }

    @Test
    public void TestAnnotated() {

    }

    @Test
    public void TestAnnotatedMustFailed()  {
        throw new TestException();
    }

    @Test
    public void TestAnnotatedOther(int i, String s) {

    }

    @Test
    private void TestAnnotatedPrivate() {

    }

    @After
    public void AfterAnnotated() {

    }
}
