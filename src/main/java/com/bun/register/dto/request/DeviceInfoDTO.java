package com.bun.register.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "DeviceInfo", description = "Objeto que contiene datos del dispositivo móvil.")
public class DeviceInfoDTO {

    @NotBlank(message = "ERR_NOTBLANK:deviceInfo.ip")
    @Schema(description = "Dirección IP del dispositivo móvil.", example = "###.###.###.###")
    private String ip;

    @Schema(description = "Dispositivo Móvil.", example = "Android")
    private String device;

    @NotBlank(message = "ERR_NOTBLANK:deviceInfo.channel")
    @Schema(description = "Canal digital.", example = "MOBILE_CHANNEL")
    private String channel;

    @Schema(description = "Aplicación desde donde se realiza el consumo.", example = "PGI_APP")
    private String application;

    public DeviceInfoDTO deepCopy() {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(gson.toJson(this), DeviceInfoDTO.class);
    }
}
