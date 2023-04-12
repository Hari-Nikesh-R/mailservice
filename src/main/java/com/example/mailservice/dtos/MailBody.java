package com.example.mailservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailBody {
    private String recipientEmail;
    private String message;
    private String subject;
    private String signature;
    private String cc;
    private String bcc;
}
