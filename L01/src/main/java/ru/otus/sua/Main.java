package ru.otus.sua;

import com.google.common.base.Objects;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        System.out.println("guava hash: " + Objects.hashCode(m));
        System.out.println("jdk hash:" + java.util.Objects.hashCode(m));
        System.out.println("obj hash:" + m.hashCode());
    }
}
