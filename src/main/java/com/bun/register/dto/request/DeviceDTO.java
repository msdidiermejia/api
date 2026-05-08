package com.bun.register.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "Device", description = "Objeto que contiene información del dispositivo.")
public class DeviceDTO {

    @Schema(description = "Identificador de dispositivo", example = "123")
    private String id;
}
