package org.sid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookType;
import org.sid.service.BookService;
import org.sid.service.MemberService;
import org.sid.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/get")
public class SubscriptionController {
	private final SubscriptionService subscriptionService;
	@Autowired
	MemberService memberService;
	
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}
	@GetMapping("/subscriptions")
	public ResponseEntity<List<Subscription>> getAllSubscriptions(){
		List<Subscription> subscriptions = subscriptionService.findAllSubscriptions();
		return new ResponseEntity(subscriptions, HttpStatus.OK); 
	}
	
	@GetMapping("/subscriptions/{lastName}")
	public ResponseEntity<List<Subscription>> getSubscriptionByLastName
	(@PathVariable("lastName") String lastName){
		List<Subscription> subscription = subscriptionService
				.findSubscriptionByLastName(lastName);
		return new ResponseEntity(subscription, HttpStatus.OK); 
	}
	@GetMapping("/subscriptions/idMembers")
	public ResponseEntity<List<Long>> getIdMembers(){
		List<Member> member = memberService.findAllMembers();
		List<Long> idMembers = new ArrayList<Long>();

		for (Member membre : member) {
			idMembers.add(membre.getIdMember());
		    }
	    		return new ResponseEntity(idMembers, HttpStatus.OK); 
	}
	
	@PostMapping("/subscriptions/{idMember}")
	public ResponseEntity<Subscription> addSubscription(@PathVariable Long idMember){
		Subscription newSubscription = subscriptionService.createSubscription(idMember);
		return new ResponseEntity(newSubscription, HttpStatus.CREATED); 
	}
	@PutMapping("/subscriptions")
	public ResponseEntity<Subscription> updateSubscription
	(@RequestBody Subscription subscription){
		Subscription updateSubscription = subscriptionService
				.updateSubscription(subscription);
		return new ResponseEntity(updateSubscription, HttpStatus.OK); 
	}
	@DeleteMapping("/subscriptions/{id}")
	public ResponseEntity<?> deleteSubscription(@PathVariable("id") Long id){
		subscriptionService.deleteSubscription(id);
		return new ResponseEntity(HttpStatus.OK); 
	}

}
