package com.cropsurvey.serviceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.cropsurvey.JWT.CustomerUserDetailsService;
import com.cropsurvey.JWT.JwtFilter;
import com.cropsurvey.JWT.JwtUtil;
import com.cropsurvey.constents.CropConstants;
import com.cropsurvey.model.User;
import com.cropsurvey.repository.UserRepo;
import com.cropsurvey.service.EmailService;
import com.cropsurvey.service.UserService;
import com.cropsurvey.utils.CropUtils;
import com.cropsurvey.utils.EmailUtils;
import com.google.common.base.Strings;




@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	CustomerUserDetailsService customerUserDetailsService;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	JwtFilter jwtFilter;
	@Autowired
	EmailService emailService;

	@Autowired
	EmailUtils emailUtils;
	
	
//	public void sendEmail(String email,String password) {
//		SimpleMailMessage message=new SimpleMailMessage();
//		message.setFrom("tb3.harshad.078@gmail.com");
//		message.setTo(email);
//		message.setText(password);
//		
//		mailSender.send(message);
//		System.out.println("Mail Sent Successfully");
//	}
	
	
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
	 
		try {
	 if(validateSignUpMap(requestMap)) {
		User user = userRepo.findByEmailId(requestMap.get("email"));
		if(Objects.isNull(user)) {
			userRepo.save(getUserFromMap(requestMap));
			return CropUtils.getResponseEntity("Successfully Registered.",HttpStatus.OK);
		}
		else {
			return CropUtils.getResponseEntity("Email Already Exists",HttpStatus.BAD_REQUEST);
		}
	 }
	 else {
			return CropUtils.getResponseEntity(CropConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
		}
		} catch(Exception ex) {
		 ex.printStackTrace();
	 }
		
	 
		return CropUtils.getResponseEntity(CropConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	
	 
	 

}
	private boolean validateSignUpMap(Map<String, String> requestMap) {
	if(	requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
		     && requestMap.containsKey("email") && requestMap.containsKey("password"))
	{
		return true;
	}
	    return false;
	}
	
	private User getUserFromMap(Map<String, String> requestMap) throws MessagingException{
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("true");
		user.setIsDeleted(false);
		
		
//		this.emailUtils.forgetMail(user.getEmail(), "Credentials by Crop Survey", user.getPassword());
		this.emailService.sendEmail("About your Login Credentials", user.getEmail(), user.getPassword());
		return user;
	}
	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {

		try {
			org.springframework.security.core.Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))) ;
			if(customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
			return new ResponseEntity<String>("{\"token\":\""+jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
				
					customerUserDetailsService.getUserDetail().getRole())+"\"}",HttpStatus.OK);
			}
		else {
			return new ResponseEntity<String>("{\"message\":\""+"Wait for admin Approval"+"\"}", HttpStatus.BAD_REQUEST);
		}
			
	}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials"+"\"}", HttpStatus.BAD_REQUEST);
	}
	@Override
	public ResponseEntity<List<User>> getAllUser() {
		try {
			if(jwtFilter.isAdmin()) {
				
				return new ResponseEntity<>(userRepo.findByIsDeleted(false),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<Void> deleteUserByID(Long id) {

		Optional<User> userDaoObj = userRepo.findById(id);
		if (userDaoObj.isPresent()) {
			User user = userDaoObj.get();
			user.setIsDeleted(true);
			userRepo.save(user);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@Override
	public ResponseEntity<User> inActiveUserById(Long id) {
		Optional<User> userDaoObj = userRepo.findById(id);
		if (userDaoObj.isPresent()) {
			User user = userDaoObj.get();
			user.setStatus("false");
			userRepo.save(user);
			return ResponseEntity.ok(user);
		}
		return null;
	}
	@Override
	public ResponseEntity<User> activeUserById(Long id) {
		Optional<User> userDaoObj = userRepo.findById(id);
		if (userDaoObj.isPresent()) {
			User user = userDaoObj.get();
			user.setStatus("true");
			userRepo.save(user);
			return ResponseEntity.ok(user);
		}
		return null;
	}
	@Override
	public ResponseEntity<String> forgetPassword(Map<String, String> requestMap) {
		
		 try {
	            User user = userRepo.findByEmail(requestMap.get("email"));
	            if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) 
	                emailUtils.forgetMail(user.getEmail(), "Credentials by Crop Survey", user.getPassword());
	                return CropUtils.getResponseEntity("Check your mail for Credentials", HttpStatus.OK);
	        }catch(Exception ex) {
	            ex.printStackTrace();
	        }
	        return CropUtils.getResponseEntity(CropConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
            User userObj = userRepo.findByEmail(jwtFilter.getCurrentUser());
            if(!userObj.equals(null)) {
                if(userObj.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObj.setPassword(requestMap.get("newPassword"));
                    userRepo.save(userObj);
                    return CropUtils.getResponseEntity("Password Updated Successfuly", HttpStatus.OK);
                }
                return CropUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
            }
            return CropUtils.getResponseEntity(CropConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception ex) {
            return CropUtils.getResponseEntity(CropConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}

//	
//	@Override
//public boolean sendEmail(String subject,String message,String to) {
//		
//		boolean f=false;
//		
//		String from="tb3.harshad.078@gmail.com";
//		// variable for gmail
//		String host="smtp.gmail.com";
//		
//		//get the system properties
//		Properties properties=System.getProperties();
//		System.out.println("PROPERTIES "+properties);
//		
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
////		properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); 
//
//		//step:1 to get seassion object
//		Session session = Session.getInstance(properties, new  Authenticator() {
//			
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("tb3.harshad.078@gmail.com","fkpzvsydmoqejccv");
//			}
//		});
//		session.setDebug(true);
//		
//		// step 2 : compose the message
//		MimeMessage m= new MimeMessage(session);
//		
//		try {
//			m.setFrom(from);
//			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			m.setSubject(subject);
//			m.setText(message);
//			
//			//step:3 send message using Transport class
//			Transport.send(m);
//			
//			System.out.println("sent Success......");
//			f=true;
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return f;
//	}
	
}
