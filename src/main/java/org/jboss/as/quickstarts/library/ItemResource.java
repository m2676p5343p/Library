package org.jboss.as.quickstarts.library;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@RequestScoped
public class ItemResource {
    @PersistenceContext
    EntityManager em;

    /**
     * Returns a list of all library items
     */
    public List<LibraryItem> getItemList() {
        return em.createQuery(
            "SELECT i FROM LibraryItem i ORDER BY id",
            LibraryItem.class
        ).getResultList();
    }

    /**
     * Returns a list of all available library items
     */
    public List<LibraryItem> getAvailableItemList() {
        return em.createQuery(
            "SELECT i FROM LibraryItem i WHERE available = TRUE ORDER BY id",
            LibraryItem.class
        ).getResultList();
    }

    /**
     * Returns a single library item with the specified ID
     */
    public LibraryItem getItemById(Long id) throws NotFoundException {
        LibraryItem item = em.find(LibraryItem.class, id);
        if (item == null) {
            throw new NotFoundException("Item with id " + id + " not found");
        }
        return item;
    }

    /** 
     * Persists a new library item in the database
     */
    @Transactional
    public void createItem(@Valid LibraryItem item) {
        em.persist(item);
    }

    /** 
     * Sets the available property of a library item with the specified ID
     * to false
     */
    @Transactional
    public void setUnavailable(Long id) throws NotFoundException {
        LibraryItem item = em.find(LibraryItem.class, id);
        if (item == null) {
            throw new NotFoundException("Item with id " + id + " not found");
        }

        item.setAvailable(false);
        em.persist(item);
    }

    /** 
     * Sets the available property of a library item with the specified ID
     * to true
     */
    @Transactional
    public void setAvailable(Long id) throws NotFoundException {
        LibraryItem item = em.find(LibraryItem.class, id);
        if (item == null) {
            throw new NotFoundException("Item with id " + id + " not found");
        }

        item.setAvailable(true);
        em.persist(item);
    }
}
