package com.bun.register.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.AuditObjDTO;
import com.bun.register.exception.BadRequestException;
import com.bun.register.exception.ConflictException;
import com.bun.register.exception.NotFoundException;
import com.bun.register.model.Usuario;
import com.bun.register.repository.PersonalizacionUsuarioRepository;
import com.bun.register.repository.PreguntaSeguridadUsuarioRepository;
import com.bun.register.repository.UsuarioRepository;
import com.bun.register.service.IRegisterService;
import com.bun.register.util.BuildAuditObjectUtil;
import com.bun.register.util.Constants;
import com.bun.register.util.EnmaskDataUtil;
import com.bun.register.util.LogAuditUtil;
import com.bun.register.util.MessagesLoggerUtil;
import com.bun.register.util.ReqFieldEmptyAuditUtil;
import com.bun.register.util.ResponseUtil;
import com.bun.register.util.UserMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bun.register.dto.request.RegisterClientRequest;
import com.bun.register.dto.request.InquiryCustomerRegisterDTO;
import com.bun.register.dto.request.RegistrationData;
import com.bun.register.dto.request.Customer;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.dto.response.InfoAccessCustomer;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Service
public class RegisterServiceImpl implements IRegisterService {

    private final MessagesLoggerUtil loggerUtil;
    private final EnviromentPropertyConfig environmentPropertyConfig;
    private final LogAuditUtil auditUtil;
    private final ResponseUtil responseUtil;
    private final BuildAuditObjectUtil buildAuditObjectUtil;
    private final UsuarioRepository usuarioRepository;
    private final PersonalizacionUsuarioRepository personalizacionUsuarioRepository;
    private final PreguntaSeguridadUsuarioRepository preguntaSeguridadUsuarioRepository;
    private final UserMapper userMapper;
    private final EnmaskDataUtil enmaskDataUtil;

    public RegisterServiceImpl(EnviromentPropertyConfig environmentPropertyConfig, MessagesLoggerUtil loggerUtil,
            LogAuditUtil auditUtil, ResponseUtil responseUtil, BuildAuditObjectUtil buildAuditObjectUtil,
            UsuarioRepository usuarioRepository,
            PersonalizacionUsuarioRepository personalizacionUsuarioRepository,
            PreguntaSeguridadUsuarioRepository preguntaSeguridadUsuarioRepository,
            UserMapper userMapper,EnmaskDataUtil enmaskDataUtil) {
        this.environmentPropertyConfig = environmentPropertyConfig;
        this.loggerUtil = loggerUtil;
        this.auditUtil = auditUtil;
        this.responseUtil = responseUtil;
        this.buildAuditObjectUtil = buildAuditObjectUtil;
        this.usuarioRepository = usuarioRepository;
        this.personalizacionUsuarioRepository = personalizacionUsuarioRepository;
        this.preguntaSeguridadUsuarioRepository = preguntaSeguridadUsuarioRepository;
        this.userMapper = userMapper;
        this.enmaskDataUtil = enmaskDataUtil;
    }

    @Override
    @Transactional
    public Object registerClient(RegisterClientRequest req) {
        loggerUtil.info(null, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                " ***************** Inicio del Proceso RegisterClient *********************");
        
        ReqFieldEmptyAuditUtil objUtilReq = new ReqFieldEmptyAuditUtil();
        RegisterClientRequest reqModifyAudit = new RegisterClientRequest();
        reqModifyAudit.setContextTransaction(objUtilReq.contextCatalogReq(req.getContextTransaction()));
        reqModifyAudit.setDeviceInfo(objUtilReq.deviceInfoCatalogReq(req.getDeviceInfo()));
        reqModifyAudit.setAgent(req.getAgent());
        reqModifyAudit.setRegistrationHabitLst(req.getRegistrationHabitLst());
        reqModifyAudit.setRegistrationQuestionLst(req.getRegistrationQuestionLst());
        RegistrationData registrationDataMasked = new Gson().fromJson(
                new Gson().toJson(req.getRegistrationData()), RegistrationData.class);
        registrationDataMasked.setId(enmaskDataUtil.maskValue(registrationDataMasked.getId()));
        reqModifyAudit.setRegistrationData(registrationDataMasked);
        String idTx = reqModifyAudit.getContextTransaction().getIdTx();
        String jsonRequestCloudWatch = new Gson().toJson(reqModifyAudit, new TypeToken<Object>() {}.getType());
        loggerUtil.info(idTx, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                "Request Inicial:  " + jsonRequestCloudWatch);

        // Validar ids duplicados en hábitos
        List<Integer> habitIds = req.getRegistrationHabitLst().getRegistrationHabits().stream()
                .map(h -> h.getIdHabits())
                .toList();
        Set<Integer> habitIdsSet = new HashSet<>(habitIds);
        if (habitIdsSet.size() < habitIds.size()) {
            List<ErrorDTO> errors = List.of(new ErrorDTO(
                    environmentPropertyConfig.getErrorHabitsIdRepet(),
                    environmentPropertyConfig.getMessageHabitsIdRepet()));
            AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, null, errors,
                    environmentPropertyConfig.getAuditServiceProcessRegisterClient());
            auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus400(),
                    environmentPropertyConfig.getAuditServiceMethodPost(),
                    environmentPropertyConfig.getAuditServiceProcessRegisterClient(), loggerUtil);
            throw new BadRequestException(responseUtil.badRequestResponse(errors, idTx));
        }

        // Validar ids duplicados en preguntas
        List<String> questionIds = req.getRegistrationQuestionLst().getRegistrationQuestions().stream()
                .map(q -> q.getId())
                .toList();
        Set<String> questionIdsSet = new HashSet<>(questionIds);
        if (questionIdsSet.size() < questionIds.size()) {
            List<ErrorDTO> errors = List.of(new ErrorDTO(
                    environmentPropertyConfig.getErrorQuestionsIdRepet(),
                    environmentPropertyConfig.getMessageQuestsionIdRepet()));
            AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, null, errors,
                    environmentPropertyConfig.getAuditServiceProcessRegisterClient());
            auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus400(),
                    environmentPropertyConfig.getAuditServiceMethodPost(),
                    environmentPropertyConfig.getAuditServiceProcessRegisterClient(), loggerUtil);
            throw new BadRequestException(responseUtil.badRequestResponse(errors, idTx));
        }

        // Validar si el usuario ya existe
        String numeroDocumento = req.getRegistrationData().getId();
        if (usuarioRepository.existsByNumeroDocumento(numeroDocumento)) {
            List<ErrorDTO> errors = List.of(new ErrorDTO(
                    environmentPropertyConfig.getErrorClientNotFound(),
                    environmentPropertyConfig.getMessageClientNotFound()));
            AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, null, errors,
                    environmentPropertyConfig.getAuditServiceProcessRegisterClient());
            auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus409(),
                    environmentPropertyConfig.getAuditServiceMethodPost(),
                    environmentPropertyConfig.getAuditServiceProcessRegisterClient(), loggerUtil);
            throw new ConflictException(responseUtil.conflictResponse(idTx, errors));
        }

        // Guardar usuario
        Usuario usuario = userMapper.toModel(req.getRegistrationData());
        usuarioRepository.save(usuario);
        loggerUtil.info(idTx, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                "Usuario guardado: " + usuario.getNumeroDocumento());

        // Guardar hábitos y preguntas según bandera
        if (environmentPropertyConfig.isFlagInsertQuestionAndHabits()) {
            personalizacionUsuarioRepository.deleteByNumeroDocumento(numeroDocumento);
            try {
                req.getRegistrationHabitLst().getRegistrationHabits().forEach(habit ->
                    personalizacionUsuarioRepository.save(UserMapper.map(habit, req.getRegistrationData())));
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                loggerUtil.info(idTx, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                        "Error al guardar hábitos para: " + numeroDocumento + " - " + e.getMessage());
                List<ErrorDTO> errors = List.of(new ErrorDTO(
                        environmentPropertyConfig.getErrorSaveHabits(),
                        environmentPropertyConfig.getMessageSaveHabits()));
                AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, null, errors,
                        environmentPropertyConfig.getAuditServiceProcessRegisterClient());
                auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus404(),
                        environmentPropertyConfig.getAuditServiceMethodPost(),
                        environmentPropertyConfig.getAuditServiceProcessRegisterClient(), loggerUtil);
                throw new NotFoundException(responseUtil.notFoundResponse(idTx, errors));
            }
            loggerUtil.info(idTx, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                    "Hábitos guardados para: " + numeroDocumento);

            preguntaSeguridadUsuarioRepository.deleteByNumeroDocumento(numeroDocumento);
            try {
                req.getRegistrationQuestionLst().getRegistrationQuestions().forEach(question ->
                    preguntaSeguridadUsuarioRepository.save(UserMapper.map(question, req.getRegistrationData())));
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                loggerUtil.info(idTx, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                        "Error al guardar preguntas para: " + numeroDocumento + " - " + e.getMessage());
                List<ErrorDTO> errors = List.of(new ErrorDTO(
                        environmentPropertyConfig.getErrorSaveQuestions(),
                        environmentPropertyConfig.getMessageSaveQuestions()));
                AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, null, errors,
                        environmentPropertyConfig.getAuditServiceProcessRegisterClient());
                auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus404(),
                        environmentPropertyConfig.getAuditServiceMethodPost(),
                        environmentPropertyConfig.getAuditServiceProcessRegisterClient(), loggerUtil);
                throw new NotFoundException(responseUtil.notFoundResponse(idTx, errors));
            }
            loggerUtil.info(idTx, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                    "Preguntas guardadas para: " + numeroDocumento);
        }

        // Auditoría éxito
        Object successResponse = responseUtil.successResponseRegisterClient(idTx);
        AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, successResponse, null,
                environmentPropertyConfig.getAuditServiceProcessRegisterClient());
        auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus200(),
                environmentPropertyConfig.getAuditServiceMethodPost(),
                environmentPropertyConfig.getAuditServiceProcessRegisterClient(), loggerUtil);

        return successResponse;
    }

    @Override
    public Object inquiryCustomerRegister(InquiryCustomerRegisterDTO req) {
        loggerUtil.info(null, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                " ***************** Inicio del Proceso InquiryCustomerRegister *********************");
        
        ReqFieldEmptyAuditUtil objUtilReq = new ReqFieldEmptyAuditUtil();
        InquiryCustomerRegisterDTO reqModifyAudit = new InquiryCustomerRegisterDTO();
        reqModifyAudit.setContextTransaction(objUtilReq.contextCatalogReq(req.getContextTransaction()));
        reqModifyAudit.setDeviceInfo(objUtilReq.deviceInfoCatalogReq(req.getDeviceInfo()));
        reqModifyAudit.setAgent(req.getAgent());
        Customer customerMasked = new Gson().fromJson(
                new Gson().toJson(req.getCustomer()), Customer.class);
        customerMasked.setId(enmaskDataUtil.maskValue(customerMasked.getId()));
        reqModifyAudit.setCustomer(customerMasked);
        String idTx = reqModifyAudit.getContextTransaction().getIdTx();
        String jsonRequestCloudWatch = new Gson().toJson(reqModifyAudit, new TypeToken<Object>() {}.getType());
        loggerUtil.info(idTx, environmentPropertyConfig.getAuditServiceService(), Constants.LEVEL_INFO,
                "Request Inicial:  " + jsonRequestCloudWatch);

        // Consultar si el usuario existe por tipoDocumento y numeroDocumento
        String numeroDocumento = req.getCustomer().getId();
        String tipoDocumento = req.getCustomer().getTypeIdentification();
        Usuario usuario = usuarioRepository.findByNumeroDocumentoAndTipoDocumento(numeroDocumento, tipoDocumento);
        if (usuario == null) {
            List<ErrorDTO> errors = List.of(new ErrorDTO(
                    environmentPropertyConfig.getErrorClientNotFound(),
                    environmentPropertyConfig.getMessageClientNotFound()));
            AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, null, errors,
                    environmentPropertyConfig.getAuditServiceProcessInquiryCustomerRegister());
            auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus409(),
                    environmentPropertyConfig.getAuditServiceMethodPost(),
                    environmentPropertyConfig.getAuditServiceProcessInquiryCustomerRegister(), loggerUtil);
            throw new ConflictException(responseUtil.conflictResponse(idTx, errors));
        }

        // Mapear entity a response
        InfoAccessCustomer infoAccessCustomer = userMapper.toInfoAccessCustomer(usuario);
        Object successResponse = responseUtil.successResponseInquiryCustomerRegister(idTx, infoAccessCustomer);

        // Auditoría éxito
        AuditObjDTO auditObjDTO = buildAuditObjectUtil.buildAuditObject(reqModifyAudit, successResponse, null,
                environmentPropertyConfig.getAuditServiceProcessInquiryCustomerRegister());
        auditUtil.writelogAndAudit(auditObjDTO, environmentPropertyConfig.getAuditServiceStatus200(),
                environmentPropertyConfig.getAuditServiceMethodPost(),
                environmentPropertyConfig.getAuditServiceProcessInquiryCustomerRegister(), loggerUtil);

        return successResponse;
    }
}
