package com.bun.register.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import com.bun.register.service.IErrorHandlerService;
import org.springframework.core.Ordered;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    private final IErrorHandlerService errorHandlerService;

    public GlobalExceptionHandler(IErrorHandlerService errorHandlerService) {
        this.errorHandlerService = errorHandlerService;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleBodyMissing(HttpMessageNotReadableException ex, HandlerMethod handlerMethod) {
        return errorHandlerService.handleBodyMissing(ex, handlerMethod);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, HandlerMethod handlerMethod) {
        return errorHandlerService.handleValidation(ex, handlerMethod);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Object> handleInternalServer(InternalServerException ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getResponse());
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailable(ServiceUnavailableException ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ex.getResponse());
    }

    @ExceptionHandler(RequestTimeOutException.class)
    public ResponseEntity<Object> handleTimeout(RequestTimeOutException ex) {

        return ResponseEntity
                .status(HttpStatus.REQUEST_TIMEOUT)
                .body(ex.getResponse());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadResquest(BadRequestException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getResponse());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflict(ConflictException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getResponse());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getResponse());
    }
}
