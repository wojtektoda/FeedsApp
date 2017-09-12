package pl.feedsapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;



@Entity
public class Feed {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	
	private String url;
	
	@Size(min=1,message="Name must be at least 1 character!")
	private String name;
	
	@OneToMany(mappedBy="feed", cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List<Item> items;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
