package org.jboss.as.quickstarts.library;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
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
    private float duration;
    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    private String newItemType;
    private String[] types;
    @SuppressWarnings("unused")
    private String formPath;

    public String getFormPath() {
        if ("book".equals(newItemType)) {
            return "/WEB-INF/newbookform.xhtml";
        } else if ("dvd".equals(newItemType)) {
            return "/WEB-INF/newdvdform.xhtml";
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
        types = new String[] {"book", "dvd"};
        newItemType = types[0];
    }

    public String addItem() {
        if (newItemType.equals("book")) {
            newItem = new Book(title, author, genre, true);
        }
        else if (newItemType.equals("dvd")) {
            newItem = new Dvd(title, duration, genre, true);
        }
        resource.createItem(newItem);
        return "home?faces-redirect=true";
    }
}
