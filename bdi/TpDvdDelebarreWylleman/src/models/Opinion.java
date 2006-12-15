package models;

/**
 * Un avis.
 */
public class Opinion implements Model {
	
	/**
	 * L'identifiant de l'auteur de l'avis.
	 */
	private final int author;
		
	/**
	 * L'identifiant du DVD commente.
	 */
	private final int dvd;
		
	/**
	 * Le commentaire.
	 */
	private String text;
	
	/**
	 * Construit un nouvel <code>Avis</code> a partir des identifiants de l'auteur de l'avis et du dvd commente.
	 */
	public Opinion(int dvdId, int authorId) {
		this.author = authorId;
		this.dvd = dvdId;
		this.text = null;
	}
	
	/**
	 * Renvoie l'identifiant de l'auteur de l'avis.
	 */
	public int getAuthorId() {
		return this.author;
	}
	
	/**
	 * Renvoie l'identifiant du DVD commente.
	 */
	public int getDVDId() {
		return this.dvd;
	}
	
	/**
	 * Renvoie le commentaire.
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Affecte le commentaire avec la commentaire passe en parametre.
	 */
	public void setText(String text) {
		this.text = text;
	}
	
}