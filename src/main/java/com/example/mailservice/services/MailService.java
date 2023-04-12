package com.example.mailservice.services;

import com.example.mailservice.dtos.MailBody;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    Boolean sendEmail(MailBody mailBody);
}
