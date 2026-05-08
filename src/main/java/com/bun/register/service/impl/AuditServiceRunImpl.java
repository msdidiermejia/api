package com.bun.register.service.impl;

import org.springframework.web.client.RestTemplate;
import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.AuditDTO;
import com.bun.register.util.Constants;
import com.bun.register.util.MessagesLoggerUtil;
import com.google.gson.Gson;

public class AuditServiceRunImpl extends Thread {
    private String api = "SrvAPIRegister";
    private Gson gson = new Gson();
    private AuditDTO audit;
    private  String url;
    private String idTrx;
    private MessagesLoggerUtil loggerUtil;

    public AuditServiceRunImpl(String idTrx, AuditDTO audit, EnviromentPropertyConfig properties, MessagesLoggerUtil loggerUtil) {
        this.url = properties.getAuditServiceUrl();
        this.audit = audit;
        this.idTrx = idTrx;
        this.loggerUtil = loggerUtil;
    }

    public void audit(){
        loggerUtil.info(null, api ,Constants.LEVEL_INFO, "************ Inicio Proceso de Auditoria ************");
        RestTemplate restTemplate = new RestTemplate();
        String jAudit = gson.toJson(audit);
        String jRequest = String.format("{\"requestAudit\": %s }",jAudit);
        loggerUtil.info(idTrx, api,Constants.LEVEL_INFO, "RequestAudit: " + jRequest);
        try {
            Object response = restTemplate.postForObject(url, audit, Object.class);
            String jResponse = gson.toJson(response);
            String jResponseAudit = String.format("{\"responseAudit\": %s }",jResponse);
            loggerUtil.info(idTrx, api,Constants.LEVEL_INFO, "ResponseAudit: " + jResponseAudit);
        } catch (Exception ex) {
            String jExceptionError = String.format("{\"auditException\": %s }",ex.getMessage());
            loggerUtil.info(idTrx, api,Constants.LEVEL_ERROR, "AuditException: " + jExceptionError);
        }
        loggerUtil.info(null, api,Constants.LEVEL_INFO, "************ Fin Proceso de Auditoria ************");
    }

    @Override
    public void run() {
        audit();
    }


}
