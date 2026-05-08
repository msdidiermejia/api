package com.bun.register.dto.request;


import com.bun.register.service.IAuditRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto que contiene la información del request del metodo registerClient")
public class RegisterClientRequest implements IAuditRequest {

    @NotNull(message = "ERR_NOTBLANK:contextTransaction")
    @Valid
    @Schema(requiredMode = RequiredMode.REQUIRED)
    private ContextTransactionDTO contextTransaction;

    @NotNull(message = "ERR_NOTBLANK:agent")
    @Valid
    @Schema(requiredMode = RequiredMode.REQUIRED)
    private AgentDTO agent;

    @NotNull(message = "ERR_NOTBLANK:registrationData")
    @Valid
    @Schema(requiredMode = RequiredMode.REQUIRED)
    private RegistrationData registrationData;

    @NotNull(message = "ERR_NOTBLANK:registrationHabitLst")
    @Valid
    @Schema(requiredMode = RequiredMode.REQUIRED)
    private RegistrationHabitsLst registrationHabitLst;

    @NotNull(message = "ERR_NOTBLANK:registrationQuestionLst")
    @Valid
    @Schema(requiredMode = RequiredMode.REQUIRED)
    private RegistrationQuestionLst registrationQuestionLst;

    private DeviceInfoDTO deviceInfo;
}
