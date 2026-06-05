package org.jboss.as.quickstarts.library;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CustomerBackingBean implements Serializable {
    /**
     * Fields to store user input when adding a new customer
     */
    private String firstName;
    private String lastName;

    // Used to execute queries on the database
    @Inject
    private CustomerResource cr;

    // Stores the list of customers
    private List<Customer> customers;

    /**
     * Getters and setters
     */
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

    public List<Customer> getCustomers() {
        return this.customers;
    }

    /**
     * Runs when the bean is initialized to load customer list
     */
    @PostConstruct
    public void init() {
        customers = cr.getCustomerList();
    }

    /**
     * Adds a new customer to the database
     */
    public String addCustomer() {
        try {
            // Creates the new Customer object
            Customer newCustomer = new Customer(this.firstName, this.lastName);

            // Persists the new Customer in the database
            cr.createCustomer(newCustomer);

            // Refreshes the customer list
            customers = cr.getCustomerList();
            
            // Reloads the page
            return "customers?faces-redirect=true";
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(e.getMessage())
            );
            return null;
        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(e.getMessage())
            );
            return null;
        }
    }
}
