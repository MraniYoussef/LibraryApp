package org.sid.testjunit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sid.dao.BookRepository;
import org.sid.dao.BookReservationRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.enumclass.BookReservationStatus;
import org.sid.service.BookReservationService;
import org.sid.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookReservationServiceTest {
	@MockBean
	private BookReservationRepository bookReservationRepository;
	@Autowired
	private BookReservationService bookReservationService; 
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookService bookService; 

	@Test
	public void testCreateBookReservation() {
		// given
		Optional<Member> memberToReserve = memberRepository.findById(15L);
		Optional<Book> bookToReserve = bookRepository.findById(11L);
		BookReservation bookReservation = new BookReservation();
		bookReservation = bookReservationService.createBookReservation(15L,
				11L);
		
					// when
					Mockito.when(bookReservationRepository.save(bookReservation)).thenReturn(bookReservation);
					//then
					Assertions.assertThat(bookReservationService.createBookReservation(4L,11L)).isEqualTo(bookReservation);
					
				}
				/*
				 * @Test public void testReturnBookReservation() { // given
				 * bookReservationService.returnBookReservation(25L); BookReservation bkReserv =
				 * bookReservationService.findMemberReservations(25L);
				 * 
				 * // when Mockito.when(bookReservationService.returnBookReservation(bkReserv.
				 * getIdBookReservation())).thenReturn(bkReserv); //then
				 * Assertions.assertThat(bookReservationRepository.findById(bkReserv.
				 * getIdBookReservation())).isEqualTo(bkReserv);
				 * 
				 * }
				 */
	
	@Test
	public void testfindAllBookReservations() {
		// given
		Optional<Member> memberToReserve1 = memberRepository.findById(4L);
		Optional<Book> bookToReserve1 = bookRepository.findById(11L);
		BookReservation bookReservation1 = new
		BookReservation(memberToReserve1, new Date(), BookReservationStatus.VALIDATED, new Date(), bookToReserve1 );
		
		Optional<Member> memberToReserve2 = memberRepository.findById(4L);
		Optional<Book> bookToReserve2 = bookRepository.findById(11L);
		BookReservation bookReservation2 = new
		BookReservation(memberToReserve2, new Date(), BookReservationStatus.VALIDATED, new Date(), bookToReserve2 );
		
		List<BookReservation> bookReservationList = new ArrayList<>();
		bookReservationList.add(bookReservation1);
		bookReservationList.add(bookReservation2);
		 
		// when
		Mockito.when(bookReservationRepository.findAll()).thenReturn(bookReservationList);
		//then
		Assertions.assertThat(bookReservationService.findAllBookReservations()).isEqualTo(bookReservationList);
		}
		
		/*
		 * @Test public void testDeleteBookReservation() { // given BookReservation
		 * bookReservation = bookReservationRepository.findById(95L).get();
		 * bookReservationRepository.delete(bookReservation);
		 * 
		 * BookReservation bookReservation1 = null; Optional<BookReservation>
		 * optionalBookReservation = bookReservationRepository.findById(95L);
		 * if(optionalBookReservation.isPresent()) { bookReservation1 =
		 * optionalBookReservation.get(); }
		 * 
		 * // when Mockito.when(bookRepository.findOne(1L)).thenReturn(book);
		 * Mockito.when(bookReservationRepository.existsById(bookReservation.
		 * getIdBookReservation())).thenReturn(false); //then
		 * Assertions.assertThat(bookReservationService.
		 * findBookReservationByIdBookReservation(95L)).isNull(); }
		 */
		  
		/* @Test public void testUpdateBook() { // given Book book = new Book();
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

