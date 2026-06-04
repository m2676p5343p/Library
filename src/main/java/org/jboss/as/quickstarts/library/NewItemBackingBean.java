package org.jboss.as.quickstarts.library;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class NewItemBackingBean implements Serializable {
    private LibraryItem newItem;
    private String title;
    private String author;
    private String genre;
    private String newItemType;
    private String[] types;
    private String formPath;

    public String getFormPath() {
        if ("book".equals(newItemType)) {
            return "/WEB-INF/newbookform.xhtml";
        }
        return null;
    }

    @Inject
    private ItemResource resource;

    public LibraryItem getNewItem() {
        return newItem;
    }

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

    public String getNewItemType() {
        return this.newItemType;
    }

    public void setNewItemType(String newItemType) {
        this.newItemType = newItemType;
    }

    public String[] getTypes() {
        return types;
    }

    @PostConstruct
    public void init() {
        types = new String[] {"book"};
        newItemType = types[0];
    }

    public String addItem() {
        if (newItemType.equals("book")) {
            newItem = new Book(title, author, genre, true);
        }
        resource.createItem(newItem);
        return "home?faces-redirect=true";
    }
}
