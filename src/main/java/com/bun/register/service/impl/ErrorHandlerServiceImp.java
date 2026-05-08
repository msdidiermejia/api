package com.bun.register.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.AuditObjDTO;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.service.IAuditRequest;
import com.bun.register.service.IErrorHandlerService;
import com.bun.register.util.BuildAuditObjectUtil;
import com.bun.register.util.Constants;
import com.bun.register.util.LogAuditUtil;
import com.bun.register.util.MessagesLoggerUtil;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@Service
public class ErrorHandlerServiceImp implements IErrorHandlerService {
    private final EnviromentPropertyConfig environmentPropertyConfig;
    private final BuildAuditObjectUtil buildAuditObjectUtil;
    private final MessagesLoggerUtil loggerUtil;
    private final LogAuditUtil auditUtil;

    public ErrorHandlerServiceImp(EnviromentPropertyConfig environmentPropertyConfig, BuildAuditObjectUtil buildAuditObjectUtil, MessagesLoggerUtil loggerUtil, LogAuditUtil auditUtil) {
        this.environmentPropertyConfig = environmentPropertyConfig;
        this.buildAuditObjectUtil = buildAuditObjectUtil;
        this.loggerUtil = loggerUtil;
        this.auditUtil = auditUtil;
    }

    public ResponseEntity<Object> handleBodyMissing(HttpMessageNotReadableException ex, HandlerMethod handlerMethod) {
        List<ErrorDTO> errors = new ArrayList<>();
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException ife && !ife.getPath().isEmpty()) {
            String field = ife.getPath().stream()
                    .map(ref -> ref.getFieldName() != null ? ref.getFieldName() : "[" + ref.getIndex() + "]")
                    .reduce((a, b) -> a + "." + b)
                    .orElse("unknown");
            errors.add(new ErrorDTO(
                    environmentPropertyConfig.getErrorBusinessEmptyField(),
                    String.format(environmentPropertyConfig.getMessageFormat(), field)));
        } else if (cause instanceof MismatchedInputException mie && !mie.getPath().isEmpty()) {
            String field = mie.getPath().stream()
                    .map(ref -> ref.getFieldName() != null ? ref.getFieldName() : "[" + ref.getIndex() + "]")
                    .reduce((a, b) -> a + "." + b)
                    .orElse("unknown");
            errors.add(new ErrorDTO(
                    environmentPropertyConfig.getErrorBusinessEmptyField(),
                    String.format(environmentPropertyConfig.getMessageEmpty(), field)));
        } else {
            errors.add(new ErrorDTO(
                    environmentPropertyConfig.getErrorBusinessEmptyField(),
                    String.format(environmentPropertyConfig.getMessageEmpty(), "request")));
        }

        AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(null, null, errors, handlerMethod.getMethod().getName());
        return responseHandler(auditObjDTO, environmentPropertyConfig.getAuditServiceService(), environmentPropertyConfig.getAuditServiceStatus400(), environmentPropertyConfig.getAuditServiceMethodPost());
    }

    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex, HandlerMethod handlerMethod) {
        Object target = ex.getBindingResult().getTarget();
        IAuditRequest originalReq = (IAuditRequest) target;
        List<ErrorDTO> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String defaultMessage = error.getDefaultMessage();
            loggerUtil.info("N/A", "handleValidation", Constants.LEVEL_ERROR,
                    "FieldError - field: [" + error.getField() + "] message: [" + defaultMessage + "]");
            if (defaultMessage == null || !defaultMessage.contains(":")) {
                errors.add(new ErrorDTO(
                        environmentPropertyConfig.getErrorBusinessEmptyField(),
                        String.format(environmentPropertyConfig.getMessageEmpty(), error.getField())));
                return;
            }
            String[] parts = defaultMessage.split(":", 2);
            String code = parts[0];
            String field = parts[1];
            switch (code) {
                case "ERR_NOTBLANK":
                    errors.add(new ErrorDTO(
                            environmentPropertyConfig.getErrorBusinessEmptyField(),
                            String.format(environmentPropertyConfig.getMessageEmpty(), field)));
                    break;
                case "ERR_FORMAT":
                    errors.add(new ErrorDTO(
                            environmentPropertyConfig.getErrorBusinessEmptyField(),
                            String.format(environmentPropertyConfig.getMessageFormat(), field)));
                    break;
                case "ERR_SIZE":
                    errors.add(new ErrorDTO(
                            environmentPropertyConfig.getErrorBusinessEmptyField(),
                            String.format(environmentPropertyConfig.getMessageSize(), field)));
                    break;
                default:
                    loggerUtil.info("N/A", "handleValidation", Constants.LEVEL_ERROR,
                            "Unhandled validation code: [" + code + "] field: [" + field + "]");
                    errors.add(new ErrorDTO(
                            environmentPropertyConfig.getErrorBusinessEmptyField(),
                            String.format(environmentPropertyConfig.getMessageEmpty(), field)));
                    break;
            }
        });
        AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(originalReq, null, errors, handlerMethod.getMethod().getName());
        return responseHandler(auditObjDTO, environmentPropertyConfig.getAuditServiceService(), environmentPropertyConfig.getAuditServiceStatus400(), environmentPropertyConfig.getAuditServiceMethodPost());
    }

    public ResponseEntity<Object> responseHandler(AuditObjDTO auditObjDTO, String auditServiceService, String auditServiceStatus, String auditServiceMethod){
        loggerUtil.info(auditObjDTO.getContextTransaction().getIdTx(), auditServiceService, Constants.LEVEL_ERROR, "Error de Validacion:  " + auditObjDTO.getResponse());
        auditUtil.writelogAndAudit(auditObjDTO, auditServiceStatus,auditServiceMethod,auditObjDTO.getMetodo(), loggerUtil);
        return new ResponseEntity<>(auditObjDTO.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
