package com.springproject.expproject.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springproject.expproject.entities.User;
import com.springproject.expproject.exceptions.UserNotFoundException;
import com.springproject.expproject.services.UserService;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;
	
	
	// getUserByID - fields with Hashset
	@GetMapping("/{id}")
	public MappingJacksonValue getUserByID(@PathVariable("id") @Min(1) Long id) {
		try {
			System.out.println("Inside GetMapping with id");
			Optional<User> userOptional = userService.getUserByID(id);
			User user = userOptional.get();
			
			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
			System.out.println("before filter");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			System.out.println("after filter"+ filterProvider.toString());
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			
			return mapper;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	
	// getUserByID - fields with @RequestParam
	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserByID2(@PathVariable("id") @Min(1) Long id,
			@RequestParam Set<String> fields) {
		try {
			System.out.println("Inside GetMapping with id");
			Optional<User> userOptional = userService.getUserByID(id);
			User user = userOptional.get();
			System.out.println("before filter");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			System.out.println("after filter"+ filterProvider.toString());
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			
			return mapper;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
}
