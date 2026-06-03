package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;

public class Dvd extends LibraryItem {
    /**
	 * Fields
	 */
	@Column(nullable = false)
	private float duration;

    /**
     * Constructors
     */
    public Dvd() {}

    public Dvd(String title, float duration, String genre, boolean available) {
        this.duration = duration;

        super(title, genre, available);
    }

    /**
     * Getters and setters
     */
    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		// Is this how it's done?
		LibraryItem item = (LibraryItem)o;
		if (!super.equals(item)) return false;
		Dvd dvd = (Dvd)item;
		return Objects.equals(duration, dvd.duration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, duration, genre);
	}

	@Override
	public String toString() {
		return "Book{" +
			   "id=" + id +
			   ", title='" + title + "'" +
			   ", duration='" + duration + " minutes'" +
			   ", genre='" + genre + "'" +
			   ", available='" + available + "'" +
			   "}";
	}
}
