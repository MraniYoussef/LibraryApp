package org.sid.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.dao.BookRepository;
import org.sid.dao.BookReservationRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookReservationStatus;
import org.sid.exception.BookReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
public class BookReservationService {
	@Autowired
	private  BookService bookService;
	@Autowired
	private SubscriptionService subscriptionService;
	
	private final BookReservationRepository bookReservationRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	public BookReservationService(BookReservationRepository bookReservationRepository) {
		//Book bookToReserve = this.retirerBook(idBook);
		
		this.bookReservationRepository= bookReservationRepository;
	}
	public BookReservation createBookReservation( Long idMember, Long idBook) {
		Optional<Member> memberToReserve = memberRepository.findById(idMember);
		Book bookToReserve = bookService.findBookByIdBook(idBook);
		Optional<Subscription> subscription = subscriptionService.findMemberSubscriptions(idMember);
		Date dateTermination = add21DaysToCurrentDate();
		if((bookToReserve.getNumberBooksAvailable()>0)
				&& (this.getCountMemberReservations(idMember)<4)
				&&(memberToReserve.isPresent()) 
				&&(subscriptionService.checkBlockageMember(idMember)== false) 
				) 
		{
			BookReservation bookReservation = new BookReservation();
			bookReservation.setBook(bookToReserve);
			bookReservation.setDateReservation(new Date());
			bookReservation.setDateTermination(dateTermination);
			bookReservation.setMember(memberToReserve.get());
			bookReservation.setStatus(BookReservationStatus.VALIDATED);
			//mettre à jour le nombre de book available
			bookToReserve.setNumberBooksAvailable(bookToReserve.getNumberBooksAvailable()-1);
			bookService.updateBook(bookToReserve);
		return bookReservationRepository.save(bookReservation);
	         }
		else {System.out.println("Résérvation incomplète, sa situation est Livres restés : "+bookToReserve.getNumberBooksAvailable()+
		"  son nom est : "+ memberToReserve.get().getFirstName()+ " son bloquage abonnement est :"+ subscription.get().getIsBlocked()
		+ "nombre reservation sont : "+" "+ this.getCountMemberReservations(idMember) );
		return null;
		}
		
	}
	public BookReservation returnBookReservation(Long idBookReservation) {
		BookReservation bookreservationToReturn = 
				bookReservationRepository.getById(idBookReservation);
				Long idMember = bookreservationToReturn.getMember().getIdMember();
				Long idBook = bookreservationToReturn.getBook().getIdBook();
		Optional<Member> memberToReturnBook = memberRepository.findById(idMember);
		Book bookToReturn = bookService.findBookByIdBook(idBook);
		Optional<Subscription> subscriptionToBlock = subscriptionService.findMemberSubscriptions(idMember);
		Date dateReturn = new Date();
		//mettre à jour le nombre de book available
		bookToReturn.setNumberBooksAvailable(bookToReturn.getNumberBooksAvailable()+1);
		bookService.createBook(bookToReturn);
		
		bookreservationToReturn.setReturnDate(dateReturn);
		bookReservationRepository.save(bookreservationToReturn);
		//si il a fait un retard il sera bloqué
		if((bookreservationToReturn.getDateTermination().compareTo(dateReturn)> 0) ) {
		      subscriptionService.blockSubscriptionByIdMember(memberToReturnBook.get().getIdMember());
		      
		      int difference = differenceLateReturn(bookreservationToReturn.getDateTermination(), dateReturn);
		      subscriptionToBlock.get().setBlockageDaysPeriod(difference);
		      subscriptionService.updateSubscription(subscriptionToBlock.get());
	    }
		return bookReservationRepository.save(bookreservationToReturn);
		
	}
	public List<BookReservation> findAllBookReservations(){
		return bookReservationRepository.findAll();
	}
	public BookReservation findMemberReservations(Long idMember) {
		return bookReservationRepository.findMemberReservations(idMember);
	}
	public Long getCountMemberReservations(Long idMember) {
		return  bookReservationRepository.countMemberReservations(idMember);
	}
	public BookReservation updateBookReservation(BookReservation bookReservation) {
		return bookReservationRepository.save(bookReservation);
	}
	
	public BookReservation findBookReservationByIdBookReservation(Long id) {
		return bookReservationRepository.findBookReservationByIdBookReservation(id)
				.orElseThrow(() -> new BookReservationNotFoundException("BookReservation by id"+id+ " was not found"));
	}
	
	public void deleteBookReservation(Long id) {
		bookReservationRepository.deleteBookReservationByIdBookReservation(id);
	}
	public BookReservationStatus createBookReservation() {
		return BookReservationStatus.CREATED;
	}
	public BookReservationStatus validateBookReservation() {
		return BookReservationStatus.VALIDATED;
	}
	public BookReservationStatus cancelBookReservation() {
		return BookReservationStatus.CANCELED;
	}
	public BookReservationStatus TerminateBookReservation() {
		return BookReservationStatus.VALIDATED;
	}
	public Date add21DaysToCurrentDate() {
		Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate); 
        // add 21 days to current day
        cal.add(Calendar.DAY_OF_MONTH, 21);
 
        Date datePlus21 = cal.getTime();
 
		return datePlus21;
	}
	
	public void blockLateMembers(Date  date) {
		List<BookReservation> listLateBookReservation = bookReservationRepository.findBookReservationByDate(date);
		for (BookReservation liste : listLateBookReservation) {
			Date dateNow = new Date();
			//si le délai est dépassé
			if((liste.getDateTermination().compareTo(dateNow)< 0) && (liste.getReturnDate()==null)) {
				Long idMember = liste.getMember().getIdMember();
				System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

			      subscriptionService.blockSubscriptionByIdMember(idMember);
		    }
			if((liste.getDateTermination().compareTo(liste.getReturnDate())< 0) ) {
				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			       subscriptionService.blockSubscriptionByIdMember(liste.getMember().getIdMember());
			      Optional<Subscription> subscriptionToBlock = subscriptionService.findMemberSubscriptions(liste.getMember().getIdMember());
			      int difference = differenceLateReturn(liste.getDateTermination(), liste.getReturnDate());
			      subscriptionToBlock.get().setBlockageDaysPeriod(difference);
			      subscriptionService.updateSubscription(subscriptionToBlock.get());
			      //pour débloquer les subscriptions
			      addDiffenrenceDaysToUnblockDate(liste);
		    }
	}
	
	//public int countBooksReservationByUser(Long id) {
	//	return bookReservationRepository.countBookReservationByIdUser(id);
	//}
	/*public Book retirerBook(Long idBook) {
		Book book = consulterBook(idBook).get();

		if(book.getNumberBooksAvailable()>0)
			book.setNumberBooksAvailable(book.getNumberBooksAvailable()-1);
		bookRepository.save(book);
		 if(book.getNumberBooksAvailable()<=0)
			throw new RuntimeException("Ce livre n'est pas disponible");
		return book;
			}
	public Optional<Book> consulterBook(Long idBook) {
		Optional<Book> book =bookRepository.findById(idBook);
		return book;	}*/

	}
	public void addDiffenrenceDaysToUnblockDate(BookReservation bookReservation ) {
	      Optional<Subscription> subscriptionToUnblock = subscriptionService
	    		  .findMemberSubscriptions(bookReservation.getMember().getIdMember());
	      int difference = differenceLateReturn(bookReservation
	    		  .getDateTermination(), bookReservation.getReturnDate());
		Date returnDate = bookReservation.getReturnDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(returnDate); 
        // add difference days to current day
        cal.add(Calendar.DAY_OF_MONTH, difference);
 
        Date unBlockDateSubscription = cal.getTime();
        subscriptionToUnblock.get().setUnblockDate(unBlockDateSubscription);
        Date now = new Date();
        if((now.compareTo(unBlockDateSubscription)>= 0) ){
        	subscriptionToUnblock.get().setIsBlocked(false);
        }
        subscriptionService.updateSubscription(subscriptionToUnblock.get());
        
	}
	// Create function for finding difference   
    public int differenceLateReturn(Date datetermination, Date dateRturn)   
    {   
       int  difference = 0;
        // In the try block, we will try to find the difference  
        try {   
            // Use parse method to get date object of both dates  
            Date date1 = datetermination;   
            Date date2 = dateRturn;   
            // Calucalte time difference in milliseconds   
            long time_difference = date2.getTime() - date1.getTime();  
            // Calucalte time difference in days  
            long days_difference = (time_difference / (1000*60*60*24)) % 365;   
             
            // Show difference in years, in days, hours, minutes, and seconds   
            System.out.print(   
                "Difference "  
                + "between two dates is: ");   
            System.out.println(   
                
                + days_difference   
                + " days"  
                ); 
           difference =  (int) days_difference;
        }   
        // Catch parse exception   
        catch (ParseException excep) {   
            excep.printStackTrace();   
        }
        return  difference;
    }   
}


