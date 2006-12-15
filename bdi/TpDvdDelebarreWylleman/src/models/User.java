package models;

/**
 * Un utilisateur du service de partage de DVDs.
 */ 
public class User implements Model {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final int id;
		
	/**
	 * Le nom de l'utilisateur.
	 */
	private String lastName;
		
	/**
	 * Le prenom de l'utilisateur.
	 */
	private String firstName;
		
	/**
	 * Le login de l'utilisateur.
	 */
	private String login;
		
	/**
	 * Le mot de passe de l'utilisateur.
	 */
	private String password;
	
	/**
	 * Construit un nouvel utilisateur a partir de l'identifiant specifie.
	 */
	public User(int id) {
		this.id = id;
		this.lastName = null;
		this.firstName = null;
		this.login = null;
		this.password = null;
	}
	
	/**
	 * Renvoie le nom complet de l'utilisateur.
	 */
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
	
	/**
	 * Renvoie l'identifiant de l'utilisateur.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Renvoie le nom de l'utilisateur.
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Affecte le nom de l'utilisateur avec celui passe en parametre.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Renvoie le prenom de l'utilisateur.
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Affecte le prenom de l'utilisateur avec celui passe en parametre.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Renvoie le login de l'utilisateur.
	 */
	public String getLogin() {
		return this.login;
	}
	
	/**
	 * Affecte le login de l'utilisateur avec celui passe en parametre.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Renvoie le mot de passe de l'utilisateur.
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Affecte le mot de passe de l'utilisateur avec celui passe en parametre.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}