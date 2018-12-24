package ru.otus.sua.L13;

import lombok.extern.slf4j.Slf4j;

@Slf4j // for thread time and info print
public class SorterBubbleImpl implements Sorter {

    private static void bubbleSort(int[] arr, int from, int to) {
        int n = to - from + 1;
        int temp = 0;
        for (int i = from; i < to + 1; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @Override
    public int[] sort(int[] arr, int from, int to) {
        log.info("Sort start.");
        bubbleSort(arr, from, to);
        log.info("Sort end.");
        return arr;
    }
}
