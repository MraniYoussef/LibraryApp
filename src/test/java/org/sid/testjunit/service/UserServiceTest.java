package org.sid.testjunit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import org.sid.enumclass.BookType;
import org.sid.service.BookReservationService;
import org.sid.service.BookService;
import org.sid.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@MockBean
	private MemberRepository memberRepository;
	@Autowired
	private MemberService userService; 
	

	@Test
	public void testCreateUser() {
		// given 
		Member member = new Member();
		member.setCin("a111");
		member.setEmail("sss@gmail");
		member.setFirstName("hor");
		member.setLastName("imad");
		member.setPassword(UUID.randomUUID().toString());
		// when
		Mockito.when(memberRepository.save(member)).thenReturn(member);
		//then
		Assertions.assertThat(userService.createUser(member)).isEqualTo(member);
		}
	@Test
	public void testfindAllUsers() {
		// given
		Member member1 = new Member();
		member1.setCin("a1111");
		member1.setEmail("sam@gmail");
		member1.setFirstName("inna");
		member1.setLastName("younes");
		member1.setPassword(UUID.randomUUID().toString());
		
		Member member2 = new Member();
		member2.setCin("b1111");
		member2.setEmail("link@gmail");
		member2.setFirstName("roh");
		member2.setLastName("bahi");
		member2.setPassword(UUID.randomUUID().toString());
		
		List<Member> userList = new ArrayList<>();
		userList.add(member1);
		userList.add(member2);
		 
		// when
		Mockito.when(memberRepository.findAll()).thenReturn(userList);
		//then
		Assertions.assertThat(userService.findAllMembers()).isEqualTo(userList);
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

