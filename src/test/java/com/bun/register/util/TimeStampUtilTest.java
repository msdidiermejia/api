package com.bun.register.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeStampUtilTest {

    @Test
    void testGetTimeStamp_ReturnsNonNull() {
        String result = TimeStampUtil.getTimeStamp();
        assertNotNull(result);
    }

    @Test
    void testGetTimeStamp_MatchesExpectedFormat() {
        String result = TimeStampUtil.getTimeStamp();
        // Expected format: dd/MM/yyyy HH:mm:ss
        assertTrue(result.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"),
                "Timestamp should match format dd/MM/yyyy HH:mm:ss but was: " + result);
    }

    @Test
    void testGetTimeStamp_TwoCallsReturnDifferentOrSameTimestamp() {
        String result1 = TimeStampUtil.getTimeStamp();
        String result2 = TimeStampUtil.getTimeStamp();
        // Both should be valid timestamps
        assertNotNull(result1);
        assertNotNull(result2);
    }

    @Test
    void testTimeStampUtil_CannotBeInstantiated() {
        assertThrows(UnsupportedOperationException.class, () -> {
            java.lang.reflect.Constructor<TimeStampUtil> constructor =
                    TimeStampUtil.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            try {
                constructor.newInstance();
            } catch (java.lang.reflect.InvocationTargetException e) {
                throw (UnsupportedOperationException) e.getCause();
            }
        });
    }
}
