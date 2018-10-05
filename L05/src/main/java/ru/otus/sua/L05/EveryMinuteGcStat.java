package ru.otus.sua.L05;

import org.apache.log4j.Logger;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EveryMinuteGcStat {

    private static Logger log = Logger.getLogger(EveryMinuteGcStat.class.getName());
    private static String pid = ManagementFactory.getRuntimeMXBean().getName();
    private final long runCounterLimit = 6; // сколько раз сработать до самозавершения приложения
    private ScheduledExecutorService executorService;
    private long totalGcCount = 0;
    private long totalGcTime = 0;
    private List<GarbageCollectorMXBean> mxBeanList;
    private long prevYongCount = 0;
    private long prevYongTime = 0;
    private long prevOldCount = 0;
    private long prevOldTime = 0;
    private long runCounter = 0;

    public EveryMinuteGcStat() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::stat, 6, 60, TimeUnit.SECONDS);
        mxBeanList = ManagementFactory.getGarbageCollectorMXBeans();
        log.info("Garbage Collectors:");
        for (GarbageCollectorMXBean gc : mxBeanList) {
            log.info(gc.getName());
        }
        log.info("\n\t\t GC statistics. All timings in (ms). About table columns:" +
                " \n\t\t YoungCount| YoungTime| OldCount| OldTime| TotalCount| TotalTime|");
    }

    private void stat() {

        runCounter++;

        long minuteYongCount = 0;
        long minuteYongTime = 0;
        long minuteOldCount = 0;
        long minuteOldTime = 0;

        long currentYongCount = mxBeanList.get(0).getCollectionCount();
        long currentOldCount = mxBeanList.get(1).getCollectionCount();

        minuteYongCount = currentYongCount - prevYongCount;
        minuteOldCount = currentOldCount - prevOldCount;

        if (minuteYongCount > 0) {
            totalGcCount += minuteYongCount;
            prevYongCount = currentYongCount;
            long currentYongTime = mxBeanList.get(0).getCollectionTime();
            minuteYongTime = currentYongTime - prevYongTime;
            if (minuteYongTime > 0) {
                totalGcTime += minuteYongTime;
                prevYongTime = currentYongTime;
            }
        }

        if (minuteOldCount > 0) {
            totalGcCount += minuteOldCount;
            prevOldCount = currentOldCount;
            long currentOldTime = mxBeanList.get(1).getCollectionTime();
            minuteOldTime = currentOldTime - prevOldTime;
            if (minuteOldTime > 0) {
                totalGcTime += minuteOldTime;
                prevOldTime = currentOldTime;
            }
        }

        log.info(String.format("%8d| %8d| %8d| %8d| %8d| %8d|",
                minuteYongCount,
                minuteYongTime,
                minuteOldCount,
                minuteOldTime,
                totalGcCount,
                totalGcTime
        ));

        if (runCounter > runCounterLimit) {
            System.exit(0);
        }

    }

}



