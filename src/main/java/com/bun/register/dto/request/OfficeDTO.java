package com.bun.register.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "Office", description = "Objeto que contiene la información de la oficina desde donde se ejecuta la operación.")
public class OfficeDTO {

    @NotNull(message = "ERR_NOTBLANK:agent.office.servicePoint")
    @Valid
    private ServicePointDTO servicePoint;
}
