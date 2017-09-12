package pl.feedsapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.feedsapp.entity.User;
import pl.feedsapp.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	//Creates Data Transfer Object as attribute - UserDTO holds data from registration form
		@ModelAttribute("userDTO") //corresponds to commandName/model attribute attribute in form
		public User constructUserDTO() {
			return new User(); // this is shortcut. normally should create instance of UserDTO holding data before final persistance
		}
	
	@RequestMapping()// the same url as attached to the class definition
	public String showRegisterForm(){
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("userDTO") User userDTO, BindingResult result){
		if(result.hasErrors()){
			return "user-register";
		}
		userService.save(userDTO);
		return "redirect:/register?success=true"; // parameter success to check in form
	}
	
	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String username){
		Boolean available = userService.findOne(username) == null;
		return available.toString();
	}

}
