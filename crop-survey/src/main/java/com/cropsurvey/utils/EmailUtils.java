package com.cropsurvey.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class EmailUtils {

	@Autowired
	private JavaMailSender emailSender ;
	
	public void forgetMail(String to, String subject, String password) throws MessagingException{
	       MimeMessage message = emailSender.createMimeMessage();
	       MimeMessageHelper helper = new MimeMessageHelper(message, true);
	       helper.setFrom("tb3.harshad.078@gmail.com");
	       helper.setTo(to);
	       helper.setSubject(subject);
	       String htmlMsg = " <p><b>Your Login Credentials for Crop Survey</b><br><b>Email: </b> " + to + "<br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>" ;
	       message.setContent(htmlMsg,"text/html");
	       emailSender.send(message);
	  }
}
