package org.jboss.as.quickstarts.library;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class Library implements Serializable {
	/*
	 * Fields to store user input for adding a new item
	 */
	private String title;
	private String author;
	private String genre;

	@Inject
	private ItemResource resource;
	private List<LibraryItem> items;

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
	 * Gets the item list from the database
	 */
	@PostConstruct
	public void init() {
		items = resource.getItemList();
	}
}