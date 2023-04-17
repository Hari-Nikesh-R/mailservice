package com.example.mailservice;

import com.example.mailservice.utils.MailServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MailserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailserviceApplication.class, args);
	}
	@Bean
	public MailServiceProperties getMailServiceProperties(){
		return new MailServiceProperties();
	}


}
