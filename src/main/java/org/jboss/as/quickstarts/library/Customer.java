package org.jboss.as.quickstarts.library;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a customer.
 */
@Entity
@Table(name = "customers")
public class Customer {
    /**
     * Fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    /**
     * Constructors
     */
    // Blank constructor is required
    public Customer() {}

    public Customer(String firstName, String lastName)
    throws NullPointerException, IllegalArgumentException {
        if (firstName == null || lastName == null) {
            throw new NullPointerException("Customer details must not be null");
        } else if (firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("Customer details must not be empty or blank");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName)
    throws NullPointerException, IllegalArgumentException {
        if (firstName == null) {
            throw new NullPointerException("First name must not be null");
        } else if (firstName.isBlank()) {
            throw new IllegalArgumentException("First name must not be blank or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)
    throws NullPointerException, IllegalArgumentException {
        if (lastName == null) {
            throw new NullPointerException("Last name must not be null");
        } else if (lastName.isBlank()) {
            throw new IllegalArgumentException("Last name must not be blank or empty");
        }
        this.lastName = lastName;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer)o;
		return Objects.equals(customer.getFirstName(), this.firstName) &&
               Objects.equals(customer.getLastName(), this.lastName);
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName);
	}

	@Override
	public String toString() {
		return "Customer{" +
			   "id=" + id +
			   ", firstName='" + firstName + "'" +
			   ", lastName'" + lastName + "'" +
			   "}";
	}
}
