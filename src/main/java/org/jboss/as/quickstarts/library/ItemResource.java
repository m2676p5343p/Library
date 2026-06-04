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

    public List<LibraryItem> getItemList() {
        /* return em.createQuery(
            "SELECT i FROM LibraryItem i ORDER BY id",
            LibraryItem.class
        ).getResultList(); 

        List<Book> books = em.createQuery(
            "SELECT b.title, b.author, b.genre, b.available " +
            "FROM Book b ",
            Book.class
        ).getResultList();

        List<LibraryItem> items = new ArrayList<LibraryItem>();
        for (Book book : books) {
            items.add((LibraryItem)book);
        }
        return items; */

        return em.createQuery(
            "SELECT i FROM LibraryItem i",
            LibraryItem.class
        ).getResultList();
    }

    public List<LibraryItem> getAvailableItemList() {
        return em.createQuery(
            "SELECT li.id, li.title, b.author, li.genre, li.available " +
            "FROM library_items li " +
            "LEFT JOIN books b ON b.id = li.id " +
            "WHERE li.available = TRUE",
            LibraryItem.class
        ).getResultList();
    }

    public LibraryItem getItemById(Long id) throws NotFoundException {
        LibraryItem item = em.find(LibraryItem.class, id);
        if (item == null) {
            throw new NotFoundException("Item with id " + id + " not found");
        }
        return item;
    }

    @Transactional
    public void createItem(@Valid LibraryItem item) {
        em.persist(item);
    }

    @Transactional
    public void updateItem(Long id, @Valid LibraryItem item) throws NotFoundException {
        LibraryItem existing = em.find(LibraryItem.class, id);
        if (existing == null) {
            throw new NotFoundException("Item with id " + id + " not found");
        }

        existing.setTitle(item.getTitle());
        existing.setGenre(item.getGenre());
        em.persist(existing);
    }
}
