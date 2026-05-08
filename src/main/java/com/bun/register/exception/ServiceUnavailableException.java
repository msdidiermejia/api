package com.bun.register.exception;

import com.bun.register.dto.response.ErrorExceptionResponseDto;

public class ServiceUnavailableException extends RuntimeException {
    private final transient ErrorExceptionResponseDto response;

    public ServiceUnavailableException(ErrorExceptionResponseDto response) {
        this.response = response;
    }

    public ErrorExceptionResponseDto getResponse() {
        return response;
    }
}
