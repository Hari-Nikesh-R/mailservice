package com.example.mailservice.services;

import com.example.mailservice.dtos.SmsBody;

public interface SmsService {
    Boolean sendSms(SmsBody smsBody);
}
