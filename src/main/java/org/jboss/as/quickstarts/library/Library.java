package org.jboss.as.quickstarts.library;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@SuppressWarnings("serial")
@Named
@ViewScoped
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
	 * Stores the selected book when a button is pressed that acts on a
	 * specific book
	 */
	Book selectedBook;

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

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}
	
	/**
	 * Gets the book list from the database
	 */
	@PostConstruct
	public void init() {
		books = br.getBookList();
	}

	public String addBook() {
		Book newBook = new Book(this.title, this.author, this.genre, true);
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
		return "home?faces-redirect=true";
	}

	public void deleteBook() {
		br.deleteBook(selectedBook.getId());
		// refreshes the book list
		books = br.getBookList();
	}

	public String editBook() { 
		br.updateBook(selectedBook.getId(),new Book(
			selectedBook.getTitle(),
			selectedBook.getAuthor(),
			selectedBook.getGenre(),
			selectedBook.getAvailable()
		));
		return "home?faces-redirect=true";
	}
}