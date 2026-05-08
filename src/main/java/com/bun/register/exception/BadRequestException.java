package com.bun.register.exception;

import com.bun.register.dto.response.ErrorExceptionResponseDto;

public class BadRequestException extends RuntimeException {

    private final transient ErrorExceptionResponseDto response;

    public BadRequestException(ErrorExceptionResponseDto response) {
        this.response = response;
    }

    public ErrorExceptionResponseDto getResponse() {
        return response;
    }
}
