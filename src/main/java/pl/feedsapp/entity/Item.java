package pl.feedsapp.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import pl.feedsapp.entity.Feed;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;

	private String title;
	
	@Lob // CLOB or BLOB type of data - long types of data like chracter[], byte[]
	@Column(name="item_description", length=100000)
	private String description;
	
	@Column(name="published_date")
	private Date publishedDate;
	
	private String link;
	
	@ManyToOne
	@JoinColumn(name="feed_id")
	private Feed feed; 
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	
	
	
}
