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

    public List<Book> getBookList() {
        return em.createQuery(
            "SELECT b FROM Book b",
            Book.class
        ).getResultList();
    }

    public Book getBookById(Long id) throws NotFoundException {
        Book book = em.find(Book.class, id);
        if (book == null) {
            throw new NotFoundException("Book with id " + id + " not found");
        }

        return book;
    }

    @Transactional
    public void createBook(@Valid Book book) {
        em.persist(book);
    }

    @Transactional
    public void updateBook(Long id, @Valid Book book) throws NotFoundException {
        Book existing = em.find(Book.class, id);
        if (existing == null) {
            throw new NotFoundException("Book with id " + id + " not found");
        }
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setGenre(book.getGenre());
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = em.find(Book.class, id);
        if (book == null) {
            throw new NotFoundException("Book with id " + id + " not found");
        }
        em.remove(book);
    }
}
