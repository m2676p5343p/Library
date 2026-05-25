package org.jboss.as.quickstarts.library;


/**
 * Represents a book. Extends the LibraryItem class and adds new fields: author
 * and genre.
 */
public class Book extends LibraryItem{
	private String author;
	private String genre;
	
	public Book(String title, String author, String releaseYear, String genre, boolean available) {
		super(title, releaseYear, "Book", available);
		this.author = author;
		this.genre = genre;
	}
	
	/**
	 * Getters and setters
	 */
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public boolean equals(Object t) {
		// If the passed object is null throw an exception
		if (t == null) {
			throw new NullPointerException();
		}
		// If the object is of type Book then compare all fields
		if (t.getClass() == getClass()) {
			Book other = (Book)t;
			return this.getTitle().equals(other.getTitle()) &&
				   this.getReleaseYear().equals(other.getReleaseYear()) &&
				   this.getAuthor().equals(other.getAuthor()) &&
				   this.getGenre().equals(other.getGenre());
				   
		// If the object is not of type Book then return false
		} else {
			return false;
		}
	}
}