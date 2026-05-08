package com.bun.register.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Hace referencia al objeto que contiene la información de registro del cliente en los canales")
public class RegistrationData {

    @Schema(description = "Hace referencia al tipo de documento del cliente CC, PA, CE y TI.", 
    		requiredMode = RequiredMode.REQUIRED,
    		example = "CC")
    @NotBlank(message = "ERR_NOTBLANK:registrationData.codTypeIdentification")
    @Size(max = 10, message = "ERR_SIZE:registrationData.codTypeIdentification")
    private String codTypeIdentification;

    @Schema(requiredMode = RequiredMode.REQUIRED, 
    		description = "Hace referencia al número de documento del cliente",
    		example = "1144198885")
    @NotBlank(message = "ERR_NOTBLANK:registrationData.id")
    @Size(max = 50, message = "ERR_SIZE:registrationData.id")
    private String id;

    @Schema(requiredMode = RequiredMode.REQUIRED, 
    		description = "Estado de la cuenta del usuario",
    		example = "1")
    @NotBlank(message = "ERR_NOTBLANK:registrationData.stateAccount")
    @Size(max = 50, message = "ERR_SIZE:registrationData.stateAccount")
    private String stateAccount;

    @Schema( 
    		description = "Dirección ip de la última conexión al portal",
    		example = "191.156.5.107")
    @Size(max = 50, message = "ERR_SIZE:registrationData.ipLastConnection")
    private String ipLastConnection;

    @Schema( 
    		description = "Fecha de la última conexión al portal",
    		example = "2025-09-05T14:35:49-05:00")
    @NotBlank(message = "ERR_NOTBLANK:registrationData.lastConnectionDate")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2}$",
            message = "ERR_FORMAT:registrationData.lastConnectionDate"
        )
    private String lastConnectionDate;

    @Schema( 
    		description = "Dirección ip de la conexión actual al portal",
    		example = "191.156.5.107")
    @Size(max = 50, message = "ERR_SIZE:registrationData.currentConnectionIp")
    private String currentConnectionIp;

    @Schema( 
    		description = "Fecha de la conexión actual al portal",
    		example = "2025-09-05T14:35:49-05:00")
    @NotBlank(message = "ERR_NOTBLANK:registrationData.currentConnectionDate")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2}$",
            message = "ERR_FORMAT:registrationData.currentConnectionDate"
        )
    private String currentConnectionDate;

    @Schema(
    		description = "id de sesión de la conexión actual",
    		example = "H8wCgzqfO4qNwcTsPKI6sPsF")
    @Size(max = 120, message = "ERR_SIZE:registrationData.sessionId")
    private String sessionId;

    @Schema(requiredMode = RequiredMode.REQUIRED, 
    		description = "Hace referencia al portal desde el cual se registra el cliente (App, WU y GYF)",
    		example = "2025-09-05T14:35:49-05:00")
    @NotBlank(message = "ERR_NOTBLANK:registrationData.registrationPortal")
    @Size(max = 10, message = "ERR_SIZE:codTypeIdentification.registrationPortal")
    private String registrationPortal;

    @Schema(requiredMode = RequiredMode.REQUIRED, 
    		description = "Fecha en la que el cliente realiza su proceso de registro exitoso al portal web",
    		example = "")
    @NotBlank(message = "ERR_NOTBLANK:registrationData.registrationDate")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2}$",
            message = "ERR_FORMAT:registrationData.registrationDate"
        )
    private String registrationDate;
    
}
