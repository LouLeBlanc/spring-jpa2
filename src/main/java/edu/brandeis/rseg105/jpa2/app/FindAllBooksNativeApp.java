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

import edu.brandeis.rseg105.jpa2.service.PublishingService;
import edu.brandeis.rseg105.jpa2.domain.Book;

/**
 * @author Louis LeBlanc
 *
 *	Find all books - not authors or categories.
 */
public class FindAllBooksNativeApp {
	private static Logger logger = LoggerFactory.getLogger(FindAllBooksNativeApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();

		PublishingService publishingService = ctx.getBean(PublishingService.class); 

		List<Book> books = publishingService.findAll();

		logger.info("================================");
		logger.info("Listing books found by Native Query: ");
		FindAllBooksApp.listBooks(books);
		logger.info("================================");

		ctx.close();
	}
}
