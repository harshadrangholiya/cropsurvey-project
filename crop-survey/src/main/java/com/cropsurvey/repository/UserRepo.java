package com.cropsurvey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cropsurvey.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmailId(@Param("email") String email);
	List<User> findByIsDeleted(Boolean isDeleted);
//	List<UserWrapper> getAllUser();
	User findByEmail(String email);
}
