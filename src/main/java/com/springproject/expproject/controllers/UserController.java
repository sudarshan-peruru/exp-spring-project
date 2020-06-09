package com.springproject.expproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springproject.expproject.entities.User;
import com.springproject.expproject.exceptions.UserExistsException;
import com.springproject.expproject.exceptions.UserNotFoundException;
import com.springproject.expproject.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		try {
			 userService.createUser(user);
			 HttpHeaders headers = new HttpHeaders();
			 headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch(UserExistsException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserByID(@PathVariable("id") Long id) {
		try {
			return userService.getUserByID(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	@PutMapping("/users/{id}")
	public User updateUserByID(@PathVariable("id") Long id, @RequestBody User user) {
		try{
			return userService.updateUserByID(id, user);
		}catch(UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUserByID(@PathVariable("id") Long id) {
		return userService.deleteUserByID(id);
	}
	
	@GetMapping("/users/username/{name}")
	public Optional<User> getUserByUsername(@PathVariable("name") String name) {
		return userService.getUserByUsername(name);
	}
}








