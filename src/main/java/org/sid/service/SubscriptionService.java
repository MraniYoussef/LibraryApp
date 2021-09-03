package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.dao.MemberRepository;
import org.sid.dao.SubscriptionRepository;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.MemberFunction;
import org.sid.exception.SubscriptionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private SubscriptionService subscriptionService;
	@Autowired
	public SubscriptionService(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository= subscriptionRepository;
	}
	public Subscription createSubscription(Long idMember) {
		
		Member memberToSubscribe = memberRepository.getById(idMember);
		Optional<Subscription> subscriptionMemberToSubscribe = subscriptionService.findMemberSubscriptions(idMember);
		MemberFunction memberFunction = memberToSubscribe.getFunction();
		List<Subscription> listsubscriptions = subscriptionService.findAllSubscriptions();
		System.out.println("ffffffff");
		 double price=0;
		 Subscription sub = new Subscription();
		  		  		System.out.println("here here here" + MemberFunction.STUDENT);

	if(subscriptionMemberToSubscribe.isPresent()) {
		System.out.println("im here inside what can i do?????");
		int periodSubscription = subscriptionService.subscriptionPeriod(subscriptionMemberToSubscribe.get().
				  getDateMembership()); 
		if(periodSubscription <= 1) {
				  if(memberFunction.equals(MemberFunction.STUDENT) ||
						  memberFunction.equals(MemberFunction.WITHOUT )) { 
					  price = 200;
				  System.out.println("le prix est : "+ price +"DH"); 
				  } else if(memberFunction.equals(MemberFunction.EMPLOYEE) ) { 
					  price = 300;
					  System.out.println("le prix est : "+ price +"DH"); }}
				  else if(periodSubscription >1 && periodSubscription <5) {
				  
				  if(memberFunction.equals(MemberFunction.STUDENT ) ||
				  memberFunction.equals(MemberFunction.WITHOUT )) {
					  price = 200 * 50 / 100 ;
					  System.out.println("le prix est : "+ price +"DH");
					  
					  } else if(memberFunction.equals(MemberFunction.EMPLOYEE )) { 
						  price = 300 * 50 / 100 ;
						  System.out.println("le prix est : "+ price +"DH");
						  }} else {
							  price = 0;}
		subscriptionMemberToSubscribe.get().setIsBlocked(false);
		  subscriptionMemberToSubscribe.get().setPrice(price);
		   sub = subscriptionRepository.save(subscriptionMemberToSubscribe.get());
										  }		 

	else if(subscriptionMemberToSubscribe.isPresent() == false) {
		if(memberFunction.equals(MemberFunction.STUDENT) ||
				memberFunction.equals(MemberFunction.WITHOUT ) ) { 
		  		System.out.println("here here here inside student");	

			price = 200;
			System.out.println("le prix est : "+ price +"DH");} 
		else if(memberFunction.equals(MemberFunction.EMPLOYEE) ) { 
		  		System.out.println("here here here inside employee");	

			price =  300; 
			System.out.println("le prix est : "+ price +"DH"); } 
	System.out.println("hnnnnnaaaaaaaaaaaaaaa");
	Subscription subscription = new Subscription();
	subscription.setMember(memberToSubscribe);
	subscription.setBlockageDaysPeriod(0);
	subscription.setDateMembership(new Date());
	subscription.setIsBlocked(false);
	subscription.setPrice(price);
	 sub = subscriptionRepository.save(subscription);
	System.out.println("coooooooooooooooooooooo");	

}System.out.println("lsaaaaaaaaa");
	return sub; 
		

		

}

		
			 
	

	public List<Subscription> findAllSubscriptions(){
		return subscriptionRepository.findAll();
	}
	public Subscription updateSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}
	public Subscription findSubscriptionByIdSubscription(Long id){
		return subscriptionRepository.findSubscriptionByIdSubscription(id)
				.orElseThrow(() -> new SubscriptionNotFoundException("Subscription by id"+id+ " was not found"));
	}
	public Optional<Subscription> findMemberSubscriptions(Long idMember) {
		return subscriptionRepository.findMemberSubscriptions(idMember);
	}
	
	public void deleteSubscription(Long id) {
		subscriptionRepository.deleteSubscriptionByIdSubscription(id);
	}
	public boolean checkBlockageMember(Long idMember) {
		Optional<Subscription> subscription = subscriptionRepository.findMemberSubscriptions(idMember);
		if(subscription.get().getIsBlocked()==true) {
			return true;
		}else 
			return false;
	}
	
	public void blockSubscriptionByIdMember(Long idMember) {
		Optional<Subscription> subscriptionToBlock =subscriptionRepository.findMemberSubscriptions(idMember);
		subscriptionToBlock.get().setIsBlocked(true);
		subscriptionRepository.save(subscriptionToBlock.get());
		
	}
	 public int subscriptionPeriod(Date dateMembership)   
	    {   
	       int  subscriptionPeriod = 0;
	        // In the try block, we will try to find subscriptionPeriod  
	        try {   
	            // Use parse method to get date object of both dates  
	            Date date1 = dateMembership;   
	            Date date2 = new Date(); 
	             
	            // Calucalte time difference in milliseconds   
	            long time_difference = date2.getTime() - date1.getTime();  
	            // Calucalte time difference in days  
	            long days_difference = (time_difference / (1000*60*60*24)) % 365;   
	            // Calucalte time difference in hours  
	            long hours_difference = (time_difference / (1000*60*60)) % 24;   
	            // Show difference in years, in days, hours, minutes, and seconds   
	            System.out.print(   
	                "Difference "  
	                + "between two dates is: ");   
	            System.out.println(   
	                
	                + hours_difference   
	                + " hours"  
	                ); 
	            subscriptionPeriod =  (int) days_difference;
	        }   
	        // Catch parse exception   
	        catch (ParseException excep) {   
	            excep.printStackTrace();   
	        }
	        return  subscriptionPeriod;
	    }
}
