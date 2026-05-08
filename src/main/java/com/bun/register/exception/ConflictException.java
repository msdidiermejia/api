package com.bun.register.exception;

import com.bun.register.dto.response.ErrorExceptionResponseDto;

public class ConflictException extends RuntimeException {

    private final transient ErrorExceptionResponseDto response;

    public ConflictException(ErrorExceptionResponseDto response) {
        this.response = response;
    }

    public ErrorExceptionResponseDto getResponse() {
        return response;
    }
}
