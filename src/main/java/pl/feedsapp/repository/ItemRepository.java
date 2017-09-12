package pl.feedsapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.feedsapp.entity.Item;
import pl.feedsapp.entity.Feed;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	List<Item> findByFeed(Feed feed, Pageable pageable); // Pageable -> interface to implement for pagination functionality

	Item findByFeedAndLink(Feed feed, String link);
	
}
