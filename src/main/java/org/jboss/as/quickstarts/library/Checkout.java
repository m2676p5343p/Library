package org.jboss.as.quickstarts.library;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "checkouts")
public class Checkout {
    /**
     * Fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "itemId")
    private LibraryItem item;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column(nullable = false)
    private java.sql.Date checkoutDate;

    @Column(nullable = false)
    private java.sql.Date dueDate;

    @Transient
    private boolean overdue;

    /**
     * Constructors
     */
    public Checkout() {}

    public Checkout(LibraryItem item, Customer customer) {
        this.item = item;
        this.customer = customer;
        // Stores current date as checkoutDate
        java.util.Date utilDate = new java.util.Date();
        this.checkoutDate = new java.sql.Date(utilDate.getTime());

        // Converts checkoutDate to LocalDate in order to add 2 weeks to it
        LocalDate localDate = this.checkoutDate.toLocalDate();
        LocalDate localDateTwoWeeksLater = localDate.plusWeeks(2);

        // The 2 weeks later date is converted back to java.sql.Date and used
        // as the dueDate property
        this.dueDate = java.sql.Date.valueOf(localDateTwoWeeksLater);
    }

    public Long getId() {
        return id;
    }

    public LibraryItem getItem() {
        return item;
    }

    public Customer getCustomer() {
        return customer;
    }

    public java.sql.Date getCheckoutDate() {
        return this.checkoutDate;
    }

    public java.sql.Date getDueDate() {
        return this.dueDate;
    }

    @Transient
    public boolean getOverdue() {
        java.sql.Date currentDate = new java.sql.Date(
            new java.util.Date().getTime()
        );
        return currentDate.after(this.dueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Checkout checkout = (Checkout)o;
		return Objects.equals(customer, checkout.customer) &&
			   Objects.equals(item, checkout.item) &&
			   Objects.equals(checkoutDate, checkout.checkoutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, customer, checkoutDate);
    }

    @Override
	public String toString() {
		return "Checkout{" +
			   "id=" + id +
			   ", customer='" + customer.toString() + "'" +
			   ", item'" + item.toString() + "'" +
			   ", checkoutDate'" + checkoutDate + "'" +
			   "}";
	}
}