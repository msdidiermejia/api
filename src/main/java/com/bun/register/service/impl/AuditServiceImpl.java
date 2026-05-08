package com.bun.register.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.AuditDTO;
import com.bun.register.dto.AuditObjDTO;
import com.bun.register.dto.request.DeviceInfoDTO;
import com.bun.register.service.IAuditService;
import com.bun.register.util.Constants;
import com.bun.register.util.MessagesLoggerUtil;
import com.bun.register.util.TimeStampUtil;

@Service
public class AuditServiceImpl implements IAuditService {

    private EnviromentPropertyConfig environmentPropertyConfig;
    private MessagesLoggerUtil messagesLoggerUtil;

    public AuditServiceImpl(EnviromentPropertyConfig environmentPropertyConfig, MessagesLoggerUtil messagesLoggerUtil){
        this.environmentPropertyConfig = environmentPropertyConfig;
        this.messagesLoggerUtil = messagesLoggerUtil;
    }

    @Override
    @Async
    public void save(AuditObjDTO auditObject, String method, String status, String process, String data, MessagesLoggerUtil loggerUtil) {
        AuditDTO audit = setAudit(method, auditObject.getContextTransaction().getIdTx(), status, process, data, auditObject);
        setAuditService(auditObject.getContextTransaction().getIdTx(), audit, loggerUtil);
    }

    private AuditDTO setAudit(String method, String idTrx, String status, String process, String auditData, AuditObjDTO auditObject) {
        AuditDTO audit = new AuditDTO();
        String customerId = auditObject.getContextTransaction().getIdUser().substring(2, auditObject.getContextTransaction().getIdUser().length());
        if(customerId.trim().equals("ENVIA")) {
            customerId = "NO ENVIA";
        }
        try {
            DeviceInfoDTO deviceInfo = auditObject.getDeviceInfo();
            String tyeIdentification = auditObject.getContextTransaction().getIdUser().substring(0, 2);
            audit.setService(environmentPropertyConfig.getAuditServiceService());
            audit.setStatus(status);
            audit.setChannel(deviceInfo.getChannel());
            audit.setApplication(deviceInfo.getApplication());
            audit.setProcessAudit(process);
            audit.setProcessDate(TimeStampUtil.getTimeStamp());
            audit.setIdTransaction(idTrx);
            audit.setIp(deviceInfo.getIp());
            audit.setMethod(method);
            audit.setData(auditData);
            audit.setDevice(deviceInfo.getDevice());
            audit.setDocumentType(tyeIdentification);
            audit.setDocumentId(customerId);
            return audit;

        } catch (Exception e) {
            messagesLoggerUtil.info(auditObject.getContextTransaction().getIdTx(), environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_ERROR, "Error:  " + e.getMessage());
            return audit;
        }
    }

    private void setAuditService(String idTrx, AuditDTO audit, MessagesLoggerUtil loggerUtil) {
        audit.setRegisterDate(TimeStampUtil.getTimeStamp());
        AuditServiceRunImpl auditService = new AuditServiceRunImpl(idTrx, audit, environmentPropertyConfig, loggerUtil);
        auditService.start();
    }

    @Override
    public String auditData(String jRequest, String jResponse) {
        return String.format("{\"request\": %s }, {\"response\": %s }", jRequest, jResponse);
    }
}

