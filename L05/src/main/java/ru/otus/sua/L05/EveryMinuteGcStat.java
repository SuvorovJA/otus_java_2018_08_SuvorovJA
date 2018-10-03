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

    private ScheduledExecutorService executorService;

    private long totalGcCount = 0;
    private long totalGcTime = 0;
    private List<GarbageCollectorMXBean> mxBeanList;

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

        for (GarbageCollectorMXBean gc : mxBeanList) {
            long count = gc.getCollectionCount();
            long time = gc.getCollectionTime();
            if (count >= 0) totalGcCount += count;
            if (time >= 0) totalGcTime += time;
        }

        log.info(String.format("%8d| %8d| %8d| %8d| %8d| %8d|",
                mxBeanList.get(0).getCollectionCount(),
                mxBeanList.get(0).getCollectionTime(),
                mxBeanList.get(1).getCollectionCount(),
                mxBeanList.get(1).getCollectionTime(),
                totalGcCount,
                totalGcTime
        ));

    }

}



