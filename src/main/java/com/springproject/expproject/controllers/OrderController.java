package com.springproject.expproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.expproject.entities.Order;
import com.springproject.expproject.entities.User;
import com.springproject.expproject.exceptions.UserNotFoundException;
import com.springproject.expproject.repositories.OrderRepository;
import com.springproject.expproject.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	UserRepository userRep;
	
	@Autowired 
	OrderRepository orderRep;
	
	@GetMapping("/{id}/orders")
	public List<Order> getAllOrders(@PathVariable long id) throws UserNotFoundException{
		Optional<User> user = userRep.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found.");
		}
		return user.get().getOrders();
	}
	
	@PostMapping("/{id}/orders")
	public Order createOrder(@PathVariable long id,@RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOp = userRep.findById(id);
		if(!userOp.isPresent()) {
			throw new UserNotFoundException("User not found.");
		}
		User user = userOp.get(); 
		order.setUser(user);
		return orderRep.save(order);
	}
	
	@GetMapping("/{userID}/orders/{orderID}")
	public Optional<Order> getOrderByID(@PathVariable("userID") long uid,@PathVariable("orderID" ) long oid) throws UserNotFoundException {
		
		Optional <Order> order = orderRep.findByOrderidAndUserId(oid, uid);
		if(!order.isPresent()) {
			throw new UserNotFoundException("Order not found.");
		}else {
			return order;
		}
		
	}
}









