package com.bun.register.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import com.bun.register.dto.response.ContextResponseDTO;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.dto.response.ErrorExceptionResponseDto;
import com.bun.register.service.IErrorHandlerService;

class GlobalExceptionHandlerTest {

    @Mock
    private IErrorHandlerService errorHandlerService;

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        handler = new GlobalExceptionHandler(errorHandlerService);
    }

    @Test
    void testHandleBodyMissing() {
        HttpMessageNotReadableException ex =
                new HttpMessageNotReadableException("body missing");

        HandlerMethod handlerMethod = mock(HandlerMethod.class);

        ResponseEntity<Object> response =
                ResponseEntity.badRequest().build();

        when(errorHandlerService.handleBodyMissing(ex, handlerMethod))
                .thenReturn(response);

        ResponseEntity<Object> result =
                handler.handleBodyMissing(ex, handlerMethod);

        assertEquals(response, result);
    }

    @Test
    void testHandleValidationExceptions() {
        MethodArgumentNotValidException ex =
                mock(MethodArgumentNotValidException.class);

        HandlerMethod handlerMethod = mock(HandlerMethod.class);

        ResponseEntity<Object> response =
                ResponseEntity.badRequest().build();

        when(errorHandlerService.handleValidation(ex, handlerMethod))
                .thenReturn(response);

        ResponseEntity<Object> result =
                handler.handleValidationExceptions(ex, handlerMethod);

        assertEquals(response, result);
    }

    @Test
    void testHandleInternalServer() {
        ContextResponseDTO context = new ContextResponseDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorExceptionResponseDto dto = new ErrorExceptionResponseDto(context, errors);

        InternalServerException ex =
                new InternalServerException(dto);

        ResponseEntity<Object> result =
                handler.handleInternalServer(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    void testHandleServiceUnavailable() {
        ContextResponseDTO context = new ContextResponseDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorExceptionResponseDto dto = new ErrorExceptionResponseDto(context, errors);

        ServiceUnavailableException ex =
                new ServiceUnavailableException(dto);

        ResponseEntity<Object> result =
                handler.handleServiceUnavailable(ex);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, result.getStatusCode());
    }

    @Test
    void testHandleTimeout() {
        ContextResponseDTO context = new ContextResponseDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorExceptionResponseDto dto = new ErrorExceptionResponseDto(context, errors);

        RequestTimeOutException ex =
                new RequestTimeOutException(dto);

        ResponseEntity<Object> result =
                handler.handleTimeout(ex);

        assertEquals(HttpStatus.REQUEST_TIMEOUT, result.getStatusCode());
    }

    @Test
    void testHandleBadRequest() {
        ContextResponseDTO context = new ContextResponseDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorExceptionResponseDto dto = new ErrorExceptionResponseDto(context, errors);

        BadRequestException ex =
                new BadRequestException(dto);

        ResponseEntity<Object> result =
                handler.handleBadResquest(ex);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testHandleConflict() {
        ContextResponseDTO context = new ContextResponseDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorExceptionResponseDto dto = new ErrorExceptionResponseDto(context, errors);

        ConflictException ex =
                new ConflictException(dto);

        ResponseEntity<Object> result =
                handler.handleConflict(ex);

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }
}
