package ru.otus.sua.L13;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

@Slf4j
public class Launcher {

    public final static int ARR_SIZE = 15;
    public final static int MAX_SORTER_THREADS = 4;
    private static final int RANDOM_NUMBER_BOUND = 14;
    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int TAIL_LENGTH = 22;

    public static void main(String[] args) throws InterruptedException {

        printConstants();
        int[] a = new Random().ints(ARR_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).toArray();
        printTailOfArray(a, TAIL_LENGTH);
        printTailOfArray(splitAndSort(a, MAX_SORTER_THREADS), TAIL_LENGTH);

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
        for (int i = a.length - tailLength; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println("\n");
    }

    private static int[] splitAndSort(int[] a, int sorterThreads) throws InterruptedException {
        if (sorterThreads <= 0) throw new IllegalArgumentException("MAX_SORTER_THREADS must be >0");
        if (sorterThreads == 1) return (new SorterImpl()).sort(a);
        Range[] ranges = arraySplitAndCopy(a, sorterThreads);
        threadSort(ranges);
        return mergeSortedArrays(ranges);
    }

    private static int[] mergeSortedArrays(Range[] ranges) {

        // TODO Threadable merging

        if (ranges.length == 1) return ranges[0].getArray();
        if (ranges.length == 2) {
            System.out.println("last 2 arrays merge.");
            return merge(ranges[0].getArray(), ranges[1].getArray());
        }
        int sizeNext = ranges.length / 2 + ranges.length % 2;
        System.out.println("merge " + ranges.length + " arrays to " + sizeNext + " arrays.");
        Range[] rangesNext = new Range[sizeNext];
        int index = 0;
        for (int i = 1; i < ranges.length; i += 2) {
            rangesNext[index]=new Range();
            rangesNext[index].setArray(merge(ranges[i - 1].getArray(), ranges[i].getArray()));
            index++;
        }
        if (ranges.length % 2 != 0) rangesNext[rangesNext.length - 1] = ranges[ranges.length - 1];
        return mergeSortedArrays(rangesNext);
    }

    private static int[] merge(int[] a, int[] b) {
        int[] answer = new int[a.length + b.length];
        int i = a.length - 1, j = b.length - 1, k = answer.length;
        while (k > 0)
            answer[--k] = (j < 0 || (i >= 0 && a[i] >= b[j])) ? a[i--] : b[j--];
        return answer;
    }

    private static void threadSort(Range[] ranges) throws InterruptedException {
        Thread[] threads = new Thread[MAX_SORTER_THREADS];
        int index = 0;
        for (Range range : ranges) {
            Thread thread = new Thread(() -> (new SorterImpl()).sort(range.getArray()));
            thread.setName(range.getName());
            threads[index++] = thread;
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        log.info("End all threads.");
    }

    private static Range[] arraySplitAndCopy(int[] a, int sorterThreads) {

        Range[] ranges = new Range[sorterThreads];

        int chunkSize = a.length / sorterThreads;
        int remainder = a.length % sorterThreads;
        int shift = 0;

        System.out.println("Split ranges\n Array length: " + a.length + "\n Threads: " + sorterThreads);
        for (int i = 1; i <= sorterThreads; i++) {
            int startElement = (i - 1) * chunkSize;
            int endElement = startElement + chunkSize - 1;
            startElement += shift;
            if (remainder > 0) { shift++; remainder--; }
            endElement += shift;
            String threadName = "SortThread" + i;
            System.out.println(String.format("chunk for %s: from=%d, to=%d, length=%d",
                    threadName, startElement, endElement, endElement - startElement + 1));
            ranges[i - 1] = new Range();
            ranges[i - 1].setName(threadName);
            ranges[i - 1].setArray(Arrays.copyOfRange(a, startElement, endElement + 1));
        }

        return ranges;
    }
}
