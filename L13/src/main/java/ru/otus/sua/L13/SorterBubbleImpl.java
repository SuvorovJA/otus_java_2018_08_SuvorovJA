package ru.otus.sua.L13;

import lombok.extern.slf4j.Slf4j;

@Slf4j // for thread time and info print
public class SorterBubbleImpl implements Sorter {

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
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
    public int[] sort(int[] arr) {
        log.info("Sort start.");
        bubbleSort(arr);
        log.info("Sort end.");
        return arr;
    }
}
