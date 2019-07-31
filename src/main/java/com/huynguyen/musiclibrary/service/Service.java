package com.huynguyen.musiclibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.huynguyen.musiclibrary.dao.Album;
import com.huynguyen.musiclibrary.dao.Artist;
import com.huynguyen.musiclibrary.dao.Country;
import com.huynguyen.musiclibrary.dao.Genre;
import com.huynguyen.musiclibrary.dao.Song;
import com.huynguyen.musiclibrary.dao.User;
import com.huynguyen.musiclibrary.dao.Userandlike;

public class Service {
	
	public List<Song> getSongs(){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Song> songs = new ArrayList<Song>();
		try{
			songs = em.createNamedQuery("Song.findAll", Song.class).getResultList();
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return songs;
	}
	public Song getSongByName(String songName){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		Song song = new Song();
		try{
			song = (Song)em.createNamedQuery("Song.getSongByName", Song.class).setParameter("song", songName).getSingleResult();
		}catch(NoResultException nre){
			em.close();
			return null;
			
		}finally{
			if(em.isOpen())
				em.close();
		}
		return song;
	}
	public List<Song> getSongsByCountry(Country country){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Song> songs = new ArrayList<Song>();
		try{
			songs = em.createNamedQuery("Song.getSongsByCountry", Song.class).setParameter("country", country).getResultList();
		}catch(NoResultException nre){
			em.close();
			return null;
			
		}finally{
			if(em.isOpen())
				em.close();
		}
		return songs;
	}
	
	public List<Song> getSongsByAlbum(String albumName){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Song> songs = new ArrayList<Song>();
		Album album = new Album();
		try{
			album = (Album)em.createNamedQuery("Album.getAlbumByName", Album.class).setParameter("album", albumName).getSingleResult();
			songs = (List<Song>)album.getSongs();
			
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return songs;
	}
	
	
	public List<Song> searchSongs(String keySearch){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Song> songs = new ArrayList<Song>();
		try{
			songs = em.createNamedQuery("Song.searchSongs", Song.class).setParameter("keysearch", keySearch).getResultList();
			
			
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return songs;
	}
	
	public List<Album> searchAlbums(String keySearch){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Album> albums = new ArrayList<Album>();
		try{
			albums = em.createNamedQuery("Album.searchAlbums", Album.class).setParameter("keysearch", keySearch).getResultList();
			
			
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return albums;
	}
	
	public List<Album> getAlbumsByCountry(Country country){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Album> albums = new ArrayList<Album>();
		try{
			albums = em.createNamedQuery("Album.getAlbumsByCountry", Album.class).setParameter("country", country).getResultList();
		}catch(NoResultException nre){
			em.close();
			return null;
			
		}finally{
			if(em.isOpen())
				em.close();
		}
		return albums;
	}
	public List<Artist> getArtists(){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Artist> artists = new ArrayList<Artist>();
		try{
			artists = em.createNamedQuery("Artist.findAll", Artist.class).getResultList();
		}catch(NoResultException nre){
			em.close();
			return artists;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return artists;
	}
	
	public List<Artist> getArtistsByCountry(Country country){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Artist> artists = new ArrayList<Artist>();
		try{
			artists = em.createNamedQuery("Artist.getArtistsByCountry", Artist.class).setParameter("country", country).getResultList();
		}catch(NoResultException nre){
			em.close();
			return null;
			
		}finally{
			if(em.isOpen())
				em.close();
		}
		return artists;
	}
	
	public Artist getArtistByName(String artistName){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		Artist artist = new Artist();
		try{
			artist = em.createNamedQuery("Artist.getArtistByName", Artist.class).setParameter("artist", artistName).getSingleResult();
		}catch(NoResultException nre){
			em.close();
			return null;
			
		}finally{
			if(em.isOpen())
				em.close();
		}
		return artist;
	}
	
	public List<Artist> searchArtists(String keySearch){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Artist> artists = new ArrayList<Artist>();
		try{
			artists = em.createNamedQuery("Artist.searchArtists", Artist.class).setParameter("keysearch", keySearch).getResultList();
			
			
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return artists;
	}
	
	
	public List<Song> getSongsByArtist(String artistName){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Song> songs = new ArrayList<Song>();
		Artist artist = new Artist();
		try{
			artist = (Artist)em.createNamedQuery("Artist.getArtistByName", Artist.class).setParameter("artist", artistName).getSingleResult();
			songs = (List<Song>)artist.getSongs();
			
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return songs;
	}
	
	public List<Album> getAlbums(){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Album> albums = new ArrayList<Album>();
		try{
			albums = em.createNamedQuery("Album.findAll", Album.class).getResultList();
		}catch(NoResultException nre){
			em.close();
			return albums;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return albums;
	}
	
	public Album getAlbumByName(String albumName){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		Album album = new Album();
		try{
			album = (Album)em.createNamedQuery("Album.getAlbumByName", Album.class).setParameter("album", albumName).getSingleResult();
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return album;
	}
	
	public List<User> getUsers(){
		
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<User> users = new ArrayList<User>();
		try{
			users = em.createNamedQuery("User.findAll", User.class).getResultList();
		}catch(NoResultException nre){
			em.close();
			return users;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return users;
		
	}
	
	public User getUserByUserId(int userId){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		User user = new User();
		try{
			user = (User)em.createNamedQuery("User.getUsersByUserId", User.class).setParameter("userId", userId).getSingleResult();
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return user;
	}
	
	public User getUserByEmail(String email){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		User user = new User();
		try{
			user = (User)em.createNamedQuery("User.getUsersByEmail", User.class).setParameter("email", email).getSingleResult();
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return user;
		 
	
	}
	
	public List<Genre> getGenre(){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Genre> genres = new ArrayList<Genre>();
		try{
			genres = em.createNamedQuery("Genre.findAll", Genre.class).getResultList();
		}catch(NoResultException nre){
			em.close();
			return genres;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return genres;
	}
	
	
	public Genre getGenreByName(String genreName){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		Genre genre = new Genre();
		try{
			genre = (Genre)em.createNamedQuery("Genre.getGenreByName", Genre.class).setParameter("genre", genreName).getSingleResult();
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return genre;
	}
	
	public Country getCountryByName(String countryName){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		Country country = new Country();
		try{
			country = (Country)em.createNamedQuery("Country.getCountryByName", Country.class).setParameter("country", countryName).getSingleResult();
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return country;
	}
	
	public List<Userandlike> getUserandlikes(){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		List<Userandlike> uls = new ArrayList<Userandlike>();
		try{
			uls = em.createNamedQuery("Userandlike.findAll", Userandlike.class).getResultList();
		}catch(NoResultException nre){
			em.close();
			return null;
		}finally{
			if(em.isOpen())
				em.close();
		}
		return uls;
	}
	
	
	
	

	public boolean insertObject(Object object){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		try{
			EntityTransaction entr = em.getTransaction();
			try{
			     entr.begin();
			     em.persist(object);
			     entr.commit();
			     return true;
			 }
			 catch(EntityExistsException e){
				 System.out.println("Oject already exist");
				 return false;
			 }
			 catch(Exception ex){
				 ex.printStackTrace();
			     return false;
			 }
			 finally{
				 if (entr.isActive()) 
					 entr.rollback();
			 }
		}finally{
			em.close();
		}
		 
	}
	public boolean mergeObject(Object object){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		try{
			EntityTransaction entr = em.getTransaction();
			try{
			     entr.begin();
			     em.merge(object);
			     entr.commit();
			     return true;
			 }
			 catch(EntityExistsException e){
				 System.out.println("Oject already exist");
				 return false;
			 }
			 catch(Exception ex){
				 ex.printStackTrace();
			     return false;
			 }
			 finally{
				 if (entr.isActive()) 
					 entr.rollback();
			 }
		}finally{
			em.close();
		}
		 
	}
	
	public boolean updatePassword(String email, String newPassword){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		User user = getUserByEmail(email);
		try{
			EntityTransaction entr = em.getTransaction();
			try{
				
				entr.begin();
				user.setPassword(newPassword);
				entr.commit();
				return true;
				
			}catch(Exception e){
				 System.out.println("Error in updating object");
			     return false;
			}finally{
				 if (entr.isActive()) 
					 entr.rollback();
			}
		}finally{
			em.close();
		}
	}
	
	public boolean updateActiveStatus(String email){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		try{
			EntityTransaction entr = em.getTransaction();
			try{
				
				entr.begin();
				User user = (User)em.createNamedQuery("User.getUsersByEmail", User.class).setParameter("email", email).getSingleResult();
				user.setActive(1);
				entr.commit();
				return true;
				
			}catch(Exception e){
				 System.out.println("Error in updating object");
			     return false;
			}finally{
				 if (entr.isActive()) 
					 entr.rollback();
			}
		}finally{
			em.close();
		}
	}
	
	public boolean updateLikeForSong(String songName, int like){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		try{
			EntityTransaction entr = em.getTransaction();
			try{
				
				entr.begin();
				Song song  = (Song)em.createNamedQuery("Song.getSongByName", Song.class).setParameter("song", songName).getSingleResult();
				song.setRate(like);;
				entr.commit();
				return true;
				
			}catch(Exception e){
				 System.out.println("Error in updating object");
			     return false;
			}finally{
				 if (entr.isActive()) 
					 entr.rollback();
			}
		}finally{
			em.close();
		}
	}
	
	public boolean updateLenth(String albumName, double newDuration){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		Album album = getAlbumByName(albumName);
		try{
			EntityTransaction entr = em.getTransaction();
			try{
				
				entr.begin();
				album.setLenth(newDuration);
				entr.commit();
				return true;
				
			}catch(Exception e){
				 System.out.println("Error in updating object");
			     return false;
			}finally{
				 if (entr.isActive()) 
					 entr.rollback();
			}
		}finally{
			em.close();
		}
	}
	
	
	public boolean removeObject(Object object){
		EntityManager em = Persistence.createEntityManagerFactory("MusicLibrary").createEntityManager();
		try{
			EntityTransaction entr = em.getTransaction();
			try{
			     entr.begin();
			     em.remove(object);
			     entr.commit();
			     return true;
			 }
			 catch(EntityExistsException e){
				 System.out.println("Oject already exist");
				 return false;
			 }
			 catch(Exception ex){
				 ex.printStackTrace();
			     return false;
			 }
			 finally{
				 if (entr.isActive()) 
					 entr.rollback();
			 }
		}finally{
			em.close();
		}
	}
	
	
}
