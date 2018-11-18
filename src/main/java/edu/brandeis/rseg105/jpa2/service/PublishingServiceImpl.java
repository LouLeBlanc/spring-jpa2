/**
 * @author Louis LeBlanc
 * For Homework Assignment 2
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */
package edu.brandeis.rseg105.jpa2.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.brandeis.rseg105.jpa2.domain.Book;

@Service("jpaPublishingService")
@SuppressWarnings("unchecked")
@Transactional
@Repository
public class PublishingServiceImpl implements PublishingService {
	final static String ALL_BOOKS_NATIVE_QUERY =
			"select id, category_id, isbn, title, price, version from book";

	private static Logger logger = LoggerFactory.getLogger(PublishingServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return em.createNamedQuery(Book.FIND_ALL_BOOKS, Book.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAllWithAuthorAndCategory() {
		return em.createNamedQuery(Book.FIND_ALL_WITH_AUTHOR_CATEGORY, Book.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findBookWithAuthorAndCategoryById(Long id) {
		TypedQuery<Book> query = em.createNamedQuery(Book.FIND_BOOK_WITH_AUTHOR_CATEGORY_BY_ID, Book.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Book> findAllByNativeQuery() {
		return em.createNativeQuery(ALL_BOOKS_NATIVE_QUERY, "bookResult").getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findBooksByAuthorId(Long id) {
		TypedQuery<Book> query = em.createNamedQuery(Book.FIND_BOOKS_BY_AUTHOR_ID, Book.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public Book save(Book book) {
		if (book.getId() == null) {
			logger.info("Inserting new book");
			em.persist(book);
		} else {
			em.merge(book);
			logger.info("Updating existing book");
		}
		logger.info("Book saved with id: " + book.getId());
		return book;
	}

	@Override
	public void delete(Book book) {
		Book mergedBook = em.merge(book);
		em.remove(mergedBook);
		logger.info("Book deleted with id: " + book.getId());
	}
}
