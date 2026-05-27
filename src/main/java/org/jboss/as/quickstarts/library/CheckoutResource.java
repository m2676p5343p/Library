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

    public List<Checkout> getCheckoutList() {
        return em.createQuery(
            "SELECT c FROM Checkout c",
            Checkout.class
        ).getResultList();
    }

    public Checkout getCheckoutById(Long id) throws NotFoundException {
        Checkout checkout = em.find(Checkout.class, id);
        if (checkout == null) {
            throw new NotFoundException("Checkout with id " + id + " not found");
        }

        return checkout;
    }

    @Transactional
    public void createCheckout(@Valid Checkout checkout) {
        em.persist(checkout);
    }

    @Transactional
    public void deleteCheckout(Long id) {
        Checkout checkout = em.find(Checkout.class, id);
        if (checkout == null) {
            throw new NotFoundException("Checkout with id " + id + " not found");
        }
        em.remove(checkout);
    }
}