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
    public Customer() {}

    public Customer(String firstName, String lastName) {
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer)o;
		return Objects.equals(id, customer.getId());
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
