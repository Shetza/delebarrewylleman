package models;

import java.util.Date;
import java.util.List;

public class DVD implements Model {
	
	private final int id;
	private int user;
	private int kind;
	private String title;
	private Date date;
	
	private List artists;
	
	public DVD(int id) {
		this.id = id;
		this.user = 0;
		this.kind = 0;
		this.title = null;
		this.date = null;
		this.artists = null;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getKind() {
		return this.kind;
	}
	
	public void setKind(int kind) {
		this.kind = kind;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getUser() {
		return this.user;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public List getArtists() {
		return this.artists;
	}
	
	public void setArtists(List artists) {
		this.artists = artists;
	}
	
}