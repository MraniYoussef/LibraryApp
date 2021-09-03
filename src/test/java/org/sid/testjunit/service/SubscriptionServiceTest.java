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
import org.sid.dao.SubscriptionRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookReservationStatus;
import org.sid.enumclass.BookType;
import org.sid.service.BookReservationService;
import org.sid.service.BookService;
import org.sid.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.expression.ParseException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceTest {
	@MockBean
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private SubscriptionService subscriptionService; 
	@Autowired
	private MemberRepository memberRepository;
	

	@Test
	public void testCreateSubscription() {
		// given
		//Member memberToSubscribe = memberRepository.findById(55L).get();
		Subscription subscription = null;
		// when
		Mockito.when(subscriptionService.createSubscription(55L)).thenReturn(subscription);
		//then
		Assertions.assertThat(subscriptionService.createSubscription(subscription.getMember().getIdMember())).isEqualTo(subscription);
		}
	@Test
	public void testfindAllBookReservations() {
		// given
		Optional<Member> memberToSubscribe1 = memberRepository.findById(4L);
		Subscription subscription1 = new
				Subscription(memberToSubscribe1, 2, false );
		
		Optional<Member> memberToSubscribe2 = memberRepository.findById(47L);
		Subscription subscription2 = new Subscription(memberToSubscribe2, 1, false );
		
		List<Subscription> subscriptionList = new ArrayList<>();
		subscriptionList.add(subscription1);
		subscriptionList.add(subscription2);
		 
		// when
		Mockito.when(subscriptionRepository.findAll()).thenReturn(subscriptionList);
		//then
		Assertions.assertThat(subscriptionService.findAllSubscriptions()).isEqualTo(subscriptionList);
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
	// Create function for finding subscription period   
   
}

