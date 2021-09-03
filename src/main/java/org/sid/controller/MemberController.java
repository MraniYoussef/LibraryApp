package org.sid.controller;

import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.Member;
import org.sid.service.BookService;
import org.sid.service.MemberService;
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
@RequestMapping("/user")
public class MemberController {
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@GetMapping("/all")
	public ResponseEntity<List<Member>> getAllmembers(){
		List<Member> members = memberService.findAllMembers();
			
		return new ResponseEntity(members, HttpStatus.OK); 
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Member> getUserByIdUser(@PathVariable("id") Long id){
		Member member = memberService.findMemberByIdMember(id);
		return new ResponseEntity(member, HttpStatus.OK); 
	}
	
	@PostMapping("/add")
	public ResponseEntity<Member> addMember(@RequestBody Member member){
		Member newMember = memberService.createUser(member);
		return new ResponseEntity(newMember, HttpStatus.CREATED); 
	}
	@PutMapping("/update")
	public ResponseEntity<Member> updateMember(@RequestBody Member member){
		Member updateMember = memberService.updateMember(member);
		return new ResponseEntity(updateMember, HttpStatus.OK); 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable("id") Long id){
		memberService.deleteMember(id);
		return new ResponseEntity(HttpStatus.OK); 
	}

}
