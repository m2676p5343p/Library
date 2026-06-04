package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "library_items")
public class LibraryItem {
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

    private String type;

    /**
     * Constructors
     */
    public LibraryItem() {}

    public LibraryItem(String title, String genre, boolean available, String type) {
        this.title = title;
        this.genre = genre;
        this.available = available;
        this.type = type;
    }

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LibraryItem item = (LibraryItem)o;
		return Objects.equals(title, item.title) &&
			   Objects.equals(genre, item.genre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, genre);
	}

	@Override
	public String toString() {
		return "Item{" +
			   "id=" + id +
			   ", title='" + title + "'" +
			   ", genre='" + genre + "'" +
               ", available='" + available + "'" +
			   "}";
	}
}
