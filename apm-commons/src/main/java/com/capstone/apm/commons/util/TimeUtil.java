package com.capstone.apm.commons.util;

import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public static long convertNanoToMilli(long nanoTime) {
        return TimeUnit.MILLISECONDS.convert(nanoTime, TimeUnit.NANOSECONDS);
    }
}
