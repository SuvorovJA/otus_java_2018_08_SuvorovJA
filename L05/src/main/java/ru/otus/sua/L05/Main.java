package ru.otus.sua.L05;

import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("PID=" + ManagementFactory.getRuntimeMXBean().getName());
        EveryMinuteGcStat statLogger = new EveryMinuteGcStat();
        SingleBigListWorker listWorker1 = new SingleBigListWorker(4 * 1234 * 1234, 8567, false);
        SingleBigListWorker listWorker2 = new SingleBigListWorker(4 * 1345 * 1345, 4345, true);
    }
}
