package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.sid.enumclass.BookReservationStatus;
@Entity
public class BookReservation implements Serializable {
	@Id @GeneratedValue
	private Long idBookReservation;
	@ManyToOne
	@JoinColumn(name="id_Member")
	private Member member;
	private Date dateReservation;
	private BookReservationStatus status;
	private Date dateTermination;
	private Date returnDate= null;
	@ManyToOne
	@JoinColumn(name="Id_Book")	
	private Book book;
	
	public BookReservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public BookReservation(Member member, Date dateReservation, BookReservationStatus status,
			Date dateTermination, Book book) {
		super();
		this.member = member;
		this.dateReservation = dateReservation;
		this.status = status;
		this.dateTermination = dateTermination;
		this.book = book;
	}

	public BookReservation(Optional<Member> userToReserve, Date currentDate, BookReservationStatus created,
			Book bookToReserve) {
		// TODO Auto-generated constructor stub
	}

	public BookReservation(Optional<Member> memberToReserve, Date date, BookReservationStatus validated, Date date2,
			Optional<Book> bookToReserve) {
		// TODO Auto-generated constructor stub
	}
	public Long getIdBookReservation() {
		return idBookReservation;
	}
	public void setIdBookReservation(Long idBookReservation) {
		this.idBookReservation = idBookReservation;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Date getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}
	public BookReservationStatus getStatus() {
		return status;
	}
	public void setStatus(BookReservationStatus status) {
		this.status = status;
	}
	public Date getDateTermination() {
		return dateTermination;
	}
	public void setDateTermination(Date dateTermination) {
		this.dateTermination = dateTermination;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}
