package com.bun.register.util;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.AuditObjDTO;
import com.bun.register.dto.request.ContextTransactionDTO;
import com.bun.register.dto.request.DeviceInfoDTO;
import com.bun.register.service.IAuditService;

@ExtendWith(MockitoExtension.class)
class LogAuditUtilTest {

    @Mock
    private IAuditService iAuditService;

    @Mock
    private EnviromentPropertyConfig environmentPropertyConfig;

    @InjectMocks
    private LogAuditUtil logAuditUtil;

    private AuditObjDTO auditObjDTO;
    private MessagesLoggerUtil messagesLoggerUtil;

    @BeforeEach
    void setUp() {
        ContextTransactionDTO contextTransaction = new ContextTransactionDTO();
        contextTransaction.setIdTx("test-tx-id");
        contextTransaction.setIdUser("CC123456789");
        contextTransaction.setIdConsumer("APPOMNI");
        contextTransaction.setIdService("REGISTER");
        contextTransaction.setCodTypeTx("REG");
        contextTransaction.setDateTx("2024-08-08T10:08:25-05:00");

        DeviceInfoDTO deviceInfo = new DeviceInfoDTO();
        deviceInfo.setIp("191.156.5.107");
        deviceInfo.setDevice("Android");
        deviceInfo.setChannel("MOBILE_CHANNEL");
        deviceInfo.setApplication("PGI_APP");

        auditObjDTO = new AuditObjDTO();
        auditObjDTO.setContextTransaction(contextTransaction);
        auditObjDTO.setDeviceInfo(deviceInfo);
        auditObjDTO.setMetodo("registerClient");
        auditObjDTO.setRequest("{\"test\":\"request\"}");
        auditObjDTO.setResponse("{\"test\":\"response\"}");

        messagesLoggerUtil = new MessagesLoggerUtil();

        when(environmentPropertyConfig.getAuditServiceService()).thenReturn("SrvAPIRegister");
        when(iAuditService.auditData(anyString(), anyString())).thenReturn("{\"request\":{}, \"response\":{}}");
    }

    @Test
    void testWritelogAndAudit_CallsAuditService() {
        // Act
        logAuditUtil.writelogAndAudit(auditObjDTO, "200", "POST", "registerClient", messagesLoggerUtil);

        // Assert
        verify(iAuditService).auditData(anyString(), anyString());
        verify(iAuditService).save(eq(auditObjDTO), eq("POST"), eq("200"), eq("registerClient"), anyString(), eq(messagesLoggerUtil));
    }

    @Test
    void testWritelogAndAudit_WithNullRequest_DoesNotThrow() {
        // Arrange
        auditObjDTO.setRequest(null);
        auditObjDTO.setResponse(null);

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            logAuditUtil.writelogAndAudit(auditObjDTO, "200", "POST", "registerClient", messagesLoggerUtil);
        });
    }
}
