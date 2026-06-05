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
    // Blank constructor is required
    public Dvd() {}

    public Dvd(String title, float duration, String genre, boolean available)
    throws NullPointerException, IllegalArgumentException {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        this.duration = duration;

        super(title, genre, available);
    }

    /**
     * Getters and setters
     */
    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float duration) throws IllegalArgumentException {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        this.duration = duration;
    }

    @Override
    public String getType() {
        return "dvd";
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Dvd)) return false;
		Dvd dvd = (Dvd)o;
		return super.equals(dvd) &&
			   Float.compare(dvd.duration, this.duration) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.title, this.duration, this.genre);
	}
}
