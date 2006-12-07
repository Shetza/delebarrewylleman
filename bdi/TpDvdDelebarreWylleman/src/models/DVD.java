package models;

import java.util.Date;

public class DVD extends AModel {
	
	private int user;
	private int kind;
	private String title;
	private Date date;
	
	public DVD(int id) {
		super(id);
		this.user = -1;
		this.kind = -1;
		this.title = null;
		this.date = null;
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
	
}