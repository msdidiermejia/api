package com.bun.register.dto.request;

import com.bun.register.service.IAuditRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@JsonInclude(value = Include.NON_NULL)
@Data
@Schema(name = "InquiryCustomerRegister", description = "Descipción del método")
public class InquiryCustomerRegisterDTO  implements IAuditRequest{

    @NotNull(message = "ERR_NOTBLANK:contextTransaction")
    @Valid
    private ContextTransactionDTO contextTransaction;

    @NotNull(message = "ERR_NOTBLANK:agent")
    @Valid
    private AgentDTO agent;

    @NotNull(message = "ERR_NOTBLANK:inquirycustomerregister")
    @Valid
    private Customer customer;

    private DeviceInfoDTO deviceInfo;

    @Override
    public ContextTransactionDTO getContextTransaction() { return contextTransaction; }

    @Override
    public DeviceInfoDTO getDeviceInfo() { return deviceInfo; }

    public InquiryCustomerRegisterDTO deepCopy() {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(gson.toJson(this), InquiryCustomerRegisterDTO.class);
    }
}
