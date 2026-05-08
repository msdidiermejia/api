package com.bun.register.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "Agent", description = "Objeto que contiene la información del agente, punto de servicio y caja del usuario quien ejecuta la operación.")
public class AgentDTO {

    @Schema(description = "Código CNB desde donde se origina la solicitud.", example = "GYF004")
    @NotBlank(message = "ERR_NOTBLANK:agent.id")
    private String id;

    @NotNull(message = "ERR_NOTBLANK:agent.office")
    @Valid
    private OfficeDTO office;
}
