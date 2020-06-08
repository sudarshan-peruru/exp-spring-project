package com.springproject.expproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.expproject.entities.User;
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
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserByID(@PathVariable("id") Long id) {
		return userService.getUserByID(id);
	}
	
	@PutMapping("/users/{id}")
	public User updateUserByID(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateUserByID(id, user);
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








