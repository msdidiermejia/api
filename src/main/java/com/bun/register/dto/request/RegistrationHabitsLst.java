package com.bun.register.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto que contiene los habitos transacionales del cliente.")
public class RegistrationHabitsLst {
	
	@NotEmpty(message = "ERR_NOTBLANK:registrationHabits")
	@Size(max = 4, message = "ERR_SIZE:registrationHabits")
    @Valid
    @Schema(requiredMode = RequiredMode.REQUIRED)
	private List<RegistrationHabit> registrationHabits;
}
