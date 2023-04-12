package com.example.mailservice.services.impl;

import com.example.mailservice.dtos.MailBody;
import com.example.mailservice.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Boolean sendEmail(MailBody mailBody) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject(mailBody.getSubject());
            simpleMailMessage.setText(mailBody.getMessage());
            simpleMailMessage.setTo(mailBody.getRecipientEmail());
            if (Objects.nonNull(mailBody.getCc())) {
                simpleMailMessage.setCc(mailBody.getCc());
            }
            if (Objects.nonNull(mailBody.getBcc())) {
                simpleMailMessage.setBcc(mailBody.getBcc());
            }
            System.out.println("-> Sending mail with info "+ simpleMailMessage);
            mailSender.send(simpleMailMessage);
            trackSentEmail(simpleMailMessage);
            return true;
        }
        catch (Exception exception){
            System.out.println("-> Error caused due to "+exception.getMessage());
            return false;
        }
    }

    private void trackSentEmail(SimpleMailMessage simpleMailMessage) throws IOException {
        System.out.println("Writing file" + new File("EmailRecords.txt").getAbsoluteFile());
        FileWriter fileWriter = new FileWriter(new File("EmailRecords.json").getAbsoluteFile());
        System.out.println("Writing Completed "+ simpleMailMessage.toString());
        fileWriter.append(simpleMailMessage.getFrom()).append("\n").append(Arrays.toString(simpleMailMessage.getTo())).append("\n").append(simpleMailMessage.getSubject()).append("\n").append(simpleMailMessage.getText());
        fileWriter.append("========================\n");
        fileWriter.close();
    }
}
