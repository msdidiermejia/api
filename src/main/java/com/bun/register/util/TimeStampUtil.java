package com.bun.register.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public final class TimeStampUtil {

    private TimeStampUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String getTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp);
    }
}
