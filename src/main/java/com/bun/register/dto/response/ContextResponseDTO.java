package com.bun.register.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class ContextResponseDTO {
    private String idTx;
    private String codStateTx;
    private String dateTx;
}
