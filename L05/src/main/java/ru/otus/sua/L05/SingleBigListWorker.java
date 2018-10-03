package ru.otus.sua.L05;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SingleBigListWorker {

    private static Logger log = Logger.getLogger(SingleBigListWorker.class.getName());

//  private ForkJoinPool pool; // FJP тоже работает, оставлено специально
    private ExecutorService pool;

    public SingleBigListWorker() throws InterruptedException {
//      pool = new ForkJoinPool(1);  // FJP тоже работает, оставлено специально
        pool = Executors.newFixedThreadPool(1);
        pool.submit(new ListSpinRoll(4 * 1234 * 1234, 10000));
    }

    private class ListSpinRoll implements Runnable {

        List<Integer> list;
        int listSize;
        int waitMillis;

        ListSpinRoll(int listSize, int waitMillis) throws InterruptedException {
            this.listSize = listSize;
            this.waitMillis = waitMillis;
        }

        @Override
        public void run() {
            log.info("Process of creation and deletion big List<Int> will started after " + waitMillis + "ms.");
            try {
                TimeUnit.MILLISECONDS.sleep(waitMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                list = new Random().ints(listSize, 1, listSize).boxed().collect(Collectors.toList());
                log.info("Create List<Int> with size = " + list.size());
                log.info("Wait " + waitMillis + "ms. for list existence before remove its.");
                try {
                    TimeUnit.MILLISECONDS.sleep(waitMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("Delete List<Int>, and wait " + 2 * waitMillis + "ms.");
                list = null;
                try {
                    TimeUnit.MILLISECONDS.sleep(2 * waitMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

