package com.springproject.expproject.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.expproject.Hello.UserDetails;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSrc;
	
	@GetMapping("/helloworld")
	public String getHelloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/getuser")
	public UserDetails getUser() {
		return new UserDetails("Sudarshan", "Peruru", "Hyd");
	}
	
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language",required=false) String locale) {
		return messageSrc.getMessage("label.hello", null, new Locale(locale) );
	}
	
}
