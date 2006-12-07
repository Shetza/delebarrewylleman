package models;

public abstract class AModel implements Model {
	
	private final int id;
	
	public AModel(int id) {
		this.id = id;
	}
	
	public final int getId() {
		return this.id;
	}
	
}