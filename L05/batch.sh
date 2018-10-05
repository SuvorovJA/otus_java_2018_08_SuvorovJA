#!/bin/sh

rm gc*.log


/usr/lib/jvm/10/bin/java -Xms1900m -Xmx1900m -XX:+UseConcMarkSweepGC  -jar target/L05.jar
grep 'EveryMinuteGcStat:33' gc1.log >>gc2.log
grep 'EveryMinuteGcStat:76' gc1.log >>gc2.log
rm gc1.log

/usr/lib/jvm/10/bin/java -Xms1900m -Xmx1900m  -jar target/L05.jar
grep 'EveryMinuteGcStat:33' gc1.log >>gc2.log
grep 'EveryMinuteGcStat:76' gc1.log >>gc2.log
rm gc1.log

/usr/lib/jvm/10/bin/java -Xms1900m -Xmx1900m -XX:+UseParallelGC -XX:+UseParallelOldGC -jar target/L05.jar
grep 'EveryMinuteGcStat:33' gc1.log >>gc2.log
grep 'EveryMinuteGcStat:76' gc1.log >>gc2.log
rm gc1.log

/usr/lib/jvm/10/bin/java -Xms1900m -Xmx1900m -XX:+UseSerialGC -jar target/L05.jar
grep 'EveryMinuteGcStat:33' gc1.log >>gc2.log
grep 'EveryMinuteGcStat:76' gc1.log >>gc2.log
rm gc1.log
