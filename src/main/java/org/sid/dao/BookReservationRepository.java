package org.sid.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
	@Query("select bk from BookReservation bk where bk.member.firstName like :x" )
	public Page<BookReservation> chercher(@Param("x")String bk, Pageable pageable);


	public Optional<BookReservation> findBookReservationByIdBookReservation(Long id);

	@Query("select bk from BookReservation bk where bk.member.idMember like :x" ) 
	public BookReservation findMemberReservations(@Param("x")Long idMember);
	
	public void deleteBookReservationByIdBookReservation(Long id);

	@Query("select bk from BookReservation bk where bk.dateTermination like :x" )
	public List<BookReservation> findBookReservationByDate(@Param("x")Date dateTermination);

	@Query("select count(bk) from BookReservation bk where bk.member.idMember like :x" )
	public Long countMemberReservations(@Param("x")Long idMember);
	
	//@Query("select count(*) from BookReservation bk where bk.member.IdMember like :x" )
	//public int countBookReservationByIdUser(@Param("x")Long id);

}
