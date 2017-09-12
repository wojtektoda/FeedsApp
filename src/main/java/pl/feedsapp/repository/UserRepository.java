package pl.feedsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.feedsapp.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);

}
