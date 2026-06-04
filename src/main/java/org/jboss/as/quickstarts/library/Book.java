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
	public Book() {}

	public Book(String title, String author, String genre, boolean available) {
		this.author = author;
		super(title, genre, available, "book");
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book)o;
		return Objects.equals(this.title, book.title) &&
			   Objects.equals(this.author, book.author) &&
			   Objects.equals(this.genre, book.genre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.title, this.author, this.genre);
	}
}