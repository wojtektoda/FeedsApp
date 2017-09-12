package pl.feedsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.feedsapp.entity.User;
import pl.feedsapp.entity.Feed;


public interface FeedRepository extends JpaRepository<Feed, Integer>{

	List<Feed> findByUser(User user); // SPring will prepare right impl. of this method

}
