package com.stacksimplify.restservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat() {
		return "Hello World I18N";
		
	}
}
