package models;

public class User implements Model {
	
	private final int id;
	private String lastName;
	private String firstName;
	private String login;
	private String password;
	
	public User(int id) {
		this.id = id;
		this.lastName = null;
		this.firstName = null;
		this.login = null;
		this.password = null;
	}
	
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}