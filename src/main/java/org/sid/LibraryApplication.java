package org.sid;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.dao.BookRepository;
import org.sid.dao.BookReservationRepository;
import org.sid.dao.EmployeeRepository;
import org.sid.dao.SubscriptionRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.MemberFunction;
import org.sid.service.BookReservationService;
import org.sid.service.MemberService;
import org.sid.service.SubscriptionService;
import org.sid.dao.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication

public class LibraryApplication implements CommandLineRunner {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepsitory;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookReservationRepository bookReservationRepository;
	@Autowired
	BookReservationService bookReservationService;
	@Autowired
	SubscriptionService subscriptionService; 
	

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
	
	  @Bean 
	  public CorsFilter corsFilter() { 
		  CorsConfiguration corsConfiguration = new CorsConfiguration(); 
		  corsConfiguration.setAllowCredentials(true);
	  corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	  corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	  
	  corsConfiguration.setAllowedHeaders(Arrays.asList("Origin",
	  "Access-Control-Allow-Origin", "Content-Type", "Accept", "Authorization",
	  "Origin, Accept", "X-Request-With", "Access-Control-Request-Method",
	  "Access-Control-Request-Headers"));
	  corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type",
	  "Accept", "Authorization", "Access-Control-Allow-Origin",
	  "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
	  corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
	  "DELETE", "OPTIONS"));
	  
	  UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new
	  UrlBasedCorsConfigurationSource();
	  urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",
	  corsConfiguration);
	  
	  return new CorsFilter(urlBasedCorsConfigurationSource); }
	 

	@Override
	public void run(String... args) throws Exception  {
		
		subscriptionService.createSubscription(55L);
		  List<BookReservation> listBookReservationToBlock =
		  bookReservationService.findAllBookReservations(); 
			
			  for (BookReservation list : listBookReservationToBlock) { 
				  Date dateNow = new Date(); 
				  //si le délai  est dépassé
			  if(list.getDateTermination().compareTo(dateNow)< 0) { Long
			  idMember = list.getMember().getIdMember();
			  bookReservationService.blockLateMembers(dateNow); } 

			  }
			 
		
		
		//Subscription subToBlock = subscriptionService.blockSubscriptionByIdMember(9L);
		//Subscription subscription = subscriptionRepository.findMemberSubscriptions(9L);
		//System.out.println("count"+ subscription.getMember().getLastName() );
		/*
		 * Optional<Member> memberToReserve = memberRepository.findById(9L);
		 * Optional<Book> bookToReserve = bookRepository.findById(11L); BookReservation
		 * bookReservation = new BookReservation();
		 * bookReservationService.createBookReservation(bookReservation,
		 * memberToReserve.get().getIdMember(), bookToReserve.get().getIdBook() );
		 */
		/*
		 * MemberFunction mf1 = null; BookType bt1 = null; BookReservationStatus
		 * brs1=null; Book book = bookRepository.save(new Book("aut1", "Illusions",
		 * bt1.ROMAN, 200, 20)); Subscription sb1=null; Employee A1=
		 * employeeRepository.save(new Employee("Abdellah", "Abdellah", "1234", "A1",
		 * "Abdellah@gmail.com")); Member memeber= memberRepository .save(new Member(
		 * "ahmed","ah","11","a5","a@g","56",new Date(),mf1,sb1 ));
		 * 
		 * 
		 * sb1 = subscriptionRepository.save(new Subscription(memeber,1,false));
		 * BookReservation bookReservation= new BookReservation(memeber, new Date(),
		 * brs1.VALIDATED, new Date(), book );
		 * bookReservationRepository.save(bookReservation);
		 */
	 
		//System.out.println("nbr bookRes"+bookReservationRepository.countBookReservationByIdUser(4L));
	}

}
