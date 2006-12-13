package models;

public class Artist implements Model {
	
	private int id;
	private String name;
	private String function;
		
	public Artist(int id) {
		this.id = id;
		this.name = null;
		this.function = null;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFunction() {
		return this.function;
	}
	
	public void setFunction(String function) {
		this.function = function;
	}
	
}