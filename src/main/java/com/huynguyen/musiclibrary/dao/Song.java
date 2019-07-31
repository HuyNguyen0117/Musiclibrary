package com.huynguyen.musiclibrary.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the song database table.
 * 
 */
@Entity
@Table(name="song")
@NamedQueries({
	@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s"),
	@NamedQuery(name="Song.getSongByName", query="SELECT s FROM Song s WHERE s.name = :song"),
	@NamedQuery(name="Song.getSongsByCountry", query="SELECT s FROM Song s WHERE s.country = :country"),
	@NamedQuery(name="Song.searchSongs", query="SELECT s FROM Song s WHERE s.name like :keysearch")
})
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idsong;

	private String date;

	private String description;

	private double lenth;

	private String lyris;

	private String name;

	private int rate;

	private String url;

	private int view;

	//bi-directional many-to-many association to Album
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		name="track"
		, joinColumns={
			@JoinColumn(name="song_idsong")
			}
		, inverseJoinColumns={
			@JoinColumn(name="album_idalbum")
			}
		)
	private List<Album> albums;

	//bi-directional many-to-many association to Artist
	@ManyToMany
	@JoinTable(
		name="song_artist"
		, joinColumns={
			@JoinColumn(name="song_idsong")
			}
		, inverseJoinColumns={
			@JoinColumn(name="artist_idartist")
			}
		)
	private List<Artist> artists;

	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	private Genre genre;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="song_user"
		, joinColumns={
			@JoinColumn(name="song_idsong")
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_iduser")
			}
		)
	private List<User> users;

	public Song() {
	}

	public int getIdsong() {
		return this.idsong;
	}

	public void setIdsong(int idsong) {
		this.idsong = idsong;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLenth() {
		return this.lenth;
	}

	public void setLenth(double lenth) {
		this.lenth = lenth;
	}

	public String getLyris() {
		return this.lyris;
	}

	public void setLyris(String lyris) {
		this.lyris = lyris;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getView() {
		return this.view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}