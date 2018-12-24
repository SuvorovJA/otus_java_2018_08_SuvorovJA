package ru.otus.sua.L13;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j // for thread time and info print
public class SorterImpl implements Sorter {
    @Override
    public int[] sort(int[] arr, int from, int to) {
        log.info("Sort start.["+from+","+to+"]");
        Arrays.sort(arr,from,to+1);
        log.info("Sort end.");
        return arr;
    }
}
