/**
 * @author Louis LeBlanc
 * For Homework Assignment 2
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.jpa2.app;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.brandeis.rseg105.jpa2.domain.Author;
import edu.brandeis.rseg105.jpa2.domain.Book;
import edu.brandeis.rseg105.jpa2.service.PublishingService;

/**
 * @author Louis LeBlanc
 *
 * Find a book with authors and category by Book ID.
 */
public class FindBookWithAuthorsCategoryById {
	private static Logger logger = LoggerFactory.getLogger(FindBookWithAuthorsCategoryById.class);

	public static void main(String[] args) {
		Long bookID = 1L;

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();

		PublishingService publishingService = ctx.getBean(PublishingService.class); 

		Book book = publishingService.findBookWithAuthorAndCategoryById(bookID);

		logger.info("================================");
		logger.info("Listing book with authors and category by book's ID: ");
		logger.info(book.toString());
		logger.info(book.getCategory().toString());
		Set<Author> authors = book.getAuthors();
		authors.forEach(author -> { logger.info(author.toString()); });
		logger.info("================================");

		ctx.close();
	}
}
