package com.bun.register.util;

import java.util.UUID;
import com.bun.register.dto.request.ContextTransactionDTO;
import com.bun.register.dto.request.DeviceInfoDTO;

public class ReqFieldEmptyAuditUtil {
    private String emptyValue = "NO ENVIA";

    public ContextTransactionDTO contextCatalogReq(ContextTransactionDTO req){
        ContextTransactionDTO resp = new ContextTransactionDTO();
        if(req == null){
            addContextTransaction(resp);
        }else{
            resp = req.deepCopy();
            normalizeContext(req, resp);
        }
        return resp;
    }

    public DeviceInfoDTO deviceInfoCatalogReq(DeviceInfoDTO req){
        DeviceInfoDTO resp = new DeviceInfoDTO();
        if(req == null){
            addDeviceInfo(resp);
        }else{
            resp = req.deepCopy();
            normalizeDeviceInfo(req, resp);
        }
        return resp;
    }

    private void normalizeContext(ContextTransactionDTO req, ContextTransactionDTO resp){
        if(req == null) {
            addContextTransaction(resp);
            resp.setIdTx(getOrDefault(resp.getIdTx(),UUID.randomUUID().toString()));
            resp.setIdUser(getOrDefault(resp.getIdUser(),emptyValue));
        }else{
            resp.setIdTx(getOrDefault(req.getIdTx(),UUID.randomUUID().toString()));
            resp.setIdUser(getOrDefault(req.getIdUser(),emptyValue));
        }
    }

    private void normalizeDeviceInfo(DeviceInfoDTO req, DeviceInfoDTO resp){
        if(req == null) {
            addDeviceInfo(resp);
            resp.setApplication(getOrDefault(resp.getApplication(),emptyValue));
            resp.setChannel(getOrDefault(resp.getChannel(),emptyValue));
            resp.setDevice(getOrDefault(resp.getDevice(),emptyValue));
        }else{
            resp.setApplication(getOrDefault(req.getApplication(),emptyValue));
            resp.setChannel(getOrDefault(req.getChannel(),emptyValue));
            resp.setDevice(getOrDefault(req.getDevice(),emptyValue));
        }

    }

    private String getOrDefault(String value, String defaultValue) {
        return isEmpty(value) ? defaultValue : value;
    }

    private boolean isEmpty(String val) {
        return val == null || val.trim().isEmpty();
    }

    private void addContextTransaction(ContextTransactionDTO resp){
        resp.setIdTx(UUID.randomUUID().toString());
        resp.setIdConsumer(emptyValue);
        resp.setIdUser(emptyValue);
    }

    private void addDeviceInfo(DeviceInfoDTO resp){
        resp.setApplication(emptyValue);
        resp.setChannel(emptyValue);
        resp.setDevice(emptyValue);
    }

}
