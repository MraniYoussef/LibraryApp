package org.sid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.enumclass.BookReservationStatus;
import org.sid.enumclass.BookType;
import org.sid.service.BookReservationService;
import org.sid.service.BookService;
import org.sid.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/get")
public class BookReservationController {
	private final BookReservationService bookReservationService;
	@Autowired
	MemberService memberService;
	public BookReservationController(BookReservationService bookReservationService) {
		this.bookReservationService = bookReservationService;
	}
	@GetMapping("/bookReservation")
	public ResponseEntity<List<BookReservation>> getAllBookReservations(){
		List<BookReservation> bookReservations = bookReservationService.findAllBookReservations();
		return new ResponseEntity(bookReservations, HttpStatus.OK); 
	}
	@GetMapping("/bookReservation/status")
	public ResponseEntity<List<BookReservationStatus>> getBookReservationStatus(){
		List<BookReservationStatus> statusBook = Arrays.asList(BookReservationStatus.values());
		return new ResponseEntity(statusBook, HttpStatus.OK); 
	}
		@GetMapping("/bookReservation/title/{titleBook}")
		public ResponseEntity<List<BookReservation>> getBookReservationByTitleBook(@PathVariable String titleBook){
			List<BookReservation> bookReservations = bookReservationService.getBookReservationByTitleBook(titleBook);
			return new ResponseEntity(bookReservations, HttpStatus.OK); 
	}
	@GetMapping("/bookReservation/Members")
	public ResponseEntity<List<Member>> getMembers(){
		List<Member> members = memberService.findAllMembers();
		
	    		return new ResponseEntity(members, HttpStatus.OK); 
	}
	
	/*
	 * @GetMapping("/bookReservation/{id}") public ResponseEntity<BookReservation>
	 * getBookReservationByIdBookReservation (@PathVariable("id") Long id){ List
	 * <BookReservation> bookReservation =
	 * bookReservationService.findBookReservationByIdBookReservation(id); return new
	 * ResponseEntity(bookReservation, HttpStatus.OK); }
	 */
	
	@PostMapping("/bookReservation/{idMember}/{idBook}")
	public ResponseEntity<BookReservation> addBookReservation
	(@PathVariable Long idMember, @PathVariable Long idBook){
		BookReservation newBookReservation = bookReservationService
				.createBookReservation(idMember, idBook);
		return new ResponseEntity(newBookReservation, HttpStatus.CREATED); 
	}
	@PutMapping("/bookReservation/{idBookReservation}")
	public ResponseEntity<BookReservation> updateBookReservation
	(@PathVariable Long idBookResevation, @RequestBody BookReservation bookReservation){
		BookReservation updateBookReservation = bookReservationService
				.updateBookReservation(bookReservation);
		return new ResponseEntity(updateBookReservation, HttpStatus.OK); 
	}
	@DeleteMapping("/bookReservation/{idBookReservation}")
	public ResponseEntity<?> deleteBookReservation
	(@PathVariable("idBookReservation") Long id){
		bookReservationService.deleteBookReservation(id);
		return new ResponseEntity(HttpStatus.OK); 
	}

}
