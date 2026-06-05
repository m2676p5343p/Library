package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a book with a unique ID, title, author, and genre.
 */
@Entity
@Table(name="books")
@PrimaryKeyJoinColumn(name = "id")
public class Book extends LibraryItem {
	/**
	 * Fields
	 */
	@NotBlank
	@Column(nullable = false)
	private String author;
	
	/**
     * Constructors
     */
	// Blank constructor is required
	public Book() {}

	public Book(String title, String author, String genre, boolean available)
	throws NullPointerException, IllegalArgumentException {
		if (author == null) {
			throw new NullPointerException("Author must not be null");
		} else if (author.isBlank()) {
			throw new IllegalArgumentException("Author must not be empty or blank");
		}
		this.author = author;
		
		super(title, genre, available);
	}
	
	/**
	 * Getters and setters
	 */
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) 
	throws NullPointerException, IllegalArgumentException {
		if (author == null) {
			throw new NullPointerException("Author must not be null");
		} else if (author.isBlank()) {
			throw new IllegalArgumentException("Author must not be empty or blank");
		}
		this.author = author;
	}

	@Override
	public String getType() {
		return "book";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book book = (Book)o;
		return super.equals(book) &&
			   Objects.equals(this.author, book.getAuthor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.title, this.author, this.genre);
	}
}