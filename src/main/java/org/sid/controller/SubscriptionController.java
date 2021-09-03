package org.sid.controller;

import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.Subscription;
import org.sid.service.BookService;
import org.sid.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
	private final SubscriptionService subscriptionService;
	
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}
	@GetMapping("/all")
	public ResponseEntity<List<Subscription>> getAllSubscriptions(){
		List<Subscription> subscriptions = subscriptionService.findAllSubscriptions();
		return new ResponseEntity(subscriptions, HttpStatus.OK); 
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Subscription> getSubscriptionByIdSubscription
	(@PathVariable("id") Long id){
		Subscription subscription = subscriptionService
				.findSubscriptionByIdSubscription(id);
		return new ResponseEntity(subscription, HttpStatus.OK); 
	}
	
	@PostMapping("/add")
	public ResponseEntity<Subscription> addSubscription(@RequestBody Long idMember){
		Subscription newSubscription = subscriptionService.createSubscription(idMember);
		return new ResponseEntity(newSubscription, HttpStatus.CREATED); 
	}
	@PutMapping("/update")
	public ResponseEntity<Subscription> updateSubscription
	(@RequestBody Subscription subscription){
		Subscription updateSubscription = subscriptionService
				.updateSubscription(subscription);
		return new ResponseEntity(updateSubscription, HttpStatus.OK); 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSubscription(@PathVariable("id") Long id){
		subscriptionService.deleteSubscription(id);
		return new ResponseEntity(HttpStatus.OK); 
	}

}
