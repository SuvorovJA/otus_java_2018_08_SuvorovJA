package ru.otus.sua.L13;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j // for thread time and info print
public class SorterImpl implements Sorter {
    @Override
    public int[] sort(int[] arr) {
        log.info("Sort start.");
        Arrays.sort(arr);
        log.info("Sort end.");
        return arr;
    }
}
