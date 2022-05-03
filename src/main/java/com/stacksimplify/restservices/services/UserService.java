package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers(){
		
		return userRepository.findAll();
		
		
	}
	
	public User createUser(User user) throws UserExistsException
	{
		//if user exists using username otherwise throw eception
		
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser!=null)
		{
			
			throw new UserExistsException("User already exists in repo");
		}
	return userRepository.save(user);	
	}
	
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException
	{
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
		{
			throw new UserNotFoundException("user not found in user repository") ;
		}
		return user;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFoundException
	{ 
		
       Optional<User> optionalUser = userRepository.findById(id);
		
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("user not found in user repository, provide correct user id") ;
		}
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	public void deleteUserById(Long id)
	{
     Optional<User> optionalUser = userRepository.findById(id);
		
		if(!optionalUser.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user not found in user repository, provide correct user id") ;
		}
			userRepository.deleteById(id);
		
	}
	
	public User getUserByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
}
