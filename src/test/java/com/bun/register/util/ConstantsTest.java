package com.bun.register.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ConstantsTest {

    @Test
    void testConstantsCannotBeInstantiated() throws Exception {
        java.lang.reflect.Constructor<Constants> constructor =
                Constants.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        java.lang.reflect.InvocationTargetException exception = assertThrows(
                java.lang.reflect.InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof IllegalStateException);
    }

    @Test
    void testLevelInfoConstant() {
        assertEquals("INFO", Constants.LEVEL_INFO);
    }

    @Test
    void testLevelWarnConstant() {
        assertEquals("WARN", Constants.LEVEL_WARN);
    }

    @Test
    void testLevelErrorConstant() {
        assertEquals("ERROR", Constants.LEVEL_ERROR);
    }

    @Test
    void testLevelDebugConstant() {
        assertEquals("DEBUG", Constants.LEVEL_DEBUG);
    }

    @Test
    void testStructurLogConstant() {
        assertEquals("{} | {} | {} | {} | {}", Constants.STRUCTUR_LOG);
    }
}
