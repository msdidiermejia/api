package com.bun.register.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bun.register.dto.request.*;
import com.bun.register.dto.response.InquiryCustomerRegisterSuccessfullDTO;
import com.bun.register.dto.response.RegisterClientSuccessfullDTO;
import com.bun.register.service.IRegisterService;

@ExtendWith(MockitoExtension.class)
class RegisterControllerTest {

    @Mock
    private IRegisterService service;

    @InjectMocks
    private RegisterController controller;

    private RegisterClientRequest registerClientRequest;
    private InquiryCustomerRegisterDTO inquiryRequest;

    @BeforeEach
    void setUp() {
        registerClientRequest = new RegisterClientRequest();
        ContextTransactionDTO ctx = new ContextTransactionDTO();
        ctx.setIdTx("test-tx-id");
        ctx.setIdUser("CC123456789");
        ctx.setIdConsumer("APPOMNI");
        ctx.setIdService("REGISTER");
        ctx.setCodTypeTx("REG");
        ctx.setDateTx("2024-08-08T10:08:25-05:00");
        registerClientRequest.setContextTransaction(ctx);

        inquiryRequest = new InquiryCustomerRegisterDTO();
        inquiryRequest.setContextTransaction(ctx);
    }

    @Test
    void testRegisterClient_ReturnsOk() {
        // Arrange
        RegisterClientSuccessfullDTO successResponse = new RegisterClientSuccessfullDTO();
        when(service.registerClient(any(RegisterClientRequest.class))).thenReturn(successResponse);

        // Act
        ResponseEntity<Object> response = controller.registerClient(registerClientRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponse, response.getBody());
        verify(service).registerClient(registerClientRequest);
    }

    @Test
    void testInquiryCustomerRegister_ReturnsOk() {
        // Arrange
        InquiryCustomerRegisterSuccessfullDTO successResponse = new InquiryCustomerRegisterSuccessfullDTO();
        when(service.inquiryCustomerRegister(any(InquiryCustomerRegisterDTO.class))).thenReturn(successResponse);

        // Act
        ResponseEntity<Object> response = controller.inquiryCustomerRegister(inquiryRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponse, response.getBody());
        verify(service).inquiryCustomerRegister(inquiryRequest);
    }

    @Test
    void testRegisterClient_ServiceReturnsNull_ResponseBodyIsNull() {
        // Arrange
        when(service.registerClient(any(RegisterClientRequest.class))).thenReturn(null);

        // Act
        ResponseEntity<Object> response = controller.registerClient(registerClientRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testInquiryCustomerRegister_ServiceReturnsNull_ResponseBodyIsNull() {
        // Arrange
        when(service.inquiryCustomerRegister(any(InquiryCustomerRegisterDTO.class))).thenReturn(null);

        // Act
        ResponseEntity<Object> response = controller.inquiryCustomerRegister(inquiryRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}
