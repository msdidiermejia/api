package com.bun.register.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.bun.register.dto.response.ContextResponseDTO;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.dto.response.ErrorExceptionResponseDto;

class ServiceUnavailableExceptionTest {

    @Test
    void testServiceUnavailableException() {
        ContextResponseDTO context = new ContextResponseDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorExceptionResponseDto dto = new ErrorExceptionResponseDto(context, errors);

        ServiceUnavailableException ex = new ServiceUnavailableException(dto);

        assertEquals(dto, ex.getResponse());
    }
}
