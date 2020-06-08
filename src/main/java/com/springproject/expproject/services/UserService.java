package com.springproject.expproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.expproject.entities.User;
import com.springproject.expproject.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserByID(Long ID) {
		return userRepository.findById(ID);
	}
	
	public User updateUserByID(Long ID,User user) {
		user.setId(ID);
		return userRepository.save(user);
	}
	
	public String deleteUserByID(Long ID) {
		if(userRepository.findById(ID).isPresent()) {
			userRepository.deleteById(ID);
			return "Deleted user with ID: "+ID;
		}else {
			return "User with given ID does not exist.";
		}
	}
	
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}







