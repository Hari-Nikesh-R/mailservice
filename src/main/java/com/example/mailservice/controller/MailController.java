package com.example.mailservice.controller;

import com.example.mailservice.dtos.BaseResponse;
import com.example.mailservice.dtos.MailBody;
import com.example.mailservice.dtos.SmsBody;
import com.example.mailservice.services.MailService;
import com.example.mailservice.services.SmsService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static com.example.mailservice.utils.Constants.*;

@RestController
@RequestMapping(value = "/notify")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private SmsService smsService;

    @PostMapping(value = SEND_MAIL)
    public BaseResponse<?> sendEmail(@RequestBody MailBody mailBody){
        try{
            if(mailService.sendEmail(mailBody)){
                return new BaseResponse<>(MAIL_SUCCESS, HttpStatus.OK.value(), "", true, mailBody);
            }
            else{
                return new BaseResponse<>(MAIL_UNKNOWN_ERROR, HttpStatus.NO_CONTENT.value(), MAIL_UNSUCCESSFUL,false, null);
            }
        }
        catch (Exception exception){
            return new BaseResponse<>(MAIL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage(), false, null);
        }
    }
    @PostMapping(value = "/send-sms")
    public BaseResponse<?> sendSms(@RequestBody SmsBody smsBody){
        try{
            if(smsService.sendSms(smsBody)){
                return new BaseResponse<>("Sms send successfully", HttpStatus.OK.value(), "", true, smsBody);
            }
            else{
                return new BaseResponse<>("Something went wrong, unable to send sms", HttpStatus.NO_CONTENT.value(), "Mail not sent", false, null);
            }
        }
        catch (Exception exception){
            return new BaseResponse<>("Internal error, unable to send mail", HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage(), false, null);
        }
    }
}
