package models;

/**
 * Une categorie de DVD.
 */ 
public class Kind implements Model {
	
	/**
	 * L'identifiant de la categorie.
	 */
	private final int id;
		
	/**
	 * Le nom de la categorie.
	 */
	private String name;
	
	/**
	 * Construit une nouvelle categorie a partir de l'identifiant specifie.
	 */
	public Kind(int id) {
		this.id = id;
		this.name = null;
	}
	
	/**
	 * Renvoie l'identifiant de la categorie.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Renvoie le nom de la categorie.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Affecte le nom de la categorie avec celui passe en parametre.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}