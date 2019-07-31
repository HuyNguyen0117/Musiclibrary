package com.huynguyen.musiclibrary.jpa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.huynguyen.musiclibrary.dao.Album;
import com.huynguyen.musiclibrary.dao.Artist;
import com.huynguyen.musiclibrary.dao.Country;
import com.huynguyen.musiclibrary.dao.Song;
import com.huynguyen.musiclibrary.dao.User;
import com.huynguyen.musiclibrary.service.Service;

public class TestJPA {
	
	private static Service service;
	
	@BeforeClass
	public static void testSetup() {
		service = new Service();
	}

	@AfterClass
	public static void testCleanup() {
	  
	}
	
	@Test
	public void songOpject() {
		
		List<Song> songs = service.getSongs();		
		assertEquals("Anh Nho Em", songs.get(0).getName());
		assertEquals("Chiec Vong Cau Hon", songs.get(1).getName());
	}
	
	@Test
	public void artistOpject(){
		
		List<Artist> artists = service.getArtists();
		assertEquals("Tuan Hung", artists.get(0).getName());
		assertEquals("Dam Vinh Hung", artists.get(1).getName());
	}
	
	@Test
	public void albumOpject(){
		List<Album> albums = service.getAlbums();
		assertEquals("Nho Em", albums.get(0).getName());
		assertEquals("Nhac Chu Tinh", albums.get(1).getName());

	}
	
	@Test
	public void userOpject(){
		List<User> users = service.getUsers();
		assertEquals("huyng1@yahoo.com", users.get(0).getEmail());
		assertEquals("alex@yahoo.com", users.get(1).getEmail());
	}
	
	@Test
	public void getUsersByUserId(){
		
		User user = service.getUserByUserId(1);
		assertEquals("Huy", user.getFirstname());
		assertEquals("Nguyen", user.getLastname());
		
	}
	
	@Test
	public void getUsersByEmail(){
		
		User user = service.getUserByEmail("huyng1@yahoo.com");
		assertEquals("Huy", user.getFirstname());
		assertEquals("Nguyen", user.getLastname());
		
	}
	
	@Test
	public void getCountryByName(){
		Country country = service.getCountryByName("Viet nam");
		assertEquals("Viet Nam", country.getName());
	}
	
	@Test
	public void getArtistByName(){
		Artist artist = service.getArtistByName("Tuan Hung");
		assertEquals("Tuan Hung", artist.getName());
	}
	
	@Test 
	public void getArtists(){
		List<Artist> artists = service.getArtists();
		assertEquals("Tuan Hung", artists.get(0).getName());
	}


}
