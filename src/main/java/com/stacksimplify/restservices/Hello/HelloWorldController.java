package com.stacksimplify.restservices.Hello;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
	//@RequestMapping(method= RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld()
	{
		return "hello world";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails HelloWorldBean()
	{
		
		return new UserDetails("Raghu","Sharma", "Amritsar");
		
	}
	
	private ResourceBundleMessageSource messageResource;
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat() {
		return "Hello World I18N";
		//code for internationalization
	}
	
}
