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
    /**
     * Fields
     */
    // Stores the newly created item
    private LibraryItem newItem;
    private String title;
    private String author;
    private String genre;
    private float duration;

    // The type of the new item
    private String newItemType;
    // Stores all 
    private String[] types;
    @SuppressWarnings("unused")
    private String formPath;

    //TODO: There must be a better way to do this. Investigate whether these
    //forms can be loaded just in XHTML based on the drop-down box selection
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

    /**
     * Getters and setters
     */
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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
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
        try {
            if (newItemType.equals("book")) {
                newItem = new Book(title, author, genre, true);
            }
            else if (newItemType.equals("dvd")) {
                newItem = new Dvd(title, duration, genre, true);
            }
            resource.createItem(newItem);
            return "home?faces-redirect=true";
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(e.getMessage())
            );
            return null;
        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(e.getMessage())
            );
            return null;
        }
    }
}
