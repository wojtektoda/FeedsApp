package pl.feedsapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import pl.feedsapp.entity.Item;
import pl.feedsapp.entity.User;
import pl.feedsapp.entity.Feed;
import pl.feedsapp.repository.ItemRepository;
import pl.feedsapp.repository.FeedRepository;
import pl.feedsapp.repository.UserRepository;

@Service
public class FeedService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private FeedRepository feedRepository; 
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	//Save items using RssService for retrieving data from xml feed source
	public void saveItems(Feed feed){
		try {
			List<Item> items = rssService.getItems(feed.getUrl());
			for(Item item:items){
				Item savedItem = itemRepository.findByFeedAndLink(feed, item.getLink());
				if(savedItem == null){
				item.setFeed(feed);
				itemRepository.save(item);}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(Feed feed, String name) {
		User user = userRepository.findByName(name);
		feed.setUser(user);
		feedRepository.save(feed);
		saveItems(feed);
		
		
	}
	
	//Method to reaload UserOrders and refresh data in time period
	// Time in ms 1h=60x60x60 (ms)
	@Scheduled(fixedDelay=3600000)
	public void reloadUserOrders() {
		List<Feed> feeds = feedRepository.findAll();
		for(Feed feed:feeds){
			saveItems(feed);
		}
	}

	@Transactional
	public List<Feed> findAllFeeds() {
		return feedRepository.findAll();
		
	}

	@PreAuthorize("#feed.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P ("feed") Feed feed) {
		feedRepository.delete(feed);
		
	}

	public Feed findOne(int id) {
		
		return feedRepository.findOne(id);
	}

	
	public void edit(Feed feed, User user) {
		User user1 = userRepository.findByName(user.getName());
		feed.setName(feed.getName());
		feed.setUrl(feed.getUrl());
		feed.setUser(user1);
		feedRepository.save(feed);
		saveItems(feed);
	}

}
