package com.bun.register.service.impl;

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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.AuditObjDTO;
import com.bun.register.dto.request.ContextTransactionDTO;
import com.bun.register.dto.response.ContextResponseDTO;
import com.bun.register.dto.response.ErrorExceptionResponseDto;
import com.bun.register.util.BuildAuditObjectUtil;
import com.bun.register.util.LogAuditUtil;
import com.bun.register.util.MessagesLoggerUtil;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ErrorHandlerServiceImpTest {

    @Mock
    private EnviromentPropertyConfig environmentPropertyConfig;

    @Mock
    private BuildAuditObjectUtil buildAuditObjectUtil;

    @Mock
    private MessagesLoggerUtil loggerUtil;

    @Mock
    private LogAuditUtil auditUtil;

    @InjectMocks
    private ErrorHandlerServiceImp errorHandlerService;

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

        ContextResponseDTO contextResponse = new ContextResponseDTO();
        contextResponse.setIdTx("test-tx-id");
        contextResponse.setCodStateTx("PF");

        ErrorExceptionResponseDto errorResponse = new ErrorExceptionResponseDto(contextResponse, List.of());

        auditObjDTO = new AuditObjDTO();
        auditObjDTO.setContextTransaction(contextTransaction);
        auditObjDTO.setResponse(errorResponse);
        auditObjDTO.setMetodo("testMethod");

        when(environmentPropertyConfig.getAuditServiceService()).thenReturn("SrvAPIRegister");
        when(environmentPropertyConfig.getAuditServiceStatus400()).thenReturn("400");
        when(environmentPropertyConfig.getAuditServiceMethodPost()).thenReturn("POST");
        when(environmentPropertyConfig.getErrorBusinessEmptyField()).thenReturn("E001");
        when(environmentPropertyConfig.getMessageEmpty()).thenReturn("Field %s is empty");
        when(environmentPropertyConfig.getMessageFormat()).thenReturn("Field %s has invalid format");
        when(environmentPropertyConfig.getMessageSize()).thenReturn("Field %s exceeds max size");
        when(buildAuditObjectUtil.buildAuditObject(any(), any(), any(), anyString())).thenReturn(auditObjDTO);
    }

    @Test
    void testResponseHandler_ReturnsBadRequest() {
        // Act
        ResponseEntity<Object> result = errorHandlerService.responseHandler(
                auditObjDTO,
                "SrvAPIRegister",
                "400",
                "POST");

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(auditUtil).writelogAndAudit(any(), anyString(), anyString(), anyString(), any());
    }

    @Test
    void testResponseHandler_ResponseBodyIsAuditResponse() {
        // Act
        ResponseEntity<Object> result = errorHandlerService.responseHandler(
                auditObjDTO,
                "SrvAPIRegister",
                "400",
                "POST");

        // Assert
        assertNotNull(result);
        assertEquals(auditObjDTO.getResponse(), result.getBody());
    }

    @Test
    void testResponseHandler_CallsLoggerUtil() {
        // Act
        errorHandlerService.responseHandler(auditObjDTO, "SrvAPIRegister", "400", "POST");

        // Assert
        verify(loggerUtil).info(anyString(), anyString(), anyString(), anyString());
    }
}
