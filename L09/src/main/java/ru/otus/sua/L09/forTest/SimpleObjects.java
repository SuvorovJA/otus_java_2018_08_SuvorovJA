package ru.otus.sua.L09.forTest;

public class SimpleObjects {
    private boolean boo;
    private char cha;
    private Character boxedCha;
    private transient int nonjson;

    public SimpleObjects() {
    }

    public SimpleObjects(boolean boo, char cha, int nonjson) {
        this.boo = boo;
        this.cha = cha;
        this.nonjson = nonjson;
        this.boxedCha = (Character) cha;
    }

}
