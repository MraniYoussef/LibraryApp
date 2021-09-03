package org.sid.testjunit.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sid.dao.BookRepository;
import org.sid.entities.Book;
import org.sid.enumclass.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)

public class BookRepositoryTest {
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	@Rollback(false)   //pour insérer les données générées par la methode de test
	public void testCreateBook() {
		// given
		Book book = new Book("lam", "iden", BookType.ROMAN, 200, 20);
		// when
		Book saveBook = bookRepository.save(book);
		//then
		assertNotNull(book);
		assertTrue(book.getIdBook()>0);
		//assertThat(saveBook).isNotNull();
		Assertions.assertThat(saveBook).isNotNull();
		Assertions.assertThat(book.getIdBook()).isGreaterThan(0);
				
	}
	
	  @Test 	
	  public void testFindBookByName() {
		  
		  String name = "Illusions";
		  
		  Optional<Book> book = bookRepository.findById(6L);
		  assertEquals(book.get().getTitle(),name);
		  assertTrue(book.get().getIdBook()>0); 
		  

			Assertions.assertThat(book.get().getIdBook()).isEqualByComparingTo(6L);
	  }
	  @Test 		
	  public void getListOfBooksTest() {
		  List<Book> books = bookRepository.findAll();
		    
			Assertions.assertThat(books.size()).isGreaterThan(0);
	  }
	  @Test	
	  	  public void updateBookTest() {
		  Book book = bookRepository.findById(13L).get();
		  book.setTitle("xsxsxsxs");
		  Book bookUpdated = bookRepository.save(book);
		  
			Assertions.assertThat(bookUpdated.getTitle()).isEqualTo("xsxsxsxs");
		
		}
	  
	  @Test
	  public void deleteBookTest() {
		  Book book = bookRepository.findById(48L).get();
			 System.out.println("boooooooooook = "+book.getIdBook());

		 bookRepository.delete(book);
		 System.out.println("boooooooooook = "+book.getIdBook());

		 Book book1 = null;
		 Optional<Book> optionalBook = bookRepository.findById(48L);
		  if(optionalBook.isPresent()) {
			  book1 = optionalBook.get();

		  }

			Assertions.assertThat(book1).isNull();
		
		}
	 
}
