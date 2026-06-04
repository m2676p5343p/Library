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
     * Stores the type of library item as a string (e.g. 'book'). This is only
     * here so the frontend can determine the type of item when deciding which
     * properties to display
     */
    @Transient
    protected String type;

    /**
     * Constructors
     */
    public LibraryItem() {}

    public LibraryItem(String title, String genre, boolean available) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Transient
    public abstract String getType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
               //"type='" + this.type + "'" +
               ", id='" + this.id + "'" +
               ", title='" + this.title + "'" +
               ", genre='" + this.genre + "'" +
               ", available='" + this.available + "'" +
               "}";
    }
}
