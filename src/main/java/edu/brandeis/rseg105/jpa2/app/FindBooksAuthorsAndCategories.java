/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
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
 * @author Louis LeBlanc
 * 
 * Find all books with authors and categories.
 *
 */
public class FindBooksAuthorsAndCategories {
	private static Logger logger = LoggerFactory.getLogger(FindBooksAuthorsAndCategories.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();

		PublishingService publishingService = ctx.getBean(PublishingService.class);

		List<Book> books = publishingService.findAllWithAuthorAndCategory();
		logger.info("================================");
		logger.info("Listing all books with authors and category names: ");
		listBooksWithCategoryAndAuthors(books);
		logger.info("================================");

		ctx.close();
	}
	
	public static void listBooksWithCategoryAndAuthors(List<Book> books) {

		books.forEach(b -> {
			logger.info(b.toString());
			logger.info(b.getCategory().toString());
				b.getAuthors().forEach(a -> logger.info(a.toString()));
				logger.info("-----------------");
		});
	}
}
