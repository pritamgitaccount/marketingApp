package com.marketing.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class MarketingApp3Application {

    public static void main(String[] args) {
        SpringApplication.run(MarketingApp3Application.class, args);
    }

    @Bean   // Use When third party library added @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();

    }
//	@Bean
//	public JavaMailSender javaMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//		// Set mail server properties
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("emailserviceapp8080@gmail.com");
//		mailSender.setPassword("ktmadubfygywipyu");
//		return mailSender;
//	}
}
