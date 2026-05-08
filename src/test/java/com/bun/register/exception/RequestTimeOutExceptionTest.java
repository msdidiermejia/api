package com.bun.register.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.bun.register.dto.response.ContextResponseDTO;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.dto.response.ErrorExceptionResponseDto;

class RequestTimeOutExceptionTest {

    @Test
    void testRequestTimeOutException() {
        ContextResponseDTO context = new ContextResponseDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorExceptionResponseDto dto = new ErrorExceptionResponseDto(context, errors);

        RequestTimeOutException ex = new RequestTimeOutException(dto);

        assertEquals(dto, ex.getResponse());
    }
}
