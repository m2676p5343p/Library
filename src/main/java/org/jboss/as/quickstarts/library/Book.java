package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a book. Extends the LibraryItem class and adds new fields: author
 * and genre.
 */
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String title;

	@NotBlank
	@Column(nullable = false)
	private String author;

	@NotBlank
	@Column(nullable = false)
	private String genre;
	
	public Book() {}

	public Book(String title, String author, String genre) {
		this.title = title;
		this.author = author;
		this.genre = genre;
	}
	
	/**
	 * Getters and setters
	 */
	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book)o;
		return Objects.equals(title, book.title) &&
			   Objects.equals(author, book.author) &&
			   Objects.equals(genre, book.genre);
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
			   ", author'" + author + "'" +
			   ", genre'" + genre + "'" +
			   "}";
	}
}