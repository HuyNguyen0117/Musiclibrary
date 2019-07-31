package com.huynguyen.musiclibrary.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the artist database table.
 * 
 */
@Entity
@Table(name="artist")
@NamedQueries({
	@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a"),
	@NamedQuery(name="Artist.getArtistsByCountry", query="SELECT a FROM Artist a WHERE a.country = :country"),
	@NamedQuery(name="Artist.getArtistByName", query="SELECT a FROM Artist a WHERE a.name = :artist"),
	@NamedQuery(name="Artist.searchArtists", query="SELECT a FROM Artist a WHERE a.name like :keysearch")

	
})
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idartist;

	private String description;

	private String dob;

	private String image;

	private String name;

	//bi-directional many-to-many association to Album
	@ManyToMany(mappedBy="artists", fetch = FetchType.EAGER)
	private List<Album> albums;

	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	//bi-directional many-to-many association to Song
	@ManyToMany(mappedBy="artists", fetch = FetchType.EAGER)
	private List<Song> songs;

	public Artist() {
	}

	public int getIdartist() {
		return this.idartist;
	}

	public void setIdartist(int idartist) {
		this.idartist = idartist;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
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