package pl.feedsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.feedsapp.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
