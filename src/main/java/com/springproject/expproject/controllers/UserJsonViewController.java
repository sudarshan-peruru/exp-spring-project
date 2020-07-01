package com.springproject.expproject.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.springproject.expproject.entities.User;
import com.springproject.expproject.entities.Views;
import com.springproject.expproject.exceptions.UserNotFoundException;
import com.springproject.expproject.services.UserService;

@Validated
@RestController
@RequestMapping(value="/jsonview/users")
public class UserJsonViewController {

	@Autowired
	private UserService userService;
	
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public Optional<User> getUserByID(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserByID(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public Optional<User> getUserByID2(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserByID(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
}
