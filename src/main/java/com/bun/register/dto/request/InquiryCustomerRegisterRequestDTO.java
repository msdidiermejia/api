package com.bun.register.dto.request;

import com.bun.register.service.IValidDateTx;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "InquiryCustomerRegister", description = "Descripción del método")
public class InquiryCustomerRegisterRequestDTO{

    @NotBlank(message = "ERR_NOTBLANK:tag.parameter")
    @Schema(description = "Descripción swaggere.", example = "123456789")
    @Size(max = 30, message = "ERR_SIZE:tag.parameter")
    @Pattern(regexp="^\\d*$", message = "ERR_FORMAT:tag.parameter")
    private String parameter;
}
