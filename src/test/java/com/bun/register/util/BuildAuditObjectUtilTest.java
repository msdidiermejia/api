package com.bun.register.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bun.register.dto.AuditObjDTO;
import com.bun.register.dto.request.ContextTransactionDTO;
import com.bun.register.dto.request.DeviceInfoDTO;
import com.bun.register.dto.request.InquiryCustomerRegisterDTO;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.dto.response.ErrorExceptionResponseDto;

@ExtendWith(MockitoExtension.class)
class BuildAuditObjectUtilTest {

    @Mock
    private ResponseUtil responseUtil;

    @InjectMocks
    private BuildAuditObjectUtil buildAuditObjectUtil;

    private InquiryCustomerRegisterDTO request;
    private ContextTransactionDTO contextTransaction;
    private DeviceInfoDTO deviceInfo;

    @BeforeEach
    void setUp() {
        contextTransaction = new ContextTransactionDTO();
        contextTransaction.setIdTx("test-tx-id");
        contextTransaction.setIdUser("CC123456789");
        contextTransaction.setIdConsumer("APPOMNI");
        contextTransaction.setIdService("REGISTER");
        contextTransaction.setCodTypeTx("REG");
        contextTransaction.setDateTx("2024-08-08T10:08:25-05:00");

        deviceInfo = new DeviceInfoDTO();
        deviceInfo.setIp("191.156.5.107");
        deviceInfo.setDevice("Android");
        deviceInfo.setChannel("MOBILE_CHANNEL");
        deviceInfo.setApplication("PGI_APP");

        request = new InquiryCustomerRegisterDTO();
        request.setContextTransaction(contextTransaction);
        request.setDeviceInfo(deviceInfo);
    }

    @Test
    void testBuildAuditObject_WithSuccessResponse_SetsResponseDirectly() {
        // Arrange
        Object successResponse = new Object();

        // Act
        AuditObjDTO result = buildAuditObjectUtil.buildAuditObject(request, successResponse, null, "testMethod");

        // Assert
        assertNotNull(result);
        assertEquals(successResponse, result.getResponse());
        assertEquals(request, result.getRequest());
        assertEquals("testMethod", result.getMetodo());
        assertNotNull(result.getContextTransaction());
        assertNotNull(result.getDeviceInfo());
    }

    @Test
    void testBuildAuditObject_WithErrors_CallsBadRequestResponse() {
        // Arrange
        List<ErrorDTO> errors = List.of(new ErrorDTO("E001", "Error message"));
        ErrorExceptionResponseDto errorResponse = mock(ErrorExceptionResponseDto.class);
        when(responseUtil.badRequestResponse(eq(errors), anyString())).thenReturn(errorResponse);

        // Act
        AuditObjDTO result = buildAuditObjectUtil.buildAuditObject(request, null, errors, "testMethod");

        // Assert
        assertNotNull(result);
        assertEquals(errorResponse, result.getResponse());
        verify(responseUtil).badRequestResponse(eq(errors), anyString());
    }

    @Test
    void testBuildAuditObject_WithNullRequest_UsesDefaultContextAndDevice() {
        // Arrange
        Object successResponse = new Object();

        // Act
        AuditObjDTO result = buildAuditObjectUtil.buildAuditObject(null, successResponse, null, "testMethod");

        // Assert
        assertNotNull(result);
        assertNotNull(result.getContextTransaction());
        assertNotNull(result.getDeviceInfo());
        assertNotNull(result.getContextTransaction().getIdTx());
    }

    @Test
    void testBuildAuditObject_ContextTransactionIsPopulated() {
        // Arrange
        Object successResponse = new Object();

        // Act
        AuditObjDTO result = buildAuditObjectUtil.buildAuditObject(request, successResponse, null, "testMethod");

        // Assert
        assertNotNull(result.getContextTransaction());
        assertEquals("test-tx-id", result.getContextTransaction().getIdTx());
    }

    @Test
    void testBuildAuditObject_DeviceInfoIsPopulated() {
        // Arrange
        Object successResponse = new Object();

        // Act
        AuditObjDTO result = buildAuditObjectUtil.buildAuditObject(request, successResponse, null, "testMethod");

        // Assert
        assertNotNull(result.getDeviceInfo());
        assertEquals("MOBILE_CHANNEL", result.getDeviceInfo().getChannel());
        assertEquals("PGI_APP", result.getDeviceInfo().getApplication());
    }
}
