//package com.marketing.app.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailServiceImpl implements EmailService {
//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	@Override
//	public void sendSimpleMail(String to, String sub, String body) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(to);
//		message.setSubject(sub);
//		message.setText(body);
//
//	 javaMailSender.send(message);
//		//JavaMailSender instance is injected into the EmailServiceImpl class. 
//		//The sendSimpleMail method creates a simple email message and uses the javaMailSender to send it.
//	    
//	}
//
//}
