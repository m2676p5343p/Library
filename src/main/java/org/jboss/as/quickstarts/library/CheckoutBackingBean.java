package org.jboss.as.quickstarts.library;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CheckoutBackingBean implements Serializable {
    private Long bookId;
    private Long customerId;

    @Inject
    private CheckoutResource cr;
    @Inject
    private BookResource br;
    @Inject
    private CustomerResource customerResource;

    private List<Checkout> checkouts;
    private List<Book> availableBooks;

    /**
     * Getters and setters
     */
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Checkout> getCheckouts() {
        return this.checkouts;
    }

    public List<Book> getAvailableBooks() {
        return this.availableBooks;
    }

    @PostConstruct
    public void init() {
        checkouts = cr.getCheckoutList();
        availableBooks = br.getAvailableBookList();
    }

    public String addCheckout() {
        // Retrieves book and customer objects based on their selected ID's, and creates
        // a new checkout object
        Book book = br.getBookById(bookId);
        Customer customer = customerResource.getCustomerById(customerId);
        Checkout checkout = new Checkout(book, customer);

        // Creates a new checkout record and updates the list
        cr.createCheckout(checkout);
        checkouts = cr.getCheckoutList();

        // Updates the available flag of the book to false and updates it's entry in the database
        book.setAvailable(false);
        br.updateBook(bookId, book);
        availableBooks = br.getAvailableBookList();

        return "checkout?faces-redirect=true";
    }
}
