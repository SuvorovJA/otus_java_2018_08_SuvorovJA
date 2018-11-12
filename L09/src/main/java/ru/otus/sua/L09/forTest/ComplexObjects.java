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

    public long getPrimiLong() {
        return primiLong;
    }

    public void setPrimiLong(long primiLong) {
        this.primiLong = primiLong;
    }

    public List<Double> getDoubleList() {
        return doubleList;
    }

    public void setDoubleList(List<Double> doubleList) {
        this.doubleList = doubleList;
    }

    public Long getBoxedLong() {
        return boxedLong;
    }

    public void setBoxedLong(Long boxedLong) {
        this.boxedLong = boxedLong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
