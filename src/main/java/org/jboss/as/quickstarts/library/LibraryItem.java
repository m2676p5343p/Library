package org.jboss.as.quickstarts.library;


/**
 * Represents a generic library item. Other classes extend this class to represent
 * a more specific library item.
 */
public abstract class LibraryItem {
	private String title;
	private String releaseYear;
	private String type;
	
	public LibraryItem(String title, String releaseYear, String type) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.type = type;
	}
	
	/**
	 * Getters and setters
	 */
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getReleaseYear() {
		return this.releaseYear;
	}
	
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}