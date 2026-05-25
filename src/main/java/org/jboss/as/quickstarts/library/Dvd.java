package org.jboss.as.quickstarts.library;


/**
 * Represents a DVD. Extends LibraryItem and adds a length property.
 */
public class Dvd extends LibraryItem {
	private float length;
	
	public Dvd(String title, String releaseYear, float length, boolean available) {
		super(title, releaseYear, "DVD", available);
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
	
	@Override
	public boolean equals(Object t) {
		// If the passed object is null throw an exception
		if (t == null) {
			throw new NullPointerException();
		}
		// If the object is of type Dvd then compare all fields
		if (t.getClass() == getClass()) {
			Dvd other = (Dvd)t;
			return this.getTitle().equals(other.getTitle()) &&
				   this.getReleaseYear().equals(other.getReleaseYear()) &&
				   this.getLength() == other.getLength();
		// If the object is not of type Dvd then return false
		} else {
			return false;
		}
	}
}