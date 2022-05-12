package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserService userService;
	
	@GetMapping
	public CollectionModel<User> getAllUsers()
	{
		List<User> allusers = userService.getAllUsers();
		
		for(User user: allusers)
		{
			//self link
			Long userid = user.getId();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			
			//relationship link with getallorders
			//EntityModel<Order> orders = ControllerLinkBuilder.methodOn
			//not ryt implementation as the relation link that we get in output is not correct
			// it should be "http://localhost:8080/hateoas/users/{1}/orders" but it is "http://localhost:8080/hateoas/users"
			Link orderslink = WebMvcLinkBuilder.linkTo(OrderHateoasController.class).withRel("all-orders");
			user.add(orderslink);
		}
		// self link for getAllUsers
		Link selflinkgetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalEntity = CollectionModel.of(allusers,selflinkgetAllUsers);
	    return finalEntity;	
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) 
	{
		try {
		Optional<User> userOptional =  userService.getUserById(id);
		User user = userOptional.get();
		Long userid = user.getId();
		Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
		user.add(selflink);
		
		//response would be hateoas entity(resource) so we will return finalEntity
		EntityModel<User> finalEntity = EntityModel.of(user);

		return finalEntity;

		}
		catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

}
