package models;

public class Opinion implements Model {
	
	private int author;
	private int dvd;
	private String text;
	
	public Opinion(int dvdId, int authorId) {
		this.author = authorId;
		this.dvd = dvdId;
		this.text = null;
	}
	
	public int getAuthorId() {
		return this.author;
	}
	
	public int getDVDId() {
		return this.dvd;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}