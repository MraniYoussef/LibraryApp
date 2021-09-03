package org.sid.service;

import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;

import org.sid.dao.BookRepository;
import org.sid.entities.Book;
import org.sid.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository= bookRepository;
	}
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	public List<Book> findAllBooks(){
		return bookRepository.findAll();
	}
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}
	public Book findBookByTitle(String title) {
		return bookRepository.findBookByTitle(title);
	}
	public Book findBookByIdBook(Long id) {
		return bookRepository.findBookByIdBook(id).orElseThrow(() -> new BookNotFoundException("Book by id"+id+ " was not found"));
	}
	public void deleteBook(Long id) {
		bookRepository.deleteBookByIdBook(id);
	}
	public IntPredicate createBook(Optional<Book> book) {
		// TODO Auto-generated method stub
		return null;
	}
	public IntPredicate updateBook(Optional<Book> book) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void numberBookAvailableMinusOne(Optional<Book> bookToReserve) {
		int numberBookAvailableMinusOne = bookToReserve.get().getNumberBooksAvailable() - 1;
		bookToReserve.get().setNumberBooksAvailable(numberBookAvailableMinusOne);  
		
	}
	
}
