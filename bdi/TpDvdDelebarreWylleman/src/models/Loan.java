package models;

import java.util.Date;

public class Loan implements Model {
	
	private int user;
	private int dvd;
	private String dvdTitle;
	private Date begin;
	private Date limit;
	private Date end;
	private int extraTime;
	private int reserveUser;
	
	public Loan(int dvdId, int userId, Date date) {
		this.user = userId;
		this.dvd = dvdId;
		this.begin = date;
		this.dvdTitle = null;
		this.limit = null;
		this.end = null;
		this.extraTime = 0;
		this.reserveUser = 0;
	}
	
	public int getUser() {
		return this.user;
	}
	
	public int getDVDId() {
		return this.dvd;
	}
	
	public Date getBegin() {
		return this.begin;
	}
	
	public String getDVDTitle() {
		return this.dvdTitle;
	}
	
	public void setDVDTitle(String dvdTitle) {
		this.dvdTitle = dvdTitle;
	}
	
	public Date getLimit() {
		return this.limit;
	}
	
	public void setLimit(Date limit) {
		this.limit = limit;
	}
	
	public Date getEnd() {
		return this.end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public int getExtraTime() {
		return this.extraTime;
	}
	
	public void setExtraTime(int extraTime) {
		this.extraTime = extraTime;
	}
	
	public int getReserveUser() {
		return this.reserveUser;
	}
	
	public void setReserveUser(int reserveUser) {
		this.reserveUser = reserveUser;
	}
	
}