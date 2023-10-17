package com.cuckoofi.gatewayservice.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {

    public static String uuidGenerator() {
        return UUID.randomUUID().toString();
    }

    public static Date calculateExpiryDate(Integer expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return cal.getTime();
    }

    public static String getTimeStampString(){
        long timestamp = System.currentTimeMillis() / 1000;

        // Convert the timestamp to a string
        return Long.toString(timestamp);
    }

    public static void printThreads(){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);

        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("Thread ID: " + threadInfo.getThreadId());
            System.out.println("Thread Name: " + threadInfo.getThreadName());
            System.out.println("Thread State: " + threadInfo.getThreadState());
            // Add more thread information as needed
            System.out.println("--------------------------------------");
        }
    }
}