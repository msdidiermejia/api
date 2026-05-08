package com.bun.register.dto.request;

import com.bun.register.service.IValidDateTx;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "ContextTransaction", description = "Objeto que contiene el contexto de la transacción.")
public class ContextTransactionDTO {

    @Schema(description = "El UUID utilizado para identificar cada transacción debe ser diferente y único para cada transacción.", example = "a4a62d19-ec33-4ef3-a29f-d9cbdfede57e")
    @NotBlank(message = "ERR_NOTBLANK:contextTransaction.idTx")
    private String idTx;

    @Schema(description = "Identificador del usuario que usa el Servicio", example = "CC123456789")
    @NotBlank(message = "ERR_NOTBLANK:contextTransaction.idUser")
    private String idUser;

    @Schema(description = "Identificación del sistema externo que consume el servicio", example = "APPOMNI")
    @NotBlank(message = "ERR_NOTBLANK:contextTransaction.idConsumer")
    private String idConsumer;

    @Schema(description = "Identificación del componente externo que consume el servicio", example = "APPOMNI")
    @NotBlank(message = "ERR_NOTBLANK:contextTransaction.idService")
    private String idService;

    @Schema(description = "Tipo de transacción", example = "REG")
    @NotBlank(message = "ERR_NOTBLANK:contextTransaction.codTypeTx")
    private String codTypeTx;

    @Schema(description = "Formato de fecha de transacción 'AAAA-MMDDThh:mm:ss±hh:mm", example = "2024-08-08T10:08:25-05:00")
    @NotBlank(message = "ERR_NOTBLANK:contextTransaction.dateTx")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2}$",
            message = "ERR_FORMAT:contextTransaction.dateTx"
        )
    private String dateTx;

    public ContextTransactionDTO deepCopy() {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(gson.toJson(this), ContextTransactionDTO.class);
    }
}
