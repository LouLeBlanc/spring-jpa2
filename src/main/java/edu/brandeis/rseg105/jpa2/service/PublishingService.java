/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.jpa2.service;

import java.util.List;

import edu.brandeis.rseg105.jpa2.domain.Book;

public interface PublishingService {
	
	List<Book> findAll();   // Named Query
	
	List<Book> findAllWithAuthorAndCategory();   // Named Query
	
	List<Book> findBooksByAuthorId(Long id);

	Book findBookWithAuthorAndCategoryById(Long id);

	List<Book> findAllByNativeQuery();   // Native Query
	
	Book save(Book book);
	
	void delete(Book book);
}
