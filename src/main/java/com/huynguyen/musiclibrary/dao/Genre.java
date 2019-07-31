package com.huynguyen.musiclibrary.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@Table(name="genre")
@NamedQueries({
	@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g"),
	@NamedQuery(name="Genre.getGenreByName", query="SELECT g FROM Genre g WHERE g.type = :genre")
})
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idgenre;

	private String type;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="genre", fetch = FetchType.EAGER)
	private List<Song> songs;

	public Genre() {
	}

	public int getIdgenre() {
		return this.idgenre;
	}

	public void setIdgenre(int idgenre) {
		this.idgenre = idgenre;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setGenre(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setGenre(null);

		return song;
	}

}