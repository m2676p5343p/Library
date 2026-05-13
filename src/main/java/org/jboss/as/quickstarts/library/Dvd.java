package org.jboss.as.quickstarts.library;


/**
 * Represents a DVD. Extends LibraryItem and adds a length property.
 */
public class Dvd extends LibraryItem {
	private float length;
	
	public Dvd(String title, String releaseYear, float length) {
		super(title, releaseYear, "DVD");
		this.length = length;
	}
	
	/**
	 * Getters and setters
	 */
	public float getLength() {
		return this.length;
	}
	
	public void setLength(float length) {
		this.length = length;
	}
}