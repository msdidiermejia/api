package com.bun.register.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Configuration
@EnableConfigurationProperties
@Getter
@Setter
//@PropertySource("file:${propertiesPath}")
public class EnviromentPropertyConfig {
    
	@Value("${timeout}")
    private String timeout;

    @Value("${patterFormatDateContext}")
    private String patterFormatDateContext;

    //MENSAJES DE ERROR

    @Value("${messageClientNotFound}")
    private String messageClientNotFound;

    @Value("${messageQuestsionIdRepet}")
    private String messageQuestsionIdRepet;
    
    @Value("${messageHabitsIdRepet}")
    private String messageHabitsIdRepet;

    @Value("${messageFormat}")
    private String messageFormat;
    
    @Value("${messageSize}")
    private String messageSize;

    @Value("${messageInternalServerError}")
    private String messageInternalServerError;

    @Value("${messageTimeout}")
    private String messageTimeout;

    @Value("${messageServiceUnavailable}")
    private String messageServiceUnavailable;
    
    @Value("${messageEmpty}")
    private String messageEmpty;


    //CODIGO DE ERROR

    @Value("${errorBusinessEmptyField}")
    private String errorBusinessEmptyField;

    @Value("${errorInternalServerError}")
    private String errorInternalServerError;

    @Value("${errorInternalTimeout}")
    private String errorInternalTimeout;

    @Value("${errorServiceUnavailable}")
    private String errorServiceUnavailable;
    
    @Value("${errorClientNotFound}")
    private String errorClientNotFound;
    
    @Value("${errorQuestionsIdRepet}")
    private String errorQuestionsIdRepet;
    
    @Value("${errorHabitsIdRepet}")
    private String errorHabitsIdRepet;

    @Value("${errorSaveHabits}")
    private String errorSaveHabits;

    @Value("${messageSaveHabits}")
    private String messageSaveHabits;

    @Value("${errorSaveQuestions}")
    private String errorSaveQuestions;

    @Value("${messageSaveQuestions}")
    private String messageSaveQuestions;

    @Value("${auditService.status.S404}")
    private String auditServiceStatus404;

    //ESTATUS DEL ERROR

    @Value("${auditService.status.S400}")
    private String auditServiceStatus400;
    
    @Value("${auditService.status.S200}")
    private String auditServiceStatus200;
    
    @Value("${auditService.status.S408}")
    private String auditServiceStatus408;
    
    @Value("${auditService.status.S500}")
    private String auditServiceStatus500;
    
    @Value("${auditService.status.S503}")
    private String auditServiceStatus503;
    
    @Value("${auditService.status.S409}")
    private String auditServiceStatus409;

    //DATOS SERVICIO

    @Value("${auditService.service}")
    private String auditServiceService;
    
    @Value("${auditService.processRegisterClient}")
    private String auditServiceProcessRegisterClient;
    
    @Value("${auditService.processInquiryCustomerRegister}")
    private String auditServiceProcessInquiryCustomerRegister;

    @Value("${auditService.method.post}")
    private String auditServiceMethodPost;

    //AUDITORIA

    @Value("${auditService.url}")
    private String auditServiceUrl;
    
    @Value("${region}")
    private String region;
    
    @Value("${secretId}")
    private String secretId;
    
    @Value("${userDB}")
    private String userDB;
    
    @Value("${passDB}")
    private String passDB;
    
    @Value("${urlDB}")
    private String urlDB;

    @Value("${flagInsertQuestionAndHabits}")
    private boolean flagInsertQuestionAndHabits;
	

}
