package org.sid.dao;

import java.util.Optional;

import org.sid.entities.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("select b from Book b where b.title like :x" )
	public Page<Book> chercher(@Param("x")String b, Pageable pageable);

	
	Optional<Book> findByTitle(String title);


	 void deleteBookByIdBook(Long idBook);


	public Book findBookByTitle(String title);


	 Optional<Book> findBookByIdBook(Long id);


	


	//public Book findOne(long l);

}
