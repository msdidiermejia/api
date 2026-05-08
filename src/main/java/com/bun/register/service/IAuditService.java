package com.bun.register.service;

import com.bun.register.dto.AuditObjDTO;
import com.bun.register.util.MessagesLoggerUtil;

public interface IAuditService {

    public void save(AuditObjDTO auditObject,String method,  String status, String process, String data, MessagesLoggerUtil loggerUtil);
    public String auditData(String jRequest, String jResponse);
}
