package com.bun.register.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(value = Include.NON_NULL)
@Data
@NoArgsConstructor
public class ErrorDTO {

    public ErrorDTO(String codError, String descError) {
        this.codError = codError;
        this.descError = descError;
    }

    public ErrorDTO(String codError, String descError, String codMessage, String descMessage) {
        this.codError = codError;
        this.descError = descError;
        this.codMessage = codMessage;
        this.descMessage = descMessage;
    }

    private String codError;
    private String codTypeError;
    private String descError;
    private String codMessage;
    private String descMessage;
}
