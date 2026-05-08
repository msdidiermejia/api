package com.bun.register.exception;

import com.bun.register.dto.response.ErrorExceptionResponseDto;

public class NotFoundException extends RuntimeException {

    private final transient ErrorExceptionResponseDto response;

    public NotFoundException(ErrorExceptionResponseDto response) {
        this.response = response;
    }

    public ErrorExceptionResponseDto getResponse() {
        return response;
    }
}
