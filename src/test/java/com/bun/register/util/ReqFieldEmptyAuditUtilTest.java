package com.bun.register.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bun.register.dto.request.ContextTransactionDTO;
import com.bun.register.dto.request.DeviceInfoDTO;

class ReqFieldEmptyAuditUtilTest {

    private static final String DEFAULT_NO_ENVIA = "NO ENVIA";
    private static final String TEST_TX_ID = "TX123456";
    private static final String TEST_CONSUMER = "CONSUMER001";
    private static final String TEST_USER = "USER001";
    private static final String TEST_APP = "APP001";
    private static final String TEST_CHANNEL_WEB = "WEB";
    private static final String TEST_DEVICE_DESKTOP = "DESKTOP";
    private static final String TEST_DEVICE_MOBILE = "MOBILE";
    private static final String WHITESPACE = "   ";
    private static final String EMPTY_STRING = "";

    private ReqFieldEmptyAuditUtil reqFieldEmptyAuditUtil;

    @BeforeEach
    void setUp() {
        reqFieldEmptyAuditUtil = new ReqFieldEmptyAuditUtil();
    }

    @Test
    void testContextCatalogReqWithNullRequestReturnsDefaultValues() {
        ContextTransactionDTO result = reqFieldEmptyAuditUtil.contextCatalogReq(null);

        assertNotNull(result);
        assertNotNull(result.getIdTx());
        assertEquals(DEFAULT_NO_ENVIA, result.getIdConsumer());
        assertEquals(DEFAULT_NO_ENVIA, result.getIdUser());
    }

    @Test
    void testContextCatalogReqWithCompleteRequestReturnsOriginalValues() {
        ContextTransactionDTO request = new ContextTransactionDTO();
        request.setIdTx(TEST_TX_ID);
        request.setIdConsumer(TEST_CONSUMER);
        request.setIdUser(TEST_USER);

        ContextTransactionDTO result = reqFieldEmptyAuditUtil.contextCatalogReq(request);

        assertNotNull(result);
        assertEquals(TEST_TX_ID, result.getIdTx());
        assertEquals(TEST_USER, result.getIdUser());
    }

    @Test
    void testContextCatalogReqWithEmptyIdTxGeneratesUUID() {
        ContextTransactionDTO request = new ContextTransactionDTO();
        request.setIdTx(EMPTY_STRING);
        request.setIdUser(TEST_USER);

        ContextTransactionDTO result = reqFieldEmptyAuditUtil.contextCatalogReq(request);

        assertNotNull(result);
        assertNotNull(result.getIdTx());
        assertNotEquals(EMPTY_STRING, result.getIdTx());
        assertTrue(result.getIdTx().matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));
    }

    @Test
    void testContextCatalogReqWithNullIdUserReturnsDefaultValue() {
        ContextTransactionDTO request = new ContextTransactionDTO();
        request.setIdTx(TEST_TX_ID);
        request.setIdUser(null);

        ContextTransactionDTO result = reqFieldEmptyAuditUtil.contextCatalogReq(request);

        assertEquals(DEFAULT_NO_ENVIA, result.getIdUser());
    }

    @Test
    void testContextCatalogReqWithWhitespaceIdUserReturnsDefaultValue() {
        ContextTransactionDTO request = new ContextTransactionDTO();
        request.setIdTx(TEST_TX_ID);
        request.setIdUser(WHITESPACE);

        ContextTransactionDTO result = reqFieldEmptyAuditUtil.contextCatalogReq(request);

        assertEquals(DEFAULT_NO_ENVIA, result.getIdUser());
    }

    @Test
    void testDeviceInfoCatalogReqWithNullRequestReturnsDefaultValues() {
        DeviceInfoDTO result = reqFieldEmptyAuditUtil.deviceInfoCatalogReq(null);

        assertNotNull(result);
        assertEquals(DEFAULT_NO_ENVIA, result.getApplication());
        assertEquals(DEFAULT_NO_ENVIA, result.getChannel());
        assertEquals(DEFAULT_NO_ENVIA, result.getDevice());
    }

    @Test
    void testDeviceInfoCatalogReqWithCompleteRequestReturnsOriginalValues() {
        DeviceInfoDTO request = new DeviceInfoDTO();
        request.setApplication(TEST_APP);
        request.setChannel(TEST_CHANNEL_WEB);
        request.setDevice(TEST_DEVICE_DESKTOP);

        DeviceInfoDTO result = reqFieldEmptyAuditUtil.deviceInfoCatalogReq(request);

        assertNotNull(result);
        assertEquals(TEST_APP, result.getApplication());
        assertEquals(TEST_CHANNEL_WEB, result.getChannel());
        assertEquals(TEST_DEVICE_DESKTOP, result.getDevice());
    }

    @Test
    void testDeviceInfoCatalogReqWithNullFieldsReturnsDefaultValues() {
        DeviceInfoDTO request = new DeviceInfoDTO();
        request.setApplication(null);
        request.setChannel(null);
        request.setDevice(null);

        DeviceInfoDTO result = reqFieldEmptyAuditUtil.deviceInfoCatalogReq(request);

        assertEquals(DEFAULT_NO_ENVIA, result.getApplication());
        assertEquals(DEFAULT_NO_ENVIA, result.getChannel());
        assertEquals(DEFAULT_NO_ENVIA, result.getDevice());
    }

    @Test
    void testDeviceInfoCatalogReqWithEmptyFieldsReturnsDefaultValues() {
        DeviceInfoDTO request = new DeviceInfoDTO();
        request.setApplication(EMPTY_STRING);
        request.setChannel(WHITESPACE);
        request.setDevice(EMPTY_STRING);

        DeviceInfoDTO result = reqFieldEmptyAuditUtil.deviceInfoCatalogReq(request);

        assertEquals(DEFAULT_NO_ENVIA, result.getApplication());
        assertEquals(DEFAULT_NO_ENVIA, result.getChannel());
        assertEquals(DEFAULT_NO_ENVIA, result.getDevice());
    }

    @Test
    void testDeviceInfoCatalogReqWithMixedFieldsReturnsCorrectValues() {
        DeviceInfoDTO request = new DeviceInfoDTO();
        request.setApplication(TEST_APP);
        request.setChannel(EMPTY_STRING);
        request.setDevice(TEST_DEVICE_MOBILE);

        DeviceInfoDTO result = reqFieldEmptyAuditUtil.deviceInfoCatalogReq(request);

        assertEquals(TEST_APP, result.getApplication());
        assertEquals(DEFAULT_NO_ENVIA, result.getChannel());
        assertEquals(TEST_DEVICE_MOBILE, result.getDevice());
    }

    @Test
    void testContextCatalogReqGeneratesDifferentUUIDs() {
        ContextTransactionDTO result1 = reqFieldEmptyAuditUtil.contextCatalogReq(null);
        ContextTransactionDTO result2 = reqFieldEmptyAuditUtil.contextCatalogReq(null);

        assertNotEquals(result1.getIdTx(), result2.getIdTx());
    }
}
