package ru.javawebinar.topjava.service.rules;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @author Alex A.
 * @version 1.0
 *          4/15/2017.
 */
public class TestStatisticLogging extends ExternalResource {
    private Map<String, Long> testTimeMap;
    private Logger LOG;

    public TestStatisticLogging(Map<String, Long> testTimeMap, Logger LOG) {
        this.testTimeMap = testTimeMap;
        this.LOG = LOG;
    }

    @Override
    protected void after() {
        testTimeMap.forEach((name, time) -> LOG.info("{} - {} milliseconds",name, time));
    }
}
