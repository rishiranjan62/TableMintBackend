package com.tablemint.auth;

public interface OtpSender {

    void send(String phoneNumber,String otp);
}
