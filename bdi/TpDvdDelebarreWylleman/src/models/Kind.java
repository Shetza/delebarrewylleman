package models;

public class Kind implements Model {
	
	private final int id;
	private String name;
	
	public Kind(int id) {
		this.id = id;
		this.name = null;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}