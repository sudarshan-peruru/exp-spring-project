package com.springproject.expproject.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/helloworld")
	public String getHelloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/getuser")
	public UserDetails getUser() {
		return new UserDetails("Sudarshan", "Peruru", "Hyd");
	}
}
