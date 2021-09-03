package org.sid.testjunit.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sid.dao.BookRepository;
import org.sid.dao.BookReservationRepository;
import org.sid.dao.SubscriptionRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SubscriptionRepositoryTest {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	@Rollback(false)   //pour insérer les données générées par la methode de test
	public void testCreateSubscription() {

		BookReservationStatus bookResStatus=null;
		// given

		  Optional<Member> memberToSubscribe = memberRepository.findById(4L);
		  Subscription subscription = new Subscription(memberToSubscribe, null, false );
		// when
		Subscription saveSubscription = subscriptionRepository.save(subscription);
		//then
		assertNotNull(subscription);
		assertTrue(subscription.getIdSubscription()>0);
		//assertThat(saveBook).isNotNull();
		Assertions.assertThat(saveSubscription).isNotNull();
		Assertions.assertThat(subscription.getIdSubscription()).isGreaterThan(0);
				
	}
	
	  @Test 	
	  public void testFindSubscriptionById() {
		  
		  Long id = 5L;
		  
		  Optional<Subscription> subscription = subscriptionRepository.findById(id);
		  assertEquals(subscription.get().getIdSubscription(),id);
		  assertTrue(subscription.get().getIdSubscription()>0); 
		  

			Assertions.assertThat(subscription.get().getIdSubscription()).isEqualByComparingTo(5L);
	  }
	  @Test 		
	  public void getListOfSubscriptionTest() {
		  List<Subscription> subscription = subscriptionRepository.findAll();
		    
			Assertions.assertThat(subscription.size()).isGreaterThan(0);
	  }
	  	  @Test	
	  	  public void updateSubscriptionTest() {
		  //given
		  Subscription subscriptionToUpdate = subscriptionRepository.findById(21L).get();
		  subscriptionToUpdate.setBlockageDaysPeriod(5);

		  
		  Subscription subscriptionUpdated = subscriptionRepository.save(subscriptionToUpdate);
		  
			Assertions.assertThat(subscriptionUpdated.getBlockageDaysPeriod()).isEqualTo(5);
		}
	  
	    
	  
	  @Test
	  public void deleteSubscriptionTest() {
		  Subscription subscription = subscriptionRepository.findById(21L).get();
		  subscriptionRepository.delete(subscription);
		 
		  Subscription subscription1 = null;
		 Optional<Subscription> optionalSubscription = subscriptionRepository.findById(21L);
		  if(optionalSubscription.isPresent()) {
			  subscription1 = optionalSubscription.get();
		  }
			Assertions.assertThat(subscription1).isNull();
		
		}
}
