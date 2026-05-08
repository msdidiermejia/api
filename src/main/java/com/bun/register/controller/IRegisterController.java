package com.bun.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import com.bun.register.dto.request.*;

public interface IRegisterController {

@OkResponseRegisterClient
@BadRequestResponse
@Timeout
@Unauthorized
@InternalServerError
@Operation(
    summary = "Operación para registerClient",
    description = "Servicio que permite realizar registerClient"
)
@PostMapping(value = "RegisterClient", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> registerClient (@Valid @RequestBody RegisterClientRequest request);

//#region Successful Response RegisterClient
@ApiResponse(
    responseCode = "200",
    description = "OK - Operación exitosa",
    content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject(
            name = "Respuesta Exitosa",
            value = """
            {
                "contextResponse": {
                    "idTx": "e4088d57-955d-46dc-b16d-312340790f22",
                    "codStateTx": "PS",
                    "dateTx": "2026-02-20T15:11:17-05:00"
                }
            }
            """
        )
    )
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface OkResponseRegisterClient {}
//#endregion

@OkResponseInquiryCustomerRegister
@BadRequestResponse
@Timeout
@Unauthorized
@InternalServerError
@Operation(
    summary = "Operación para inquiryCustomerRegister",
    description = "Servicio que permite realizar inquiryCustomerRegister"
)
@PostMapping(value = "InquiryCustomerRegister", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> inquiryCustomerRegister (@Valid @RequestBody InquiryCustomerRegisterDTO request);

//#region Successful Response InquiryCustomerRegister
@ApiResponse(
    responseCode = "200",
    description = "OK - Operación exitosa",
    content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject(
            name = "Respuesta Exitosa",
            value = """
            {
                "contextResponse": {
                    "idTx": "e4088d57-955d-46dc-b16d-312340790f22",
                    "codStateTx": "PS",
                    "dateTx": "2026-02-20T15:11:17-05:00"
                }
            }
            """
        )
    )
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface OkResponseInquiryCustomerRegister {}
//#endregion

    
//#region Bad Request
@ApiResponse(
    responseCode = "400",
    description = "BAD REQUEST - Error en la solicitud",
    content = @Content(
        mediaType = "application/json",
        examples = {
            @ExampleObject(
                name = "Campos Faltantes",
                description = "Ocurre cuando falta un campo obligatorio.",
                value = """
                {
                    "contextResponse": {
                        "idTx": "0d4fc229-71d7-4185-afac-4b8de8562f84",
                        "codStateTx": "PF",
                        "dateTx": "2026-02-20T15:12:58-05:00"
                    },
                    "error": [
                        {
                            "codError": "E001",
                            "descError": "Ocurrió un error en la ejecución del servicio"
                        }
                    ]
                }
                """
            )
        }
    )
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface BadRequestResponse {}
//#endregion

    
//#region Request Timeout
@ApiResponse(
    responseCode = "408",
    description = "REQUEST TIMEOUT - Tiempo de espera agotado",
    content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject(
            name = "Timeout",
            description = "Cuando el servicio no responde dentro del tiempo especificado.",
            value = """
            {
                "contextResponse": {
                    "idTx": "e2af6e9e-eddb-4bef-8fc1-0a2b5abf9ebc",
                    "codStateTx": "PF",
                    "dateTx": "2026-02-20T15:15:07-05:00"
                },
                "error": [
                    {
                        "codError": "E002",
                        "descError": "Solicitud agotada"
                    }
                ]
            }
            """
        )
    )
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface Timeout {}
//#endregion

    
//#region Unauthorized
@ApiResponse(
    responseCode = "401",
    description = "UNAUTHORIZED - Token Invalido",
    content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject(
            name = "Unauthorized",
            description = "Cuando el token expira o se envía un token invalido.",
            value = """
            {
                "contextResponse": {
                    "idTx": "e2af6e9e-eddb-4bef-8fc1-0a2b5abf9ebc",
                    "codStateTx": "PF",
                    "dateTx": "2026-02-20T15:15:07-05:00"
                },
                "error": [
                    {
                        "codError": "E003",
                        "descError": "Token no es valido"
                    }
                ]
            }
            """
        )
    )
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface Unauthorized {}
//#endregion

    
//#region Internal Server Error
@ApiResponse(
    responseCode = "500",
    description = "INTERNAL SERVER ERROR",
    content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject(
            name = "Error Interno",
            description = "Error interno del servidor.",
            value = """
            {
                "contextResponse": {
                    "idTx": "e2af6e9e-eddb-4bef-8fc1-0a2b5abf9ebc",
                    "codStateTx": "PF",
                    "dateTx": "2026-02-20T15:15:07-05:00"
                },
                "error": [
                    {
                        "codError": "E004",
                        "descError": "Ocurrió un error en la ejecucion del servicio externo"
                    }
                ]
            }
            """
        )
    )
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface InternalServerError {}
//#endregion

}
