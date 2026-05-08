package com.bun.register.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "ServicePoint", description = "Objeto que contiene la información del punto de servicio.")
public class ServicePointDTO {

    @Schema(description = "Código del punto de servicio.", example = "123")
    private String id;

    @NotNull(message = "ERR_NOTBLANK:agent.office.servicePoint.device")
    @Valid
    private DeviceDTO device;
}
