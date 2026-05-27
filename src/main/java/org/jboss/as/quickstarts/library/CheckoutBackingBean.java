package org.jboss.as.quickstarts.library;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
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

    private Checkout selectedCheckout;

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

    public Checkout getSelectedCheckout() {
        return selectedCheckout;
    }

    public void setSelectedCheckout(Checkout selectedCheckout) {
        this.selectedCheckout = selectedCheckout;
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

    public String returnBook() {
        // Sets the book to available and updates the database
        Book returningBook = selectedCheckout.getBook();
        returningBook.setAvailable(true);
        br.updateBook(returningBook.getId(), returningBook);

        // Deletes the checkout record from the database and refreshes the list
        cr.deleteCheckout(selectedCheckout.getId());
        checkouts = cr.getCheckoutList();
        return "checkout?faces-redirect=true";
    }
}
