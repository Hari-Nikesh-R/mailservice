package com.example.mailservice.controller;

import com.example.mailservice.dtos.BaseResponse;
import com.example.mailservice.dtos.MailBody;
import com.example.mailservice.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static com.example.mailservice.utils.Constants.*;

@RestController
@RequestMapping(value = SEND_MAIL)
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping
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
}
