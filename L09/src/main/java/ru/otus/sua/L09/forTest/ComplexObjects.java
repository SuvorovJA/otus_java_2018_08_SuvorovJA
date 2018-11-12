package ru.otus.sua.L09.forTest;

import java.util.List;

public class ComplexObjects {

    private String name;
    private int[] array;
    private Long boxedLong;
    private long primiLong;
    private List<Double> doubleList;
    private SimpleObjects simpleObject;

    public ComplexObjects() {
    }

    public SimpleObjects getSimpleObject() {
        return simpleObject;
    }

    public void setSimpleObject(SimpleObjects simpleObject) {
        this.simpleObject = simpleObject;
    }


    public void setPrimiLong(long primiLong) {
        this.primiLong = primiLong;
    }


    public void setDoubleList(List<Double> doubleList) {
        this.doubleList = doubleList;
    }


    public void setBoxedLong(Long boxedLong) {
        this.boxedLong = boxedLong;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setArray(int[] array) {
        this.array = array;
    }
}
