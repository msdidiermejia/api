package com.bun.register.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class AuditDTO {
    protected String service;
    protected String status;
    protected String channel;
    protected String application;
    protected String processAudit;
    protected String processDate;
    protected String registerDate;
    protected String idTransaction;
    protected String ip;
    protected String method;
    protected String data;
    protected String device;
    private String documentType;
    private String documentId;
}
