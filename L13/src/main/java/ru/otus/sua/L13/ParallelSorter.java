package ru.otus.sua.L13;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ParallelSorter {

    private static Sorter sorter;
    private final int sorterThreads;

    public ParallelSorter(int sorterThreads, Sorter sorter) {
        this.sorterThreads = sorterThreads;
        ParallelSorter.sorter = sorter;
    }

    private static Range[] arraySplit(int[] a, int sorterThreads) {

        Range[] ranges = new Range[sorterThreads];

        int chunkSize = a.length / sorterThreads;
        int remainder = a.length % sorterThreads;
        int shift = 0;

        System.out.println("Split ranges\n Array length: " + a.length + "\n Threads: " + sorterThreads);
        for (int i = 1; i <= sorterThreads; i++) {
            int startElement = (i - 1) * chunkSize;
            int endElement = startElement + chunkSize - 1;
            startElement += shift;
            if (remainder > 0) {
                shift++;
                remainder--;
            }
            endElement += shift;

            ranges[i - 1] = new Range();
            ranges[i - 1].setName("SortThread" + i);
            ranges[i - 1].setStart(startElement);
            ranges[i - 1].setEnd(endElement);
            ranges[i - 1].setLength(endElement - startElement + 1);
            System.out.println(String.format("chunk for %s: from=%d, to=%d, length=%d",
                    ranges[i - 1].getName(),
                    ranges[i - 1].getStart(),
                    ranges[i - 1].getEnd(),
                    ranges[i - 1].getLength()));

        }
        return ranges;
    }

    public int[] sort(int[] a) {
        try {
            return splitAndSort(a, sorterThreads);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("sorting interrupted. return null.");
            return null;
        }
    }

    private int[] splitAndSort(int[] a, int sorterThreads) throws InterruptedException {
        if (sorterThreads <= 0) throw new IllegalArgumentException("MAX_SORTER_THREADS must be >0");
        if (sorterThreads == 1) return SorterFab.getSorter().sort(a, 0, a.length - 1);
        Range[] ranges = arraySplit(a, sorterThreads);
        threadSort(a, ranges);
        return mergeSortedRanges(a, ranges);
    }

    private int[] mergeSortedRanges(int[] a, Range[] ranges) {
        if (ranges.length == 1) return a;
        System.out.print("merge 2 ranges from " + ranges.length);
        Range preLast = ranges[ranges.length - 2];
        Range last = ranges[ranges.length - 1];
        System.out.println(" [" + preLast.getStart() + "," + preLast.getEnd() +
                "] and [" + last.getStart() + "," + last.getEnd() + "]");
        merge(a, preLast, last);
        preLast.setEnd(last.getEnd());
        preLast.setLength(preLast.getLength() + last.getLength());
        return mergeSortedRanges(a, Arrays.copyOf(ranges, ranges.length - 1));
    }

    private void merge(int[] a, Range range1, Range range2) {
        // TODO check and swap. range2 MUST be upper then range1
        int mergedSize = range1.getLength() + range2.getLength();
        int[] merged = (new int[mergedSize]);
        int i = range1.getEnd(), j = range2.getEnd(), k = merged.length;
        while (k > 0)
            merged[--k] = (j < range2.getStart() || (i >= range1.getStart() && a[i] >= a[j])) ? a[i--] : a[j--];
        System.arraycopy(merged,0,a,range1.getStart(),mergedSize);
    }

    private synchronized void swap(int[] a, int i, int j) {
        int store = a[i];
        a[i] = a[j];
        a[j] = store;
    }

    private void threadSort(int[] a, Range[] ranges) throws InterruptedException {
        Thread[] threads = new Thread[sorterThreads];
        int index = 0;
        for (Range range : ranges) {
            Thread thread = new Thread(() -> SorterFab.getSorter().sort(a, range.getStart(), range.getEnd()));
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
        private int start;
        private int end;
        private int length;
        private String name;
    }


    public static class SorterFab {
        static Sorter getSorter() {
            return sorter;
        }
    }

}
