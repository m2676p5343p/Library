package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="library_items")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class LibraryItem {
    /**
     * Fields
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@NotBlank
	@Column(nullable = false)
	protected String title;

    @NotBlank
	@Column(nullable = false)
	protected String genre;

	@Column(nullable = false)
	protected boolean available;

    /**
     * This field only exists to backup the getType() method. When the frontend accesses
     * item.type, it calls getType which is an abstract method that must be implemented
     * by subclasses. The subclass implementation returns a String representing the type
     * of object it is ('book', 'dvd', etc.)
     * Marked @Transient so it is not stored in the database
     */
    @Transient
    private String type;

    /**
     * Constructors
     */
    // Blank constructor is required
    public LibraryItem() {}

    public LibraryItem(String title, String genre, boolean available)
    throws NullPointerException, IllegalArgumentException {
        if (title == null || genre == null) {
            throw new NullPointerException("Item properties must not be null");
        } else if (title.isBlank() || genre.isBlank()) {
            throw new IllegalArgumentException("Item properties must not be blank or empty");
        }
        this.title = title;
        this.genre = genre;
        this.available = available;
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

    public void setTitle(String title)
    throws NullPointerException, IllegalArgumentException {
        if (title == null) {
            throw new NullPointerException("Title must not be null");
        } else if (title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank or empty");
        }
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre)
    throws NullPointerException, IllegalArgumentException {
        if (genre == null) {
            throw new NullPointerException("Genre must not be null");
        } else if (genre.isBlank()) {
            throw new IllegalArgumentException("Genre must not be blank or empty");
        }
        this.genre = genre;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Abstract method required to be implemented by subtypes. Subtypes will
     * return a String representing what kind of library item they are
     */
    @Transient
    public abstract String getType();

    /**
     * Doesn't compare id's since there is no way for duplicate id's to exist
     * with the database constraints. Obviously it is possible for an item to
     * share genre and title but until more fields are added (such as ISBN) this
     * is as good as it gets.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryItem)) return false;
        LibraryItem item = (LibraryItem)o;
        return Objects.equals(this.title, item.title) &&
               Objects.equals(this.genre, item.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.genre, this.available);
    }

    @Override
    public String toString() {
        return "Item{" +
               ", id='" + this.id + "'" +
               ", title='" + this.title + "'" +
               ", genre='" + this.genre + "'" +
               ", available='" + this.available + "'" +
               "}";
    }
}
