package com.bun.register.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.bun.register.dto.request.*;
import com.bun.register.service.IRegisterService;

@RestController
public class RegisterController implements IRegisterController{

    private final IRegisterService service;

    public RegisterController(IRegisterService service) {
        this.service = service;
    }
    @PostMapping("/registerClient")
	public ResponseEntity<Object> registerClient(@Valid @RequestBody RegisterClientRequest request) {
	    return new ResponseEntity<>(service.registerClient(request), HttpStatus.OK);
	}
	@PostMapping("/inquiryCustomerRegister")
	public ResponseEntity<Object> inquiryCustomerRegister(@Valid @RequestBody InquiryCustomerRegisterDTO request) {
	    return new ResponseEntity<>(service.inquiryCustomerRegister(request), HttpStatus.OK);
	}
}
