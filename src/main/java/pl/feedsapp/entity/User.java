package pl.feedsapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import pl.feedsapp.annotation.UniqueUserName;

import javax.persistence.CascadeType;
import javax.persistence.Column;



@Entity
@Table(name="app_user")// in postgrsql "user" name is reserved keyword
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=3,message="Name must be at least 3 characters!")
	@Column(unique=true)
	@UniqueUserName(message="Such user already exists!")
	private String name;
	
	@Size(min=1,message="Invalid email address!")
	@Email
	private String email;
	
	@Size(min=5,message="Password must be at least 5 characters!")
	private String password;
	private Boolean enabled;
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToMany
	@JoinTable
	@JsonIgnore
	private List<Role> roles;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE) //cascadeType.REMOVE - while removing THIS Object it removes all related objects -in this case userOrders
	@JsonIgnore
	private List<Feed> feeds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}

	
	

	
	
}
