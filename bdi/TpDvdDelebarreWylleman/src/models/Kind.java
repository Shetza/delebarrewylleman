package models;

public class Kind extends AModel {
	
	private String name;
	
	public Kind(int id) {
		super(id);
		this.name = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}