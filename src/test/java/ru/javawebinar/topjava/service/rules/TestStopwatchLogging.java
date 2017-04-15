package ru.javawebinar.topjava.service.rules;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Alex A.
 * @version 1.0
 *          4/15/2017.
 */
public class TestStopwatchLogging extends Stopwatch {
    private Map<String, Long> testTimeMap;
    private Logger LOG;

    public TestStopwatchLogging(Map<String, Long> testTimeMap, Logger LOG) {
        this.testTimeMap = testTimeMap;
        this.LOG = LOG;
    }

    @Override
    protected void finished(long nanos, Description description) {
        long millis = TimeUnit.NANOSECONDS.toMillis(nanos);
        String displayName = description.getDisplayName();

        LOG.info("{} succeeded in {} ms", displayName, millis);
        testTimeMap.put(displayName, millis);
    }
}
