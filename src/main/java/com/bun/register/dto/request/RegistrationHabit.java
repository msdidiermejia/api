package com.bun.register.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Hace referencia al objeto que contiene la información de lo hábitos transaccionales para el registro de clientes")
public class RegistrationHabit {

	@Schema(description = "Código del tipo de personalización correspondiente.", 
			requiredMode = RequiredMode.REQUIRED,
			example = "1")
    @NotNull(message = "ERR_NOTBLANK:registrationHabit.idHabits")
    @Min(value = 0, message = "ERR_SIZE:registrationHabit.idHabits")
    @Max(value = 99, message = "ERR_SIZE:registrationHabit.idHabits")
	private Integer idHabits;
	
	@Schema(description = "Código Tipo transacción a personalizar", 
			requiredMode = RequiredMode.REQUIRED,
			example = "1")
    @NotNull(message = "ERR_NOTBLANK:registrationHabit.habitTypeCodeTrx")
    @Min(value = 0, message = "ERR_SIZE:registrationHabit.habitTypeCodeTrx")
    @Max(value = 99, message = "ERR_SIZE:registrationHabit.habitTypeCodeTrx")
	private Integer habitTypeCodeTrx;
	
	@Schema(description = "Descripción tipo personalización", 
			requiredMode = RequiredMode.REQUIRED,
			example = "Transferencias a cuentas giros y finanzas")
    @NotBlank(message = "ERR_NOTBLANK:registrationHabit.descriptionHabits")
    @Size(max = 150, message = "ERR_SIZE:registrationHabit.descriptionHabits")
	private String descriptionHabits;
	
	@Schema(description = "Numero máximo asociado al tipo de personalización", 
			requiredMode = RequiredMode.REQUIRED,
			example = "5")
    @NotNull(message = "ERR_NOTBLANK:registrationHabit.maximumNumberTrx")
    @Min(value = 0, message = "ERR_SIZE:registrationHabit.maximumNumberTrx")
    @Max(value = 99, message = "ERR_SIZE:registrationHabit.maximumNumberTrx")
	private Integer maximumNumberTrx;
	
	@Schema(description = "Monto máximo asociado a al tipo de personalización", 
			requiredMode = RequiredMode.REQUIRED,
			example = "3000000")
    @NotNull(message = "ERR_NOTBLANK:registrationHabit.maximumAmountTrx")
    @Min(value = 0, message = "ERR_SIZE:registrationHabit.maximumAmountTrx")
    @Max(value = 9999999999L, message = "ERR_SIZE:registrationHabit.maximumAmountTrx")
	private Long maximumAmountTrx;
}
