/**
 * @author Louis LeBlanc
 * For Homework Assignment 2
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.jpa2.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.brandeis.rseg105.jpa2.domain.Book;
import edu.brandeis.rseg105.jpa2.service.PublishingService;

/**
 * 
 * @author Louis LeBlanc
 *
 * Find all Books and Categories by Author ID.
 */
public class FindBooksByAuthorId {
	private static Logger logger = LoggerFactory.getLogger(FindBooksByAuthorId.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Long authorId = 7L;

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();

		PublishingService publishingService = ctx.getBean(PublishingService.class); 

		List<Book> books = publishingService.findBooksByAuthorId(authorId);

		logger.info("================================");
		logger.info("Listing books by author id " + authorId + ": ");
		FindBooksAuthorsAndCategories.listBooksWithCategoryAndAuthors(books);
		logger.info("================================");
		
		ctx.close();
	}
}
