package ru.otus.sua.L03;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> jli = new ArrayList<>();
        jli.add(333);
        jli.add(444);
        jli.add(555);

        List<Integer> mli = new MyArrayList<>();
        mli.add(333);
        mli.add(444);
        mli.add(555);

        List<String> mls = new MyArrayList<>();
        mls.add("333");
        mls.add("444");
        mls.add("555");

        System.out.println(jli.toString());
        System.out.println(mli.toString());
        System.out.println(mls.toString());
    }

}
