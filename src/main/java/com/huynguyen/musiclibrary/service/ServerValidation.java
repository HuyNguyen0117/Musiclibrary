package com.huynguyen.musiclibrary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huynguyen.musiclibrary.dao.Album;
import com.huynguyen.musiclibrary.dao.Song;
import com.huynguyen.musiclibrary.dao.User;
import com.huynguyen.musiclibrary.dao.Userandlike;

public class ServerValidation {
	
	private Service service;
	
	public ServerValidation(){
		service = new Service();
	}
	
	//check if the email and password registered
	public boolean isThisEmailAndPasswordRegistered(String email, String password){
		User user = service.getUserByEmail(email);
		if(user != null){
			if(user.getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}
	
	//check if the email is registered
	public boolean isThisEmailRegistered(String email){
		User user = service.getUserByEmail(email);
		if(user != null)
			return true;
		return false;
	}
	
	//check if the account is activated
	public boolean isThisAccountActivated(User user){
		if(user != null){
			if(user.getActive() == 1)
				return true;
		}
		return false;
		
	}
	
	//check if the song is existed in the system
	public boolean isThisSongExisted(String songName){
		
		Song song = service.getSongByName(songName);
		if(song != null)
			return true;
		
		return false;
	}
	
	//check if the album is already existed in the system
	public boolean isThisAlbumExisted(String albumName){
		Album album = service.getAlbumByName(albumName);
		if(album != null)
			return true;
		return false;
	}
	
	//check if the user is already like this song
	public boolean isThisUserLikedThisSong(int userId, int songId){
		
		List<Userandlike> uls = service.getUserandlikes();
		
		for(Userandlike ul: uls){
			if(ul.getUserIduser() == userId && ul.getSongIdsong() == songId)
				return true;
		}
		
		return false;
		
	}
}
