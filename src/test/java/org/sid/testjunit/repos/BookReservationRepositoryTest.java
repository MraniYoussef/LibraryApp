package org.sid.testjunit.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sid.dao.BookRepository;
import org.sid.dao.BookReservationRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.enumclass.BookReservationStatus;
import org.sid.enumclass.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class BookReservationRepositoryTest {

	@Autowired
	private BookReservationRepository bookReservationRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	@Rollback(false)   //pour insérer les données générées par la methode de test
	public void testCreateBookReservation() {

		BookReservationStatus bookResStatus=null;
		// given

		  Optional<Member> memberToReserve = memberRepository.findById(4L);
		  Optional<Book> bookToReserve = bookRepository.findById(11L);
		  BookReservation bookReservation = new
				  BookReservation(memberToReserve, new Date(), bookResStatus.VALIDATED, new Date(), bookToReserve );
		// when
		BookReservation saveBookReservation = bookReservationRepository.save(bookReservation);
		//then
		assertNotNull(bookReservation);
		assertTrue(bookReservation.getIdBookReservation()>0);
		//assertThat(saveBook).isNotNull();
		Assertions.assertThat(saveBookReservation).isNotNull();
		Assertions.assertThat(bookReservation.getIdBookReservation()).isGreaterThan(0);
				
	}
	
	  @Test 	
	  public void testFindBookReservationById() {
		  
		  Long id = 7L;
		  Optional<BookReservation> bookReservation = bookReservationRepository.findById(7L);
		  assertEquals(bookReservation.get().getIdBookReservation(),id);
		  assertTrue(bookReservation.get().getIdBookReservation()>0); 
		  

			Assertions.assertThat(bookReservation.get().getIdBookReservation()).isEqualByComparingTo(7L);
	  }
	  @Test 		
	  public void getListOfBookReservationsTest() {
		  List<BookReservation> bookReservations = bookReservationRepository.findAll();
		    
			Assertions.assertThat(bookReservations.size()).isGreaterThan(0);
	  }
	  @Test	
	  	  public void updateBookReservationTest() {
		  //given
		  Book bookToUpdate = bookRepository.findById(28L).get();

		  BookReservation bookReservation = bookReservationRepository.findById(12L).get();
		  bookReservation.setBook(bookToUpdate);;
		  BookReservation bookReservationUpdated = bookReservationRepository.save(bookReservation);
		  
			Assertions.assertThat(bookReservationUpdated.getBook()).isEqualTo(bookToUpdate);
		}
	  
	    
	  
	  @Test
	  public void deleteBookReservationTest() {
		  BookReservation bookReservation = bookReservationRepository.findById(17L).get();
		 bookReservationRepository.delete(bookReservation);
		 
		 BookReservation bookReservation1 = null;
		 Optional<BookReservation> optionalBookReservation = bookReservationRepository.findById(17L);
		  if(optionalBookReservation.isPresent()) {
			  bookReservation1 = optionalBookReservation.get();
		  }
			Assertions.assertThat(bookReservation1).isNull();
		
		}
}
