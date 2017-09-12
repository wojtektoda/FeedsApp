package pl.feedsapp.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.feedsapp.entity.Item;
import pl.feedsapp.entity.User;
import pl.feedsapp.entity.Feed;
import pl.feedsapp.service.ItemService;
import pl.feedsapp.service.FeedService;
import pl.feedsapp.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
 
     //TODO
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "api/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        System.out.println("I am here");
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @RequestMapping("api/hello/{userId}")
    public User users(@PathVariable int userId) {//REST Endpoint.
    	
        User user = userService.findOne(userId);
        return user;
    }

}
