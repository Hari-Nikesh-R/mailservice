package com.example.mailservice.services.impl;

import com.example.mailservice.dtos.SmsBody;
import com.example.mailservice.services.SmsService;
import com.example.mailservice.utils.MailServiceProperties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private MailServiceProperties mailServiceProperties;

    @Override
    public Boolean sendSms(SmsBody smsBody) {
        Twilio.init(mailServiceProperties.getTwilioAuthSid(), mailServiceProperties.getTwilioAuth());
        Message.creator(new com.twilio.type.PhoneNumber(smsBody.getTo()),
                new PhoneNumber("+16204104426"), smsBody.getBody()).create();
        return true;
    }
}
