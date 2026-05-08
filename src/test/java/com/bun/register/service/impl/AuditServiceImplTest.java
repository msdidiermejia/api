package com.bun.register.service.impl;

import static org.junit.jupiter.api.Assertions.*;
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
import com.bun.register.util.MessagesLoggerUtil;

@ExtendWith(MockitoExtension.class)
@org.mockito.junit.jupiter.MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class AuditServiceImplTest {

    @Mock
    private EnviromentPropertyConfig environmentPropertyConfig;

    @Mock
    private MessagesLoggerUtil messagesLoggerUtil;

    @InjectMocks
    private AuditServiceImpl auditService;

    private AuditObjDTO auditObjDTO;

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

        when(environmentPropertyConfig.getAuditServiceService()).thenReturn("SrvAPIRegister");
        when(environmentPropertyConfig.getAuditServiceUrl()).thenReturn("http://audit-service/audit");
    }

    @Test
    void testAuditData_FormatsCorrectly() {
        // Arrange
        String jRequest = "{\"key\":\"value\"}";
        String jResponse = "{\"result\":\"ok\"}";

        // Act
        String result = auditService.auditData(jRequest, jResponse);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains(jRequest));
        assertTrue(result.contains(jResponse));
        assertTrue(result.contains("request"));
        assertTrue(result.contains("response"));
    }

    @Test
    void testAuditData_WithEmptyStrings_ReturnsFormattedString() {
        // Act
        String result = auditService.auditData("", "");

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("request"));
        assertTrue(result.contains("response"));
    }

    @Test
    void testSave_DoesNotThrowException() {
        // Act & Assert - save is async, just verify it doesn't throw
        assertDoesNotThrow(() -> {
            auditService.save(auditObjDTO, "POST", "200", "registerClient", "{}", messagesLoggerUtil);
        });
    }

    @Test
    void testSave_WithIdUserContainingEnvia_DoesNotThrow() {
        // Arrange - idUser that contains "ENVIA" after first 2 chars
        auditObjDTO.getContextTransaction().setIdUser("CCENVIA");

        // Act & Assert
        assertDoesNotThrow(() -> {
            auditService.save(auditObjDTO, "POST", "200", "registerClient", "{}", messagesLoggerUtil);
        });
    }

    @Test
    void testSave_WithNullDeviceInfo_DoesNotThrow() {
        // Arrange
        auditObjDTO.setDeviceInfo(null);

        // Act & Assert - should handle gracefully via catch block
        assertDoesNotThrow(() -> {
            auditService.save(auditObjDTO, "POST", "200", "registerClient", "{}", messagesLoggerUtil);
        });
    }
}
