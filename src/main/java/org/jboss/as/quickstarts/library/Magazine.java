package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="magazines")
@PrimaryKeyJoinColumn(name="id")
public class Magazine extends LibraryItem {
    /**
     * Fields
     */
    @Column(nullable=false)
    private int numPages;

    /**
     * Constructors
     */
    // Blank constructor is required
    public Magazine() {}

    public Magazine(String title, String genre, boolean available, int numPages)
    throws NullPointerException, IllegalArgumentException
    {
        super(title, genre, available);
        if (numPages <= 0) {
            throw new IllegalArgumentException("Number of pages must be greater than 0");
        }
        this.numPages = numPages;
    }

    /**
     * Getters and setters
     */
    public int getNumPages() {
        return this.numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    @Override
    public String getType() {
        return "magazine";
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Magazine)) return false;
		Magazine magazine = (Magazine)o;
		return super.equals(magazine) &&
			   Float.compare(magazine.numPages, this.numPages) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.title, this.numPages, this.genre);
	}
}
