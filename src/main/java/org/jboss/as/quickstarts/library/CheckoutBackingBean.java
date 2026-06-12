package org.jboss.as.quickstarts.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.NotFoundException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CheckoutBackingBean implements Serializable {
    /**
     * These fields store the ID's of the item and customer that are selected
     * in the drop-down boxes
     */
    private Long itemId;
    private Long customerId;

    /**
     * These resources are used to execute queries on the database
     */
    @Inject
    private CheckoutResource checkoutResource;
    @Inject
    private ItemResource itemResource;
    @Inject
    private CustomerResource customerResource;

    // Stores the existing checkouts
    private List<Checkout> checkouts;

    // Stores all items that are currently available
    private List<LibraryItem> availableItems;

    // Stores all customers who are in good standing (no overdue loans)
    private List<Customer> goodStandingCustomers;

    // Stores the selected checkout for modification (e.g. if the user opts to
    // return an item)
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

    public List<Customer> getGoodStandingCustomers() {
        return this.goodStandingCustomers;
    }

    /**
     * Populates the checkouts and availableItems Lists when the Bean is initialized
     */
    @PostConstruct
    public void init() {
        checkouts = checkoutResource.getCheckoutList();
        availableItems = itemResource.getAvailableItemList();
        goodStandingCustomers = checkoutResource.getGoodStandingCustomers();
    }

    /**
     * Adds a new checkout to the database
     */
    public String addCheckout() {
        if (itemId == null || customerId == null) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage("Need to select both item and customer!")
            );
            return null;
        } else {
            try {
                // Retrieves item and customer objects based on their selected ID's, and creates
                // a new checkout object
                LibraryItem item = itemResource.getItemById(itemId);
                Customer customer = customerResource.getCustomerById(customerId);
                Checkout checkout = new Checkout(item, customer);

                // Creates a new checkout record and updates the list
                checkoutResource.createCheckout(checkout);
                checkouts = checkoutResource.getCheckoutList();

                // Sets the item to unavailable and updates the database
                itemResource.setUnavailable(
                    item.getId()
                );

                // Refreshes the list of available items and reloads the page
                availableItems = itemResource.getAvailableItemList();
                return "checkout?faces-redirect=true";
            } catch (NotFoundException e) {
                FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(e.getMessage())
                );
                return null;
            }
        }
    }

    /**
     * Removes a checkout from the database
     */
    public String returnItem() {
        if (selectedCheckout == null) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage("Selected checkout is null!")
            );
            return null;
        }
        try {
            // Sets the item to available and updates the database
            itemResource.setAvailable(
                selectedCheckout.getItem().getId()
            );
            // Refreshes list of available items
            availableItems = itemResource.getAvailableItemList();

            // Deletes the checkout record from the database and refreshes the list
            checkoutResource.deleteCheckout(selectedCheckout.getId());
            checkouts = checkoutResource.getCheckoutList();

            // Reloads the page
            return "checkout?faces-redirect=true";
        } catch (NotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(e.getMessage())
            );
            return null;
        }
    }
}
