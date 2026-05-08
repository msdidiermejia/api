package com.bun.register.exception;

import com.bun.register.dto.response.ErrorExceptionResponseDto;

public class RequestTimeOutException extends RuntimeException {

    private final transient ErrorExceptionResponseDto response;

    public RequestTimeOutException(ErrorExceptionResponseDto response) {
        this.response = response;
    }

    public ErrorExceptionResponseDto getResponse() {
        return response;
    }
}
