package models;

/**
 * Un artiste d'un DVD.
 */ 
public class Artist implements Model {
	
	/**
	 * L'identifiant de l'artiste.
	 */
	private final int id;
		
	/**
	 * Le nom de l'artiste.
	 */
	private String name;
		
	/**
	 * La fonction de l'artiste.
	 */
	private String function;
		
	/**
	 * Construit un nouvel artiste a partir de l'identifiant specifie.
	 */
	public Artist(int id) {
		this.id = id;
		this.name = null;
		this.function = null;
	}
	
	/**
	 * Renvoie l'identifiant de l'artiste.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Renvoie le nom de l'artiste.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Affecte le nom de l'artiste avec celui passe en parametre.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Renvoie la fonction de l'artiste.
	 */
	public String getFunction() {
		return this.function;
	}
	
	/**
	 * Affecte la fonction de l'artiste avec celle passee en parametre.
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	
}