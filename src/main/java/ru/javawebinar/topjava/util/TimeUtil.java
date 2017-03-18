package ru.javawebinar.topjava.util;

import java.time.LocalTime;

/**
 * Time util class. Has some methods to work with classes from {@link java.time} package.
 *
 * @author GKislin
 * @version 07.01.2015
 *
 * @author of JavaDoc Aksionshik A.
 * @version 18.03.2017
 */
public class TimeUtil {

    /**
     * Check if specified time is in specified range of time;
     * @param lt - time, which should be checked;
     * @param startTime - the begin of time range;
     * @param endTime - the end of time range;
     * @return - true, if specified time is in range, else - return false;
     */
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
