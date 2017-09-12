package pl.feedsapp.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.feedsapp.entity.Item;
import pl.feedsapp.entity.Role;
import pl.feedsapp.entity.User;
import pl.feedsapp.entity.Feed;
import pl.feedsapp.repository.ItemRepository;
import pl.feedsapp.repository.RoleRepository;
import pl.feedsapp.repository.FeedRepository;
import pl.feedsapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public List<User> findAllUsers() {
		
		 return userRepository.findAll();	

	}

	public User findOne(int id) {
		// TODO Auto-generated method stub
		
		return userRepository.findOne(id);
	}
	@Transactional
	public User findOneWithFeed(int id) {
		
		User user = findOne(id);
		List<Feed> feeds = feedRepository.findByUser(user);
		for(Feed feed: feeds){
			List<Item> items = itemRepository.findByFeed(feed, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			feed.setItems(items);
		}
		user.setFeeds(feeds);
		return user;
	}

	public void save(User userDTO) {
		userDTO.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userDTO.setPassword(encoder.encode(userDTO.getPassword()));
		List<Role> userRoles = new ArrayList<Role>();
		userRoles.add(roleRepository.findByName("ROLE_USER"));
		userDTO.setRoles(userRoles);
		
		userRepository.save(userDTO);
	}

	public User findOneWithFeed(String name) {
		User user = userRepository.findByName(name);
		return findOneWithFeed(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);
		
	}

	public User findOne(String username) {
		return userRepository.findByName(username);		
	}

}
