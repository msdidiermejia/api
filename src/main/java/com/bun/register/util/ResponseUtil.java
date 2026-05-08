package com.bun.register.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.dto.response.ContextResponseDTO;
import com.bun.register.dto.response.ErrorDTO;
import com.bun.register.dto.response.ErrorExceptionResponseDto;
import com.bun.register.dto.response.InfoAccessCustomer;
import com.bun.register.dto.response.RegisterClientSuccessfullDTO;
import com.bun.register.dto.response.InquiryCustomerRegisterResponseDTO;
import com.bun.register.dto.response.InquiryCustomerRegisterSuccessfullDTO;


@Component
public class ResponseUtil {

    private EnviromentPropertyConfig environmentPropertyConfig;

    public ResponseUtil(EnviromentPropertyConfig environmentPropertyConfig){
        this.environmentPropertyConfig = environmentPropertyConfig;
    }

    public RegisterClientSuccessfullDTO successResponseRegisterClient (String idTx) {
    	return new RegisterClientSuccessfullDTO(true ,contextResponse(idTx, "PS"));
    }
	public InquiryCustomerRegisterSuccessfullDTO successResponseInquiryCustomerRegister (String idTx, InfoAccessCustomer infoAccessCustomer) {
	    return new InquiryCustomerRegisterSuccessfullDTO(true, contextResponse(idTx, "PS"), infoAccessCustomer);
	}

    public ErrorExceptionResponseDto badRequestResponse(List<ErrorDTO> errors, String idTx) {
        return new ErrorExceptionResponseDto(contextResponse(idTx, "PF"), errors);
    }

    public ErrorExceptionResponseDto internalServerErrorResponse(String idTx, List<ErrorDTO> errors) {
        if(errors == null) {
            errors = new ArrayList<>();
            errors.add(new ErrorDTO(environmentPropertyConfig.getErrorInternalServerError(), environmentPropertyConfig.getMessageInternalServerError()));
        }
        return new ErrorExceptionResponseDto(contextResponse(idTx, "PF"), errors);
    }

    public ErrorExceptionResponseDto timeOutErrorResponse(String idTx, List<ErrorDTO> errors) {
        if(errors == null) {
            errors = new ArrayList<>();
            errors.add(new ErrorDTO(environmentPropertyConfig.getErrorInternalTimeout(), environmentPropertyConfig.getMessageTimeout()));
        }
        return new ErrorExceptionResponseDto(contextResponse(idTx, "PF"), errors);
    }

    public ErrorExceptionResponseDto conflictResponse(String idTx, List<ErrorDTO> errors) {
        if(errors == null) {
            errors = new ArrayList<>();
            errors.add(new ErrorDTO(environmentPropertyConfig.getErrorClientNotFound(), environmentPropertyConfig.getMessageClientNotFound()));
        }
        return new ErrorExceptionResponseDto(contextResponse(idTx, "PF"), errors);
    }

    public ErrorExceptionResponseDto notFoundResponse(String idTx, List<ErrorDTO> errors) {
        if(errors == null) {
            errors = new ArrayList<>();
        }
        return new ErrorExceptionResponseDto(contextResponse(idTx, "PF"), errors);
    }

    private String dateFormat() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return zonedDateTime.format(formatter);
    }

    private ContextResponseDTO contextResponse(String idTx, String codState) {
        ContextResponseDTO contextTransactionResponse = new ContextResponseDTO();
        contextTransactionResponse.setIdTx(idTx);
        contextTransactionResponse.setCodStateTx(codState);
        contextTransactionResponse.setDateTx(dateFormat());
        return contextTransactionResponse;
    }
}
