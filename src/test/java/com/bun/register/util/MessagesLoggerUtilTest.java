package com.bun.register.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessagesLoggerUtilTest {

    private static final String TEST_ID_TX = "TX123456";
    private static final String TEST_METHOD = "testMethod";
    private static final String TEST_MESSAGE = "Test message";

    private MessagesLoggerUtil messagesLoggerUtil;

    @BeforeEach
    void setUp() {
        messagesLoggerUtil = new MessagesLoggerUtil();
    }

    @Test
    void testInfoWithLevelInfo() {
        messagesLoggerUtil.info(TEST_ID_TX, TEST_METHOD, Constants.LEVEL_INFO, TEST_MESSAGE);
    }

    @Test
    void testInfoWithLevelWarn() {
        messagesLoggerUtil.info(TEST_ID_TX, TEST_METHOD, Constants.LEVEL_WARN, TEST_MESSAGE);
    }

    @Test
    void testInfoWithLevelError() {
        messagesLoggerUtil.info(TEST_ID_TX, TEST_METHOD, Constants.LEVEL_ERROR, TEST_MESSAGE);
    }

    @Test
    void testInfoWithLevelDebug() {
        messagesLoggerUtil.info(TEST_ID_TX, TEST_METHOD, Constants.LEVEL_DEBUG, TEST_MESSAGE);
    }

    @Test
    void testInfoWithUnknownLevel() {
        messagesLoggerUtil.info(TEST_ID_TX, TEST_METHOD, "UNKNOWN", TEST_MESSAGE);
    }

    @Test
    void testInfoWithNullIdTx() {
        messagesLoggerUtil.info(null, TEST_METHOD, Constants.LEVEL_INFO, TEST_MESSAGE);
    }

    @Test
    void testInfoWithNullMethod() {
        messagesLoggerUtil.info(TEST_ID_TX, null, Constants.LEVEL_INFO, TEST_MESSAGE);
    }

    @Test
    void testInfoWithNullMessage() {
        messagesLoggerUtil.info(TEST_ID_TX, TEST_METHOD, Constants.LEVEL_INFO, null);
    }

    @Test
    void testInfoWithEmptyStrings() {
        messagesLoggerUtil.info("", "", Constants.LEVEL_INFO, "");
    }

    @Test
    void testInfoWithLongMessage() {
        String longMessage = "A".repeat(1000);

        messagesLoggerUtil.info(TEST_ID_TX, TEST_METHOD, Constants.LEVEL_INFO, longMessage);
    }
}
