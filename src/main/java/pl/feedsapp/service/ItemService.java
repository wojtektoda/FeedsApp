package pl.feedsapp.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import pl.feedsapp.entity.Item;
import pl.feedsapp.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	//Return 20 items on the page in descending order
	public List<Item> getItems(){
		return itemRepository.findAll(new PageRequest(0, 20, Direction.DESC, "PublishedDate")).getContent();
	}
	
	
	public Item findOne(int id) {
		Item item;
		item = itemRepository.findOne(id);
		return item; 
	}

	public void delete(Item item) {
		itemRepository.delete(item);
		
	}
	
	



}
