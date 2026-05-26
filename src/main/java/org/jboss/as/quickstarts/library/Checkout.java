package org.jboss.as.quickstarts.library;

import java.sql.Date;
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
    @JoinColumn(name = "book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(nullable = false)
    private Date checkoutDate;

    /**
     * Constructors
     */
    public Checkout() {}

    public Checkout(Book book, Customer customer) {
        this.book = book;
        this.customer = customer;
        java.util.Date utilDate = new java.util.Date();
        this.checkoutDate = new Date(utilDate.getTime());
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getCheckoutDate() {
        return this.checkoutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Checkout checkout = (Checkout)o;
		return Objects.equals(customer, checkout.customer) &&
			   Objects.equals(book, checkout.book) &&
			   Objects.equals(checkoutDate, checkout.checkoutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, customer, checkoutDate);
    }

    @Override
	public String toString() {
		return "Checkout{" +
			   "id=" + id +
			   ", customer='" + customer.toString() + "'" +
			   ", book'" + book.toString() + "'" +
			   ", checkoutDate'" + checkoutDate + "'" +
			   "}";
	}
}