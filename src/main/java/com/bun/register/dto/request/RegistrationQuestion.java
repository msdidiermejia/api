package com.bun.register.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Objeto que tiene la informacion de cada pregunta de seguridad de un cliente.")
public class RegistrationQuestion {
	
	@Schema(description = "id de la pregunta", 
			requiredMode = RequiredMode.REQUIRED,
			example = "1")
    @NotBlank(message = "ERR_NOTBLANK:registrationQuestion.id")
    @Size(max = 20, message = "ERR_SIZE:registrationQuestion.id")
	private String id;
	
	@Schema(description = "descripcion de la pregunta", 
			example = "Pregunta de seguridad")
    private String description;
	
	@Schema(description = "codigo tipo pregunta", 
			example = "1")
    private String codTypeQuestion;
	
	@Schema(description = "codigo del estado de la pregunta", 
			example = "1")
    private String codState;
	
	@Schema(description = "codigo pregunta", 
			example = "1")
    private String codQuestion;
	
	@Schema(description = "respuesta", 
			requiredMode = RequiredMode.REQUIRED,
			example = "respuesta")
    @NotBlank(message = "ERR_NOTBLANK:registrationQuestion.answer")
    @Size(max = 200, message = "ERR_SIZE:registrationQuestion.answer")
	private String answer;

}
