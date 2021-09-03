package org.sid.controller;

import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.service.BookReservationService;
import org.sid.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookReservation")
public class BookReservationController {
	private final BookReservationService bookReservationService;
	
	public BookReservationController(BookReservationService bookReservationService) {
		this.bookReservationService = bookReservationService;
	}
	@GetMapping("/all")
	public ResponseEntity<List<BookReservation>> getAllBookReservations(){
		List<BookReservation> bookReservations = bookReservationService.findAllBookReservations();
		return new ResponseEntity(bookReservations, HttpStatus.OK); 
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<BookReservation> getBookReservationByIdBookReservation
	(@PathVariable("id") Long id){
		BookReservation bookReservation = bookReservationService
				.findBookReservationByIdBookReservation(id);
		return new ResponseEntity(bookReservation, HttpStatus.OK); 
	}
	
	@PostMapping("/add")
	public ResponseEntity<BookReservation> addBookReservation
	(@RequestBody Long idMember, Long idBook){
		BookReservation newBookReservation = bookReservationService
				.createBookReservation(idMember, idBook);
		return new ResponseEntity(newBookReservation, HttpStatus.CREATED); 
	}
	@PutMapping("/update")
	public ResponseEntity<BookReservation> updateBookReservation
	(@RequestBody BookReservation bookReservation){
		BookReservation updateBookReservation = bookReservationService
				.updateBookReservation(bookReservation);
		return new ResponseEntity(updateBookReservation, HttpStatus.OK); 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBookReservation
	(@PathVariable("id") Long id){
		bookReservationService.deleteBookReservation(id);
		return new ResponseEntity(HttpStatus.OK); 
	}

}
