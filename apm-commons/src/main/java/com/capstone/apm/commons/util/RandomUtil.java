package com.capstone.apm.commons.util;

import java.util.UUID;

public class RandomUtil {

    public static String getRandomTraceId() {
        return UUID.randomUUID().toString();
    }
}
