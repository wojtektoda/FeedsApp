package pl.feedsapp.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pl.feedsapp.entity.Role;
import pl.feedsapp.entity.User;
import pl.feedsapp.entity.Feed;
import pl.feedsapp.repository.RoleRepository;
import pl.feedsapp.repository.FeedRepository;
import pl.feedsapp.repository.UserRepository;


// Service responsible for initialize sample data
@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired 
	private FeedRepository feedRepository;
	
	
	@PostConstruct // method invoked after deployment to the server 
	public void init() {
		if(roleRepository.findByName("ROLE_ADMIN")==null) // check if any data exists 
		{
		// Creating user roles
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setEmail("admin@example.pl");
		userAdmin.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin")); // password in encoded by BCrypt 
		List<Role> adminRoles = new ArrayList<Role>();
		adminRoles.add(roleAdmin);
		adminRoles.add(roleUser);
		userAdmin.setRoles(adminRoles);
		
		userRepository.save(userAdmin);
		
		User user = new User();
		user.setName("user");
		user.setEmail("user@example.pl");
		BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();
		user.setPassword(encoder2.encode("user"));
		List<Role> userRoles = new ArrayList<Role>();
		userRoles.add(roleUser);
		user.setRoles(userRoles);
		
		userRepository.save(user);
		
		User user2 = new User();
		user2.setName("user2");
		user2.setEmail("user2@example.pl");
		BCryptPasswordEncoder encoder3 = new BCryptPasswordEncoder();
		user2.setPassword(encoder3.encode("user2"));
		List<Role> userRoles2 = new ArrayList<Role>();
		userRoles2.add(roleUser);
		user2.setRoles(userRoles2);
		
		userRepository.save(user2);
		
		Feed feed1 = new Feed();
		feed1.setName("The Guardian - Technology RSS");
		feed1.setUrl("https://www.theguardian.com/technology/mobilephones/rss");
		feed1.setUser(userAdmin);
		
		feedRepository.save(feed1);
		
		Feed feed2 = new Feed();
		feed2.setName("The Guardian - Audio RSS");
		feed2.setUrl("https://www.theguardian.com/audio/rss");
		feed2.setUser(userAdmin);
		
		feedRepository.save(feed2);
		
	}
	}
	

	

}
