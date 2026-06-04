package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a book with a unique ID, title, author, and genre.
 */
@Entity
@Table(name = "books")
public class Book extends LibraryItem {
	/**
	 * Fields
	 */
	@NotBlank
	@Column(nullable = false)
	protected String author;

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

		// Is this how it's done?
		LibraryItem item = (LibraryItem)o;
		if (!super.equals(item)) return false;
		Book book = (Book)item;
		return Objects.equals(author, book.author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, author, genre);
	}

	@Override
	public String toString() {
		return "Book{" +
			   "id=" + id +
			   ", title='" + title + "'" +
			   ", author='" + author + "'" +
			   ", genre='" + genre + "'" +
			   ", available='" + available + "'" +
			   "}";
	}
}