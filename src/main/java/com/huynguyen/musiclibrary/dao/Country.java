package com.huynguyen.musiclibrary.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQueries({
	@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c"),
	@NamedQuery(name="Country.getCountryByName", query="SELECT c FROM Country c WHERE c.name = :country")
})
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcountry;

	private String name;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="country")
	private List<Album> albums;

	//bi-directional many-to-one association to Artist
	@OneToMany(mappedBy="country")
	private List<Artist> artists;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="country")
	private List<Song> songs;

	public Country() {
	}

	public int getIdcountry() {
		return this.idcountry;
	}

	public void setIdcountry(int idcountry) {
		this.idcountry = idcountry;
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

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setCountry(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setCountry(null);

		return album;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Artist addArtist(Artist artist) {
		getArtists().add(artist);
		artist.setCountry(this);

		return artist;
	}

	public Artist removeArtist(Artist artist) {
		getArtists().remove(artist);
		artist.setCountry(null);

		return artist;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setCountry(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setCountry(null);

		return song;
	}

}