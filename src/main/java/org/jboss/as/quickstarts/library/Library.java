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
	// Used for executing queries on the database
	@Inject
	private ItemResource resource;
	// Stores all library items
	private List<LibraryItem> items;

	/*
	 * Getters and setters
	 */
	public List<LibraryItem> getItems() {
		return this.items;
	}
	
	/**
	 * Gets the item list from the database
	 */
	@PostConstruct
	public void init() {
		items = resource.getItemList();
	}
}