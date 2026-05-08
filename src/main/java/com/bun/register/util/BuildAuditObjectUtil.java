package com.bun.register.util;

import java.util.List;
import org.springframework.stereotype.Component;
import com.bun.register.dto.AuditObjDTO;
import com.bun.register.dto.request.ContextTransactionDTO;
import com.bun.register.dto.request.DeviceInfoDTO;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.service.IAuditRequest;

@Component
public class BuildAuditObjectUtil {

    private final ResponseUtil responseUtil;

    public BuildAuditObjectUtil(ResponseUtil responseUtil){
        this.responseUtil = responseUtil;
    }

    public AuditObjDTO buildAuditObject(IAuditRequest originalReq,Object response, List<ErrorDTO> errors, String method){

        AuditObjDTO auditObjDTO = new AuditObjDTO();
        auditObjDTO.setContextTransaction(contexTransactionAudit(originalReq));
        auditObjDTO.setDeviceInfo(deviceAudit(originalReq));
        auditObjDTO.setRequest(originalReq);
        auditObjDTO.setMetodo(method);
        if(errors != null){
            auditObjDTO.setResponse(responseAudit(auditObjDTO.getContextTransaction().getIdTx(), errors));
        }else{
            auditObjDTO.setResponse(response);
        }
        return auditObjDTO;
    }

    private Object responseAudit(String idTx, List<ErrorDTO> errors){
        return responseUtil.badRequestResponse(errors, idTx);
    }

    private ContextTransactionDTO contexTransactionAudit(IAuditRequest originalReq){
        ReqFieldEmptyAuditUtil utilReq = new ReqFieldEmptyAuditUtil();
        if(originalReq != null){
            return utilReq.contextCatalogReq(originalReq.getContextTransaction());
        }
        return utilReq.contextCatalogReq(null);
    }

    private DeviceInfoDTO deviceAudit(IAuditRequest originalReq){
        ReqFieldEmptyAuditUtil utilReq = new ReqFieldEmptyAuditUtil();
        if(originalReq != null){
            return utilReq.deviceInfoCatalogReq(originalReq.getDeviceInfo());
        }
        return utilReq.deviceInfoCatalogReq(null);
    }
}
