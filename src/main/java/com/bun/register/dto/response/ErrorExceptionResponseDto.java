package com.bun.register.dto.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@AllArgsConstructor
public class ErrorExceptionResponseDto {
    private ContextResponseDTO contextResponse;
    private List<ErrorDTO> error;
}
