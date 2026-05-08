        package com.bun.register.service;

        import com.bun.register.dto.request.ContextTransactionDTO;
        import com.bun.register.dto.request.DeviceInfoDTO;

        public interface IAuditRequest {
            ContextTransactionDTO getContextTransaction();
            DeviceInfoDTO getDeviceInfo();
}
