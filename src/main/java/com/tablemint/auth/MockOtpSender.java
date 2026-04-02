package com.tablemint.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MockOtpSender implements OtpSender {

    @Override
    public void send(String phoneNumber, String otp) {
        {
            log.info("OTP for {} (dev only): {}", phoneNumber, otp);
        }
    }
}
