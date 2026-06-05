package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="dvds")
@PrimaryKeyJoinColumn(name="id")
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
    public String getType() {
        return "dvd";
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dvd dvd = (Dvd)o;
		return Objects.equals(this.title, dvd.title) &&
			   this.duration == dvd.duration &&
			   Objects.equals(this.genre, dvd.genre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.title, this.duration, this.genre);
	}
}
