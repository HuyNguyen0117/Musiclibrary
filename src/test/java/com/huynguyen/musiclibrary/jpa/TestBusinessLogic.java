package com.huynguyen.musiclibrary.jpa;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.huynguyen.musiclibrary.dao.Country;
import com.huynguyen.musiclibrary.dao.Genre;
import com.huynguyen.musiclibrary.dao.Song;
import com.huynguyen.musiclibrary.dao.Userandlike;
import com.huynguyen.musiclibrary.service.Service;

public class TestBusinessLogic {

	private static Service service;
	
	@BeforeClass
	public static void testSetup() {
		service = new Service();
	}

	@AfterClass
	public static void testCleanup() {
	  
	}
	
	@Test
	public void test(){
		int songId = 0;
		int albumId = 0;
		List<Song> songs = service.getSongs();
		
		System.out.println(songs.get(songs.size() - 1).getIdsong());
		
	}
	
	@Test 
	public void testUpdate(){
		
	}
	
//	@Test
//	public void testInsertUser(){
//		User user = new User();
//		
//		Type type = new Type();
//		type.setIdtype(2);
//		type.setType("admin");
//		
//		Date date = new Date(Calendar.getInstance().getTimeInMillis());
//		
//		user.setType(type);
//		user.setFirstname("LA");
//		user.setLastname("Nguyen");
//		user.setEmail("la@yahoo.com");
//		user.setGender("Female");
//		user.setPassword("languyen");
//		user.setDob(date);
//		
//		
//		assertTrue(service.insertObject(user));
//		
//	}
	
	@Test
	public void testInsertSong(){

		
		Song song = new Song();
		
		Country country = new Country();
		
		country.setIdcountry(1);
		country.setName("Viet Nam");
		Genre genre = new Genre();
		genre.setIdgenre(2);
		genre.setType("blue");
		
		song.setIdsong(100);
		song.setName("Xin Loi Tinh Yeu");
		song.setLenth(5.20);
		
		song.setUrl("re");
		song.setDate("sdfsdf");
		song.setRate(5);
		song.setView(100);
		song.setDescription("Description");
		song.setLyris("Lyris");
		
		
		
		song.setGenre(genre);
		song.setCountry(country);
			
		assertTrue(service.insertObject(song));
		
		
	}
	
	@Test
	public void testInsertLike(){

		
		Userandlike like = new Userandlike();
		like.setSongIdsong(1);
		like.setUserIduser(1);
		
		assertTrue(service.insertObject(like));
		
		
		
	}

}
