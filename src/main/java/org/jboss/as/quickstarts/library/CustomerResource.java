package org.jboss.as.quickstarts.library;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@RequestScoped
public class CustomerResource {
    @PersistenceContext
    EntityManager em;

    public List<Customer> getCustomerList() {
        return em.createQuery(
            "SELECT c FROM Customer c",
            Customer.class
        ).getResultList();
    }

    public Customer getCustomerById(Long id) throws NotFoundException {
        Customer customer = em.find(Customer.class, id);
        if (customer == null) {
            throw new NotFoundException("Customer with id " + id + " not found");
        }

        return customer;
    }

    @Transactional
    public void createCustomer(@Valid Customer customer) {
        em.persist(customer);
    }
}