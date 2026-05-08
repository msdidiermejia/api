package com.bun.register.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;

public interface IErrorHandlerService {
    public ResponseEntity<Object> handleBodyMissing(HttpMessageNotReadableException ex, HandlerMethod handlerMethod);
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex, HandlerMethod handlerMethod);
}
