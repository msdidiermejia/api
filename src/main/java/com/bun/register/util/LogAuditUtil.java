package com.bun.register.util;

import org.springframework.stereotype.Component;
import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.AuditObjDTO;
import com.bun.register.service.IAuditService;
import com.google.gson.Gson;

@Component
public class LogAuditUtil {

    private IAuditService iAuditService;
    private EnviromentPropertyConfig environmentPropertyConfig;

    public LogAuditUtil(IAuditService iAuditService, EnviromentPropertyConfig environmentPropertyConfig) {
        this.iAuditService = iAuditService;
        this.environmentPropertyConfig = environmentPropertyConfig;
    }


    public void writelogAndAudit(AuditObjDTO objAudit, String status, String methodAudit, String service, MessagesLoggerUtil messagesLoggerUtil) {
        Gson gson = new Gson();
        messagesLoggerUtil.info(objAudit.getContextTransaction().getIdTx(), environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO, "FIN del Proceso  " + environmentPropertyConfig.getAuditServiceService() + "/" + objAudit.getMetodo());
        String auditData = iAuditService.auditData(gson.toJson(objAudit.getRequest()), gson.toJson(objAudit.getResponse()));
        iAuditService.save(objAudit, methodAudit, status, service, auditData, messagesLoggerUtil);
    }
}
