package com.cropsurvey.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cropsurvey.model.User;




public interface UserService  {
	
	ResponseEntity<String> signUp(Map<String, String> requestMap);
	
	ResponseEntity<String> login(Map<String, String> requestMap);

	ResponseEntity<List<User>> getAllUser();
	
	ResponseEntity<Void> deleteUserByID(Long id);
	ResponseEntity<User> inActiveUserById(Long id);
	ResponseEntity<User> activeUserById(Long id);

	ResponseEntity<String> forgetPassword(Map<String, String> requestMap);

	ResponseEntity<String> changePassword(Map<String, String> requestMap);


}

