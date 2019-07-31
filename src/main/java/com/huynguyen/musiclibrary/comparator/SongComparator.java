package com.huynguyen.musiclibrary.comparator;

import java.util.Comparator;

import com.huynguyen.musiclibrary.dao.Song;


	
public class SongComparator implements Comparator<Song> {

	public int compare(Song song1, Song song2) {
	    	
	    return song2.getView() - song1.getView();
	       
	}
}
	 
		 
	


