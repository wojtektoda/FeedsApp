package pl.feedsapp.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.feedsapp.entity.Item;
import pl.feedsapp.entity.User;
import pl.feedsapp.entity.Feed;
import pl.feedsapp.service.ItemService;
import pl.feedsapp.service.FeedService;
import pl.feedsapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private ItemService itemService;

	
	@ModelAttribute("feedDTO") 
	public Feed constructUserOrderDTO() {
		return new Feed(); 
	}
	
	
	@RequestMapping(value="/account")
	public String account(Model model, Principal principal) { // principal object from SpringSecurity - holds data about loggedin user
		
		String name = principal.getName();	//get loggedin user name
		model.addAttribute("users", userService.findOneWithFeed(name));
		return "account";
		
	}
	
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String AddFeed(Model model, @Valid @ModelAttribute("feedDTO") Feed feedDTO, BindingResult result, Principal principal){
		if(result.hasErrors()){
			System.out.println("Something went wrong with JAXB!");
			return account(model, principal);
		}
		String name = principal.getName();
		feedService.save(feedDTO, name);
		return "redirect:/account"; 
	}

	@RequestMapping(value="/feed/edit/{id}")
		public @ResponseBody Feed editFeed(@PathVariable int id){ // ResponseBody for user request
		Feed feed  = feedService.findOne(id);
			System.out.println(feed.getId());
			return feed;
		}
	

	
	@RequestMapping(value="/feed/edit", method=RequestMethod.POST)
	public String editFeed(@Valid @ModelAttribute("feedDTO") Feed feedDTO, Principal principal ){
	String name = principal.getName();
	User user = userService.findOne(name);
	System.out.println(feedDTO.getId());
		feedService.edit(feedDTO, user);
		System.out.println("Feed Updated!");
		return "redirect:/account";
	}
	
	@RequestMapping("/feeds")
	public String showFeeds(Model model){
		
		model.addAttribute("feeds",feedService.findAllFeeds());
		return "feeds";
	}
	
	@RequestMapping("/feeds/{id}")
	public String showFeedById(Model model, @PathVariable int id){
		model.addAttribute("feedDetail",feedService.findOne(id));
		return "feeds";
	}
	
	@RequestMapping("/feed/remove/{id}")
	public String removeFeed(@PathVariable int id){
		Feed feed = feedService.findOne(id);
		feedService.delete(feed);
		return "redirect:/account";
	}
	
	
	
	@RequestMapping("/item/remove/{id}")
	public String removeItem(@PathVariable int id){
		Item item = itemService.findOne(id);
		itemService.delete(item);
		return "redirect:/account";
	}
	
	
	
	
	

}
