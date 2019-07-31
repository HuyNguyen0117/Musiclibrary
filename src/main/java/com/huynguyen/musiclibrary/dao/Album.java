package com.huynguyen.musiclibrary.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@Table(name="album")
@NamedQueries({
	@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a"),
	@NamedQuery(name="Album.getAlbumByName", query="SELECT a FROM Album a WHERE a.name = :album"),
	@NamedQuery(name="Album.getAlbumsByCountry", query="SELECT a FROM Album a WHERE a.country =:country"),
	@NamedQuery(name="Album.searchAlbums", query="SELECT a FROM Album a WHERE a.name like :keysearch")
	
})
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idalbum;

	private String date;

	private String description;

	private String image;

	private double lenth;

	private String name;

	private int rate;

	private int view;

	//bi-directional many-to-many association to Artist
	@ManyToMany
	@JoinTable(
		name="album_artist"
		, joinColumns={
			@JoinColumn(name="album_idalbum")
			}
		, inverseJoinColumns={
			@JoinColumn(name="artist_idartist")
			}
		)
	private List<Artist> artists;

	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	//bi-directional many-to-many association to Song
	@ManyToMany(mappedBy="albums", fetch = FetchType.EAGER)
	private List<Song> songs;

	public Album() {
	}

	public int getIdalbum() {
		return this.idalbum;
	}

	public void setIdalbum(int idalbum) {
		this.idalbum = idalbum;
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

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getLenth() {
		return this.lenth;
	}

	public void setLenth(double lenth) {
		this.lenth = lenth;
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

	public int getView() {
		return this.view;
	}

	public void setView(int view) {
		this.view = view;
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

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

}