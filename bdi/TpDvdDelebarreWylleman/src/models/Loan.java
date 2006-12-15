package models;

import java.util.Date;

/**
 * L'emprunt d'un DVD par un utilisateur.
 */
public class Loan implements Model {
	
	/**
	 * L'identifiant de l'utilisateur qui emprunte.
	 */
	private final int user;
		
	/**
	 * L'identifiant du DVD emprunte.
	 */
	private final int dvd;
		
	/**
	 * Le titre du DVD emprunte.
	 */
	private String dvdTitle;
		
	/**
	 * La date d'emprunt.
	 */
	private final Date begin;
		
	/**
	 * La date limite de rendu du DVD.
	 */
	private Date limit;
		
	/**
	 * La date effective de rendu du DVD.
	 */
	private Date end;
		
	/**
	 * Le nombre de demande de prolongement de l'emprunt.
	 */
	private int extraTime;
		
	/**
	 * L'identifiant de l'utilisateur qui reserve le DVD emprunte.
	 */
	private int reserveUser;
	
	/**
	 * Construit un nouvel emprunt a partir des identifiants du DVD emprunte et de l'utilisateur ainsi que la date d'emprunt.
	 */
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
	
	/**
	 * Renvoie l'identifiant de l'utilisateur qui emprunte.
	 */
	public int getUser() {
		return this.user;
	}
	
	/**
	 * Renvoie l'identifiant du DVD emprunte.
	 */
	public int getDVDId() {
		return this.dvd;
	}
	
	/**
	 * Renvoie la date de l'emprunt.
	 */
	public Date getBegin() {
		return this.begin;
	}
	
	/**
	 * Renvoie le titre du DVD emprunte.
	 */
	public String getDVDTitle() {
		return this.dvdTitle;
	}
	
	/**
	 * Affecte le titre du DVD emprunte avec celui passe en parametre.
	 */
	public void setDVDTitle(String dvdTitle) {
		this.dvdTitle = dvdTitle;
	}
	
	/**
	 * Renvoie la date limite de rendu du DVD.
	 */
	public Date getLimit() {
		return this.limit;
	}
	
	/**
	 * Affecte la date limite de rendu du DVD avec celle passee en parametre.
	 */
	public void setLimit(Date limit) {
		this.limit = limit;
	}
	
	/**
	 * Renvoie la date effective de rendu du DVD.
	 */
	public Date getEnd() {
		return this.end;
	}
	
	/**
	 * Affecte la date effective de rendu du DVD avec celle passee en parametre.
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
	
	/**
	 * Renvoie le nombre de demande de prolongement de l'emprunt.
	 */
	public int getExtraTime() {
		return this.extraTime;
	}
	
	/**
	 * Affecte le nombre de demande de prolongement de l'emprunt avec celui passe en parametre.
	 */
	public void setExtraTime(int extraTime) {
		this.extraTime = extraTime;
	}
	
	/**
	 * Renvoie l'identifiant de l'utilisateur qui reserve le DVD emprunte.
	 */
	public int getReserveUser() {
		return this.reserveUser;
	}
	
	/**
	 * Affecte l'identifiant de l'utilisateur qui reserve le DVD emprunte avec celui passe en parametre.
	 */
	public void setReserveUser(int reserveUser) {
		this.reserveUser = reserveUser;
	}
	
}