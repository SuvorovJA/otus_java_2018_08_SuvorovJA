package ru.otus.sua.L13;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Slf4j
public class ParallelSorter {

    private static Class<? extends Sorter> sorterClass;
    private final int sorterThreads;

    public ParallelSorter(int sorterThreads, Class<? extends Sorter> sorterClass) {
        this.sorterThreads = sorterThreads;
        ParallelSorter.sorterClass = sorterClass;
    }


    public int[] sort(int[] array) {
        try {
            return splitAndSort(array, sorterThreads);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("sorting interrupted. return null.");
            return null;
        }
    }

    private int[] splitAndSort(int[] a, int sorterThreads) throws InterruptedException {
        if (sorterThreads <= 0) throw new IllegalArgumentException("MAX_SORTER_THREADS must be >0");
        if (sorterThreads == 1) return SorterFab.getSorter().sort(a);
        Range[] ranges = arraySplitAndCopy(a, sorterThreads);
        threadSort(ranges);
        return mergeSortedArrays(ranges);
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
            if (remainder > 0) { shift++;remainder--; }
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


    private int[] mergeSortedArrays(Range[] ranges) {

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
            rangesNext[index] = new Range();
            rangesNext[index].setArray(merge(ranges[i - 1].getArray(), ranges[i].getArray()));
            index++;
        }
        if (ranges.length % 2 != 0) rangesNext[rangesNext.length - 1] = ranges[ranges.length - 1];
        return mergeSortedArrays(rangesNext);
    }

    private int[] merge(int[] a, int[] b) {
        int[] answer = new int[a.length + b.length];
        int i = a.length - 1, j = b.length - 1, k = answer.length;
        while (k > 0)
            answer[--k] = (j < 0 || (i >= 0 && a[i] >= b[j])) ? a[i--] : b[j--];
        return answer;
    }

    private void threadSort(Range[] ranges) throws InterruptedException {
        Thread[] threads = new Thread[sorterThreads];
        int index = 0;
        for (Range range : ranges) {
            Thread thread = new Thread(() -> SorterFab.getSorter().sort(range.getArray()));
            thread.setName(range.getName());
            threads[index++] = thread;
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        log.info("End all threads.");
    }

    @Data
    private static class Range {
        private String name;
        private int[] array;
    }


    public static class SorterFab {
        static Sorter getSorter() {
            try {
                return sorterClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                return arr -> {
                    log.info("generate default sorter");
                    Arrays.parallelSort(arr);
                    return arr;
                };
            }
        }
    }

}
