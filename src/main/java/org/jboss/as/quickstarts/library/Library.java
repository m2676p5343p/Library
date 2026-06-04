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
	private float duration;

	/**
	 * Stores LibraryItem objects
	 */
	private List<LibraryItem> items;
	@Inject
	private BookResource br;

	/*
	 * Stores the selected item when a button is pressed that acts on a
	 * specific item
	 */
	LibraryItem selectedItem;

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

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public List<LibraryItem> getItems() {
		return this.items;
	}

	public LibraryItem getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(LibraryItem selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	/**
	 * Gets the book list from the database
	 */
	@PostConstruct
	public void init() {
		items = br.getItemList();
	}

	public String addBook() {
		Book newBook = new Book(this.title, this.author, this.genre, true);
		if (items.contains(newBook)) {
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Book already exists in database!")
			);
		} else {
			// calls BookResource to add the new book to the database
			br.createItem(newBook);
			// refreshes the book list
			items = br.getItemList();
		}
		return "home?faces-redirect=true";
	}

	public String addDvd() {
		Dvd newDvd = new Dvd(this.title, this.duration, this.genre, true);
		if (items.contains(newDvd)) {
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Dvd already exists in database!")
			);
		} else {
			br.createItem(newDvd);
			items = br.getItemList();
		}
		return "home?faces-redirect=true";
	}

	public String editBook() { 
		br.updateBook(see.getId(),new Book(
			selectedBook.getTitle(),
			selectedBook.getAuthor(),
			selectedBook.getGenre(),
			selectedBook.getAvailable()
		));
		return "home?faces-redirect=true";
	}
}