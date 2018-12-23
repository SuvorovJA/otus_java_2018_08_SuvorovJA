package ru.otus.sua.L13;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

@Slf4j
public class Launcher {

    private final static int ARR_SIZE = 15;
    private final static int MAX_SORTER_THREADS = 4;
    private static final int RANDOM_NUMBER_BOUND = 14;
    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int TAIL_LENGTH = 22;

    public static void main(String[] args) {

        printConstants();
        int[] a = new Random().ints(ARR_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).toArray();
        printTailOfArray(a, TAIL_LENGTH);
        printTailOfArray((new ParallelSorter(MAX_SORTER_THREADS,SorterImpl.class)).sort(a),TAIL_LENGTH);

    }

    private static void printConstants() {
        System.out.println("ARR_SIZE=" + ARR_SIZE);
        System.out.println("MAX_SORTER_THREADS=" + MAX_SORTER_THREADS);
        System.out.println("printing TAIL_LENGTH=" + TAIL_LENGTH);
        System.out.println("randoms from " + RANDOM_NUMBER_ORIGIN + " to " + (RANDOM_NUMBER_BOUND - 1));
        System.out.println();
    }

    private static void printTailOfArray(int[] a, int tailLength) {
        if (tailLength <= 0) tailLength = 5;
        if (tailLength > a.length) tailLength = a.length;
        System.out.println("\n last " + tailLength + " elements:");
        System.out.print("...");
        for (int i = a.length - tailLength; i < a.length; i++) System.out.print(" " + a[i]);
        System.out.println("\n");
    }


}
