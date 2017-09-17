package pl.feedsapp.controller;

import java.security.Principal;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public HttpHeaders users(@PathVariable int userId, HttpRequest httprequest ) {//REST Endpoint.
    	HttpHeaders header = httprequest.getHeaders();
    	
        User user = userService.findOne(userId);
        
        return header;
    }

}
