package pl.feedsapp.controller;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.feedsapp.service.ItemService;

@Controller
public class IndexController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("items", itemService.getItems());
		return "index"; //   name of the view as definition in Apache Tiles bean configuration file general.xml
	}
	
	@PostConstruct 
	public void startDBManager() {
		// hsqldb manager
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:dataSource", "--user", "sa", "--password", "" });
	}
	

}
