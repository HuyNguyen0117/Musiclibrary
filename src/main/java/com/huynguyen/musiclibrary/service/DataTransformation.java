package com.huynguyen.musiclibrary.service;

import java.util.ArrayList;
import java.util.List;

import com.huynguyen.musiclibrary.dao.Album;
import com.huynguyen.musiclibrary.dao.Artist;
import com.huynguyen.musiclibrary.dao.Country;
import com.huynguyen.musiclibrary.dao.Genre;
import com.huynguyen.musiclibrary.dao.Song;
import com.huynguyen.musiclibrary.dao.User;
import com.huynguyen.musiclibrary.dao.Userandlike;

public class DataTransformation {
	
	private Service service;
	
	public static String message;
	
	public DataTransformation(){
		service = new Service();
	}
	
	
	public boolean insertInforToDatabase(Object object){
		return service.insertObject(object);
	}
	
	public boolean mergeInforToDatabase(Object object){
		return service.mergeObject(object);
	}
	
	public boolean removeInforFromDatabase(Object object){
		return service.removeObject(object);
	}
	
	
	public boolean updatePassword(String email, String password){
		return service.updatePassword(email, password);
	}
	
	public boolean updateActiveStatus(String email){
		return service.updateActiveStatus(email);
	}
	
	public boolean updateLikeForSong(String songName, int like){
		return service.updateLikeForSong(songName, like);
	}
	
	public boolean updateLenth(String albumName, double newDuration){
		return service.updateLenth(albumName, newDuration);
	}
	
	public User getUserByEmail(String email){
		return service.getUserByEmail(email);
	}
	
	public List<Song> getSongs(){
		return service.getSongs();
	}
	public Song getSongByName(String songName){
		return service.getSongByName(songName);
	}
	
	public List<Song> getSongsByCountry(Country country){
		return service.getSongsByCountry(country);
	}
	
	public Album getAlbumByName(String albumName){
		return service.getAlbumByName(albumName);
	}
	
	public List<Album> getAlbumsByCountry(Country country){
		return service.getAlbumsByCountry(country);
	}
	
	public List<Album> getAlbums(){
		return service.getAlbums();
	}
	
	public List<Song> getSongsByAlbum(String albumName){
		return service.getSongsByAlbum(albumName);
	}
	
	public List<Song> getSongsByArtist(String artistName){
		return service.getSongsByArtist(artistName);
	}
	
	public List<Artist> getArtists(){
		return service.getArtists();
	}
	public List<Artist> getArtistsByCountry(Country country){
		return service.getArtistsByCountry(country);
	}
	
	public List<Genre> getGenres(){
		return service.getGenre();
	}
	
	public Genre getGenreByName(String genreName){
		return service.getGenreByName(genreName);
	}
	
	public Country getCountryByName(String countryName){
		return service.getCountryByName(countryName);
	}
	
	public Artist getArtistByName(String artistName){
		return service.getArtistByName(artistName);
	}
	
	public List<Song> searchSongs(String keySearch){
		return service.searchSongs(keySearch);
	}
	
	public List<Album> searchAlbums(String keySearch){
		return service.searchAlbums(keySearch);
	}
	
	public List<Artist> searchArtists(String keySearch){
		return service.searchArtists(keySearch);
	}
	
	public List<Userandlike> getUserandlikes(){
		return service.getUserandlikes();
	}

	public boolean uploadSongAndAlbum(String title, String album, String country, String date,
			String duration, String genre, String fileNameNoWhiteSpace){
		
		
		Album myAlbum = getAlbumByName(album);
		Genre myGenre = getGenreByName(genre);
		
		//convert string to double
		double myDuration = Double.parseDouble(duration);
		Country myCountry = getCountryByName(country);
		
		if(myCountry == null){
			System.out.println("This country is not presented");
			return false;
		}
		
		//generate ids for song and album
		int mySongId = 1;
		int myAlbumId = 1;
		List<Song> songs = getSongs();
		List<Album> albums = getAlbums();
		
		if(songs.size() > 0)
			mySongId = songs.get(songs.size() - 1).getIdsong() + 1;
		if(albums.size() > 0)
			myAlbumId = albums.get(albums.size() - 1).getIdalbum() + 1;
		
		//create song object
		Song mySong = new Song();
		
		mySong.setIdsong(mySongId);
		
		mySong.setName(title);
		mySong.setLenth(myDuration);
		mySong.setUrl("resources/songs/" + fileNameNoWhiteSpace);
		mySong.setRate(0);
		mySong.setDescription("Description");
		mySong.setView(0);
		mySong.setDate(date);
		mySong.setGenre(myGenre);
		mySong.setCountry(myCountry);
		
		
		if(myAlbum != null){
			
			double albumLenth = myAlbum.getLenth();
			
			myAlbum.setLenth(albumLenth + myDuration);
			
			if(!updateLenth(myAlbum.getName(), albumLenth + myDuration)){
				System.out.println("Can not update lenth");
				return false;
			}
			
			List<Album> albumsList = new ArrayList<Album>();
			albumsList.add(myAlbum);
			mySong.setAlbums(albumsList);
			
			if(!mergeInforToDatabase(mySong)){
				System.out.println("Album already in the system, can not insert song ojbect");
				return false;
				
			}
			
		}else{
			//create album object
			Album newAlbum = new Album();
			newAlbum.setIdalbum(myAlbumId);
			newAlbum.setName(album);
			
			newAlbum.setLenth(myDuration);
			newAlbum.setDate(date);
			newAlbum.setImage("resources/images/default.jpeg");
			newAlbum.setDescription("Description");
			newAlbum.setCountry(myCountry);
			newAlbum.setRate(0);
			
			List<Album> albumsList = new ArrayList<Album>();
			albumsList.add(newAlbum);
			
			mySong.setAlbums(albumsList);
	
			if(!insertInforToDatabase(mySong)){
				System.out.println("Can no upload album and song objects");				
				return false;
				
			}

			
		}
		
		
		return true;
	}
	
	public boolean uploadSongAndArtist(String titleName, String artistName, String countryName){
		
		Artist artist = getArtistByName(artistName);
		Song song = getSongByName(titleName);
		Country country = getCountryByName(countryName);
		
		List<Artist> artists = new ArrayList<Artist>();
		if(song == null){
			return false;
		}
		
		if(artist == null){		
			List<Artist> theArtists = getArtists();
			int artistId = 1;
			if(theArtists.size() > 0)
				artistId = theArtists.get(theArtists.size() - 1).getIdartist() + 1;
			artist = new Artist();
			artist.setIdartist(artistId);
			artist.setName(artistName);
			artist.setCountry(country);
			artist.setDescription("Description");
			artist.setImage("resources/images/default.jpeg");
			
			if(!insertInforToDatabase(artist)){
				return false;
			}
			
			artist = getArtistByName(artistName);
		}
		
		artists.add(artist);
		song.setArtists(artists);
		
		if(!mergeInforToDatabase(song)){
			System.out.println("update song and artist, cand not insert song object");
			return false;
		}
		
		return true;
	}
	
	public boolean uploadAlbumAndArtist(String albumName, String artistName){
		
		Album album = getAlbumByName(albumName);
		Artist artist = getArtistByName(artistName);
		
		List<Artist> artists = new ArrayList<Artist>();
		
		if(album == null || artist == null){
			System.out.println("Update album and artist. album or artist not exist");
			return false;
		}
		
		artists.add(artist);
		album.setArtists(artists);
		
		if(!mergeInforToDatabase(album)){
			System.out.println("Update album and artist. Can not merge album and artist to the database");
			return false;
		}
		
		return true;
	}
	

}
