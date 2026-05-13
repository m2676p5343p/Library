package org.jboss.as.quickstarts.library;


/**
 * Represents a book. Extends the LibraryItem class and adds new fields: author
 * and genre.
 */
public class Book extends LibraryItem{
	private String author;
	private String genre;
	
	public Book(String title, String author, String releaseYear, String genre) {
		super(title, releaseYear, "Book");
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
}