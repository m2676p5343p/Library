package org.jboss.as.quickstarts.library;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class Library implements Serializable {
	/*
	 * Fields to store user input for adding a new book
	 */
	private String title;
	private String author;
	private String genre;

	/**
	 * Stores Book objects
	 */
	@Inject
	private BookResource br;
	private List<Book> books;

	/*
	 * Getters and setters
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<Book> getBooks() {
		return this.books;
	}
	
	/**
	 * Instantiates the items ArrayList. Reads the CSV file and instantiates Book and Dvd objects
	 * from the CSV to store in the ArrayList.
	 */
	@PostConstruct
	public void init() {
		books = br.getBookList();
	}

	public void addBook() {
		Book newBook = new Book(this.title, this.author, this.genre);
		if (books.contains(newBook)) {
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Book already exists in database!")
			);
		} else {
			// calls BookResource to add the new book to the database
			br.createBook(newBook);
			// refreshes the book list
			books = br.getBookList();
		}
	}
}