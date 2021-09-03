package org.sid.testjunit.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sid.dao.BookRepository;
import org.sid.entities.Book;
import org.sid.enumclass.BookType;
import org.sid.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
	@MockBean
	private BookRepository bookRepository;
	@Autowired
	private BookService bookservice; 

	@Test
	public void testCreateBook() {
		// given
		Book book = new Book();
		book.setAuthor("Hamid");
		book.setTitle("succès");
		book.setType(BookType.ROMAN);
		book.setNumberCopies(15);
		book.setNumberBooksAvailable(10);
		 
		// when
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		//then
		Assertions.assertThat(bookservice.createBook(book)).isEqualTo(book);
				
	}
	@Test
	public void testfindAllBooks() {
		// given
		Book book1 = new Book();
		book1.setAuthor("rifai");
		book1.setTitle("progrès");
		book1.setType(BookType.ROMAN);
		book1.setNumberCopies(20);
		book1.setNumberBooksAvailable(15);
		
		Book book2 = new Book();
		book2.setAuthor("rifai");
		book2.setTitle("progrès");
		book2.setType(BookType.ROMAN);
		book2.setNumberCopies(20);
		book2.setNumberBooksAvailable(15);
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(book1);
		bookList.add(book2);
		 
		// when
		Mockito.when(bookRepository.findAll()).thenReturn(bookList);
		//then
		Assertions.assertThat(bookservice.findAllBooks()).isEqualTo(bookList);
		}
		/*
		 * @Test public void testDeleteBook() { // given Book book = new Book();
		 * book.setIdBook(1L); book.setAuthor("Daoui"); book.setTitle("branche");
		 * book.setType(BookType.ROMAN); book.setNumberCopies(50);
		 * book.setNumberBooksAvailable(40);
		 * 
		 * // when Mockito.when(bookRepository.findOne(1L)).thenReturn(book);
		 * Mockito.when(bookRepository.existsById(book.getIdBook())).thenReturn(false);
		 * //then
		 * Assertions.assertThat(bookservice.createBook(book)).isNotEqualTo(book); }
		 * 
		 * @Test public void testUpdateBook() { // given Book book = new Book();
		 * book.setIdBook(1L); book.setAuthor("Daoui Raoui"); book.setTitle("branche");
		 * book.setType(BookType.ROMAN); book.setNumberCopies(50);
		 * book.setNumberBooksAvailable(49);
		 * 
		 * // when Mockito.when(bookRepository.findById(1L)).thenReturn(book);
		 * book.setAuthor("Daoui");
		 * Mockito.when(bookRepository.save(book)).thenReturn(book); //then
		 * Assertions.assertThat(bookservice.updateBook(book)).isEqualTo(book);
		 * 
		 * }
		 */
}

