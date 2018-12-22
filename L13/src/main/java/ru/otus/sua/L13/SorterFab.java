package ru.otus.sua.L13;

public class SorterFab {

    public static Sorter getSorter(){
        return new SorterImpl();
    }

}
