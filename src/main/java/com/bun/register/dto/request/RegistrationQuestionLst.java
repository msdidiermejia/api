package com.bun.register.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Objeto que contiene las lista de preguntas de seguridad del cliente.")
public class RegistrationQuestionLst {
	
	@NotEmpty(message = "ERR_NOTBLANK:registrationQuestions")
	@Size(max = 3, message = "ERR_SIZE:registrationQuestions")
    @Valid
    @Schema(requiredMode = RequiredMode.REQUIRED)
	private List<RegistrationQuestion> registrationQuestions;

}
