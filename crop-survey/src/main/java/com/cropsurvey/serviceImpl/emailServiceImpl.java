package com.cropsurvey.serviceImpl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.cropsurvey.service.EmailService;

@Service
public class emailServiceImpl implements EmailService{

	@Override
	public boolean sendEmail(String subject, String message, String to) {
		boolean f=false;
		
		String from="tb3.harshad.078@gmail.com";
		// variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties=System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); 

		//step:1 to get seassion object
		Session session = Session.getInstance(properties, new  Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("tb3.harshad.078@gmail.com","fkpzvsydmoqejccv");
			}
		});
		session.setDebug(true);
		
		// step 2 : compose the message
		MimeMessage m= new MimeMessage(session);
		
		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			String htmlMsg = " <p><b>Login Details</b><br><b>Email: </b> " + to + "<br><b>Password: </b> " + message + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>" ;
		       m.setContent(htmlMsg,"text/html");
			//step:3 send message using Transport class
			Transport.send(m);
			
			System.out.println("sent Success......");
			f=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}


