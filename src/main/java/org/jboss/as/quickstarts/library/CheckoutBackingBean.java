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
    private Long itemId;
    private Long customerId;

    @Inject
    private CheckoutResource cr;
    @Inject
    private ItemResource resource;
    @Inject
    private CustomerResource customerResource;

    private List<Checkout> checkouts;
    private List<LibraryItem> availableItems;

    private Checkout selectedCheckout;

    /**
     * Getters and setters
     */
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public List<LibraryItem> getAvailableItems() {
        return this.availableItems;
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
        availableItems = resource.getAvailableItemList();
    }

    public String addCheckout() {
        // Retrieves item and customer objects based on their selected ID's, and creates
        // a new checkout object
        LibraryItem item = resource.getItemById(itemId);
        Customer customer = customerResource.getCustomerById(customerId);
        Checkout checkout = new Checkout(item, customer);

        // Creates a new checkout record and updates the list
        cr.createCheckout(checkout);
        checkouts = cr.getCheckoutList();

        // Sets the item to unavailable and updates the database
        resource.setUnavailable(
            item.getId()
        );
        availableItems = resource.getAvailableItemList();

        return "checkout?faces-redirect=true";
    }

    public String returnItem() {
        // Sets the item to available and updates the database
        resource.setAvailable(
            selectedCheckout.getItem().getId()
        );
        availableItems = resource.getAvailableItemList();

        // Deletes the checkout record from the database and refreshes the list
        cr.deleteCheckout(selectedCheckout.getId());
        checkouts = cr.getCheckoutList();
        return "checkout?faces-redirect=true";
    }
}
