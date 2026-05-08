package com.bun.register.service;

import com.bun.register.dto.request.RegisterClientRequest;
import com.bun.register.dto.request.InquiryCustomerRegisterDTO;


public interface IRegisterService {
    public Object registerClient(RegisterClientRequest req);
public Object inquiryCustomerRegister(InquiryCustomerRegisterDTO req);

}

