package com.bun.register.dto;

import com.bun.register.dto.request.ContextTransactionDTO;
import com.bun.register.dto.request.DeviceInfoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class AuditObjDTO {
    private ContextTransactionDTO contextTransaction;
    private DeviceInfoDTO deviceInfo;
    private Object response;
    private Object request;
    private String metodo;
}

