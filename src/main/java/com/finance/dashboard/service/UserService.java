package com.finance.dashboard.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.finance.dashboard.entity.Role;
import com.finance.dashboard.entity.Status;
import com.finance.dashboard.entity.UserEntity;
import com.finance.dashboard.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public UserEntity createUser(UserEntity userEntity)
	{
		userEntity.setStatus(userEntity.getStatus() == null ? com.finance.dashboard.entity.Status.ACTIVE : userEntity.getStatus());
        return userRepo.save(userEntity);
	}
	
	public List<UserEntity> getAllUsers()
	{
		return userRepo.findAll();
	}
	
	 public UserEntity updateRole(Long id, Role role) {
		 UserEntity user = userRepo.findById(id).orElseThrow();
	        user.setRole(role);
	        return userRepo.save(user);
	  }

	
	public UserEntity updateStatus(Long id, Status status) {
	     UserEntity user = userRepo.findById(id)
	             .orElseThrow(() -> new RuntimeException("User not found"));

	     user.setStatus(status);
	     return userRepo.save(user);
	 }
	public UserEntity getUserById(Long id) {
			// TODO Auto-generated method stub
			return userRepo.findById(id)
					.orElseThrow(() -> new RuntimeException("User not found"));
	}

	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}
	
	
}
