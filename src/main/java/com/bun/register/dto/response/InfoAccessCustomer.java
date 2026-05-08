package com.bun.register.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InfoAccessCustomer {

    @Schema(description = "Hace referencia al tipo de documento del cliente CC, PA, CE y TI.", requiredMode = RequiredMode.REQUIRED)
    private String codTypeIdentification;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Hace referencia al número de documento del cliente")
    private String id;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Estado de la cuenta del usuario")
    private String stateAccount;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Dirección ip de la última conexión al portal")
    private String ipLastConnection;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Fecha de la última conexión al portal")
    private String lastConnectionDate;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Dirección ip de la conexión actual al portal")
    private String currentConnectionIp;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Fecha de la conexión actual al portal")
    private String currentConnectionDate;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "id de sesión de la conexión actual")
    private String sessionId;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Hace referencia al portal desde el cual se registra el cliente (App, WU y GYF)")
    private String registrationPortal;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Fecha en la que el cliente realiza su proceso de registro exitoso al portal web")
    private String registrationDate;

}
