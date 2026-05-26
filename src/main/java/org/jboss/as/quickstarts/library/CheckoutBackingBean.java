package org.jboss.as.quickstarts.library;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
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

    @PostConstruct
    public void init() {
        checkouts = cr.getCheckoutList();
    }

    public void addCheckout() {
        Book book = br.getBookById(bookId);
        Customer customer = customerResource.getCustomerById(customerId);
        Checkout checkout = new Checkout(book, customer);
        cr.createCheckout(checkout);
        checkouts = cr.getCheckoutList();
    }
}
