package com.bun.register.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Hace referencia al objeto que contiene la información del cliente")
public class Customer {
	
	@Schema(description = "Hace referencia al tipo de documento del cliente CC, PA, CE y TI.", 
    		requiredMode = RequiredMode.REQUIRED,
    		example = "CC")
    @NotBlank(message = "ERR_NOTBLANK:customer.typeIdentification")
    private String typeIdentification;

    @Schema(requiredMode = RequiredMode.REQUIRED, 
    		description = "Hace referencia al número de documento del cliente",
    		example = "1144198885")
    @NotBlank(message = "ERR_NOTBLANK:customer.id")
    @Size(max = 11, message = "ERR_SIZE:customer.id")
    private String id;

}
