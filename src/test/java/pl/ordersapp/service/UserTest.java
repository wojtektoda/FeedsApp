package pl.ordersapp.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.feedsapp.entity.Item;
import pl.feedsapp.exception.RssException;
import pl.feedsapp.service.RssService;

public class UserTest {
	
	private RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void test() throws RssException {
		List<Item> items = rssService.getItems("http://feeds.feedburner.com/javavids?format=xml");
		Item item = items.get(0);
		//assertEquals(10, items.size());
		assertEquals("How to solve Source not found error during debug in Eclipse", item.getTitle());
	}

}
