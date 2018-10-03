package ru.otus.sua.L05;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SingleBigListWorker {

    private static Logger log = Logger.getLogger(SingleBigListWorker.class.getName());

    int listSize;
    int waitMillis;
    boolean leakageMode;

    //  private ForkJoinPool pool; // FJP тоже работает, оставлено специально
    private ExecutorService pool;

    public SingleBigListWorker(int listSize, int waitMillis, boolean leakageMode) {

        this.listSize = listSize;
        this.waitMillis = waitMillis;
        this.leakageMode = leakageMode;

//      pool = new ForkJoinPool(1);  // FJP тоже работает, оставлено специально
        pool = Executors.newFixedThreadPool(1);
        pool.submit(new ListSpinRoll());
    }

    private class ListSpinRoll implements Runnable {

        List<Integer> list;


        @Override
        public void run() {
            log.info("Process of creation and deletion big List<Int> will started after " + waitMillis + "ms.");
            waiter();
            //noinspection InfiniteLoopStatement
            while (true) {
                list = new Random().ints(listSize, 1, listSize).boxed().collect(Collectors.toList());
                log.info("Create List<Int> with size = " + list.size());
                if (leakageMode) {
                    leakerNeverReturn();
                } else {
                    log.info("Wait " + waitMillis + "ms. for list existence before remove its.");
                    waiter();
                    log.info("Delete List<Int>, and wait " + 2 * waitMillis + "ms.");
                    list = null;
                    waiter();
                    waiter();
                }
            }
        }

        private void waiter() {
            try {
                TimeUnit.MILLISECONDS.sleep(waitMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void leakerNeverReturn() {
            List<Double> leekList = new ArrayList<>();
            List<Double> doubleList = list.stream().mapToDouble(Integer::doubleValue).boxed().collect(Collectors.toList());
            //noinspection InfiniteLoopStatement
            while (true) {
                leekList.addAll(doubleList);
                leekList.removeIf(i -> i > (double) (listSize / 2));
                log.info("LeekList<Double>.size()=" + leekList.size());
                waiter();
            }
        }
    }

}

