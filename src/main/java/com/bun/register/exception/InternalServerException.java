package com.bun.register.exception;

import com.bun.register.dto.response.ErrorExceptionResponseDto;

public class InternalServerException extends RuntimeException {
    private final transient ErrorExceptionResponseDto response;

    public InternalServerException(ErrorExceptionResponseDto response) {
        this.response = response;
    }

    public ErrorExceptionResponseDto getResponse() {
        return response;
    }
}
