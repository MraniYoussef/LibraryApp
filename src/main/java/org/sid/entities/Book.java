package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.sid.enumclass.BookType;

@Entity
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long idBook;
	@NotNull
	@Size(min=2,max=20)
	private String author;
	
	private String title;
	
	private BookType type;
	
	private int numberCopies;
	
	private int numberBooksAvailable;
	//@OneToMany(cascade= CascadeType.REMOVE, orphanRemoval = true)
	//@JoinColumn(name="Id_BookRes")
	//private Collection<BookReservation> bookReservations;
	
	public Book(String author, String title, BookType type, int numberCopies, int numberBooksAvailable) {
		super();
		this.author = author;
		this.title = title;
		this.type = type;
		this.numberCopies = numberCopies;
		this.numberBooksAvailable = numberBooksAvailable;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdBook() {
		return idBook;
	}
	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BookType getType() {
		return type;
	}
	public void setType(BookType type) {
		
		this.type = type;
	}
	public int getNumberCopies() {
		return numberCopies;
	}
	public void setNumberCopies(int numberCopies) {
		this.numberCopies = numberCopies;
	}
	public int getNumberBooksAvailable() {
		return numberBooksAvailable;
	}
	public void setNumberBooksAvailable(int numberBooksAvailable) {
		this.numberBooksAvailable = numberBooksAvailable;
	}
	@Override
	public String toString() {
		return "Book [idBook=" + idBook + ", author=" + author + ", title=" + title + ", type=" + type
				+ ", numberCopies=" + numberCopies + ", numberBooksAvailable=" + numberBooksAvailable
				+ "]";
	}
	

}
