package ru.otus.sua.L05;

import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("PID=" + ManagementFactory.getRuntimeMXBean().getName());
        EveryMinuteGcStat statLogger = new EveryMinuteGcStat();
    }
}
