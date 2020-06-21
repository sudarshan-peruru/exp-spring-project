package com.springproject.expproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springproject.expproject.entities.User;
import com.springproject.expproject.exceptions.UserExistsException;
import com.springproject.expproject.exceptions.UserNameNotFoundException;
import com.springproject.expproject.exceptions.UserNotFoundException;
import com.springproject.expproject.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException{
		Optional<User> existUser = userRepository.findByUsername(user.getUsername());
		if(existUser.isPresent()) {
			throw new UserExistsException("User already exists in repo.");
		}
				
		return userRepository.save(user);
	}
	
	public Optional<User> getUserByID(Long ID) throws UserNotFoundException{
		Optional<User> user =  userRepository.findById(ID);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found.");
		}
		return user;
	}
	
	public User updateUserByID(Long ID,User user) throws UserNotFoundException{
		Optional<User> optionaluser = userRepository.findById(ID);
		if(!optionaluser.isPresent()) {
			throw new UserNotFoundException("User not found, provide correct user ID.");
		}
		user.setId(ID);
		return userRepository.save(user);
	}
	
	public String deleteUserByID(Long ID){
		Optional<User> optionaluser = userRepository.findById(ID);
		if(!optionaluser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found, provide correct user ID.");
		}
		userRepository.deleteById(ID);
		return "Deleted user with ID: "+ID;
	}
	
	public User getUserByUsername(String username) throws UserNameNotFoundException {
		User user = userRepository.findByUsername(username)
					.orElseThrow(()->new UserNameNotFoundException("Username: "+ username + " not found in User repository."));
		
		return user;
	}
}







