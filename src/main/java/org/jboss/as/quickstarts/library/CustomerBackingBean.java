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
public class CustomerBackingBean implements Serializable {
    /**
     * Fields to store user input when adding a new customer
     */
    private String firstName;
    private String lastName;

    @Inject
    private CustomerResource cr;
    private List<Customer> customers;

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

    @PostConstruct
    public void init() {
        customers = cr.getCustomerList();
    }

    public String addCustomer() {
        Customer newCustomer = new Customer(this.firstName, this.lastName);
        cr.createCustomer(newCustomer);
        customers = cr.getCustomerList();
        return "customers?faces-redirect=true";
    }
}
