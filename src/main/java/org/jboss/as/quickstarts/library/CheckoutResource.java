package org.jboss.as.quickstarts.library;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@RequestScoped
public class CheckoutResource {
    @PersistenceContext
    EntityManager em;

    /**
     * Returns all checkouts
     */
    public List<Checkout> getCheckoutList() {
        return em.createQuery(
            "SELECT c FROM Checkout c",
            Checkout.class
        ).getResultList();
    }

    /**
     * Returns a single checkout by it's id
     */
    public Checkout getCheckoutById(Long id) throws NotFoundException {
        Checkout checkout = em.find(Checkout.class, id);
        if (checkout == null) {
            throw new NotFoundException("Checkout with id " + id + " not found");
        }
        return checkout;
    }

    /**
     * Adds a new checkout object to the database
     */
    @Transactional
    public void createCheckout(@Valid Checkout checkout) {
        em.persist(checkout);
    }

    /**
     * Deletes a specified checkout from the database
     */
    @Transactional
    public void deleteCheckout(Long id) {
        Checkout checkout = em.find(Checkout.class, id);
        if (checkout == null) {
            throw new NotFoundException("Checkout with id " + id + " not found");
        }
        em.remove(checkout);
    }
}