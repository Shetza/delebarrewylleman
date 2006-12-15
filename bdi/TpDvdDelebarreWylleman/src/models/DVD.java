package models;

import java.util.Date;
import java.util.List;

/**
 * Un DVD.
 */ 
public class DVD implements Model {
	
	/**
	 * L'identifiant du DVD.
	 */
	private final int id;
		
	/**
	 * L'identifiant du proprietaire du DVD.
	 */
	private int user;
		
	/**
	 * L'identifiant de la categorie du DVD.
	 */
	private int kind;
		
	/**
	 * Le titre du DVD.
	 */
	private String title;
		
	/**
	 * La date de parution du DVD.
	 */
	private Date date;
	
	/**
	 * La liste des artistes apparaissant dans le DVD.
	 */
	private List artists;
	
	/**
	 * Construit un nouveau DVD a partir de l'identifiant specifie.
	 */
	public DVD(int id) {
		this.id = id;
		this.user = 0;
		this.kind = 0;
		this.title = null;
		this.date = null;
		this.artists = null;
	}
	
	/**
	 * Renvoie l'identifiant du DVD.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Renvoie le titre du DVD.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Affecte le titre du DVD avec celui passe en parametre.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Renvoie l'identifiant de la categorie du DVD.
	 */
	public int getKind() {
		return this.kind;
	}
	
	/**
	 * Affecte la categorie du DVD avec celle passee en parametre.
	 */
	public void setKind(int kind) {
		this.kind = kind;
	}
	
	/**
	 * Renvoie la date de parution du DVD.
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Affecte la date de parution du DVD avec celle passee en parametre.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Renvoie l'identifiant du proprietaire du DVD.
	 */
	public int getUser() {
		return this.user;
	}
	
	/**
	 * Affecte l'identifiant du proprietaire du DVD avec celui passe en parametre.
	 */
	public void setUser(int user) {
		this.user = user;
	}
	
	/**
	 * Renvoie la liste des artistes apparaissant dans le DVD.
	 */
	public List getArtists() {
		return this.artists;
	}
	
	/**
	 * Affecte la liste des artistes apparaissant dans le DVD avec celle passee en parametre.
	 */
	public void setArtists(List artists) {
		this.artists = artists;
	}
	
}