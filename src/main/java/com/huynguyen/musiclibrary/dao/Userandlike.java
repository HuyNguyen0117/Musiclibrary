package com.huynguyen.musiclibrary.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userandlike database table.
 * 
 */
@Entity
@Table(name="userandlike")
@NamedQuery(name="Userandlike.findAll", query="SELECT u FROM Userandlike u")
public class Userandlike implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idlike;

	@Column(name="song_idsong")
	private int songIdsong;

	@Column(name="user_iduser")
	private int userIduser;

	public Userandlike() {
	}

	public int getIdlike() {
		return this.idlike;
	}

	public void setIdlike(int idlike) {
		this.idlike = idlike;
	}

	public int getSongIdsong() {
		return this.songIdsong;
	}

	public void setSongIdsong(int songIdsong) {
		this.songIdsong = songIdsong;
	}

	public int getUserIduser() {
		return this.userIduser;
	}

	public void setUserIduser(int userIduser) {
		this.userIduser = userIduser;
	}

}