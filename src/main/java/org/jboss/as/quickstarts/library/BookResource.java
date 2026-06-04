package org.jboss.as.quickstarts.library;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@RequestScoped
public class BookResource {
    @PersistenceContext
    EntityManager em;

    public List<LibraryItem> getItemList() {
        return em.createQuery(
            "SELECT b FROM LibraryItem b ORDER BY id",
            LibraryItem.class
        ).getResultList();
    }

    public List<LibraryItem> getAvailableItemList() {
        return em.createQuery(
            "SELECT b FROM LibraryItem b WHERE b.available = TRUE",
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
    public void updateBook(Long id, @Valid Book book) throws NotFoundException {
        Book existing = em.find(Book.class, id);
        if (existing == null) {
            throw new NotFoundException();
        }
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setGenre(book.getGenre());
        existing.setAvailable(book.getAvailable());
    }

    @Transactional
    public void updateDvd(Long id, @Valid Dvd dvd) throws NotFoundException {
        Dvd existing = em.find(Dvd.class, id);
        if (existing == null) {
            throw new NotFoundException();
        }
        existing.setTitle(dvd.getTitle());
        existing.setDuration(dvd.getDuration());
        existing.setGenre(dvd.getGenre());
        existing.setAvailable(dvd.getAvailable());
    }

    @Transactional
    public void deleteItem(Long id) {
        LibraryItem item = em.find(LibraryItem.class, id);
        if (item == null) {
            throw new NotFoundException("Item with id " + id + " not found");
        }
        em.remove(item);
    }
}
