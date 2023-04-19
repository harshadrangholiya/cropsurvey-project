package com.cropsurvey.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cropsurvey.constents.CropConstants;
import com.cropsurvey.model.EmailRequest;
import com.cropsurvey.model.User;
import com.cropsurvey.service.EmailService;
import com.cropsurvey.service.UserService;
import com.cropsurvey.utils.CropUtils;

@RestController

@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;
	
		@PostMapping(path="/signup")	
	 public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap){
			try {
				return userService.signUp(requestMap);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return CropUtils.getResponseEntity(CropConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@RequestMapping(value="/sendemail",method=RequestMethod.POST)
		public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		
			System.out.println(request);
			boolean result=this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
			if(result) {
				return ResponseEntity.ok("Email is Sent Successfully...");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not sent");
			}
		}
//		 @PostMapping("/sendMail")
//		    public String
//		    sendMail(@RequestBody EmailRequest Request)
//		    {
//		        String status;
//				try {
//					status = UserService.sendSimpleMail(Request);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		 
//		        return status;
//		    }
//		
		@PostMapping(path ="/login")
		public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap) {
			try {
				return userService.login(requestMap);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return CropUtils.getResponseEntity(CropConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@GetMapping(path ="/get")
		public ResponseEntity<List<User>> getAllUser() {
			try {
				return userService.getAllUser();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return new ResponseEntity<List<User>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@DeleteMapping(path="/{id}")
		public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
			
			return userService.deleteUserByID(id);
		}
		
		@GetMapping(path="/{id}")
		public ResponseEntity<User> inActiveUser(@PathVariable Long id) {
			return userService.inActiveUserById(id);
		}
		
		@GetMapping(path="/active/{id}")
		public ResponseEntity<User> activeUser(@PathVariable Long id) {
			return userService.activeUserById(id);
		}
		
		@PostMapping(path="/changePassword")
		public ResponseEntity<String>changePassword(@RequestBody Map<String,String> requestMap){
			return userService.changePassword(requestMap);
		}
		
		@PostMapping(path = "/forgetPassword")
	    ResponseEntity<String> forgetPassword(@RequestBody Map<String, String> requestMap){
			  try {
		            return userService.forgetPassword(requestMap);
		        }catch (Exception ex){
		            
		        }
		        return CropUtils.getResponseEntity(CropConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

