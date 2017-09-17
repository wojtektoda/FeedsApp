package pl.feedsapp.controller;

import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.feedsapp.entity.User;

@Controller
public class LoginController {
	
	
	
	@RequestMapping("/login")
	public String login(@Valid User user) {
		
		return "login";
	}

}
