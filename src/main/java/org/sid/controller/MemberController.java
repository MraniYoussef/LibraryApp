package org.sid.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookReservationStatus;
import org.sid.enumclass.MemberFunction;
import org.sid.service.BookService;
import org.sid.service.MemberService;
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
public class MemberController {
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@GetMapping("/members")
	public ResponseEntity<List<Member>> getAllmembers(){
		List<Member> members = memberService.findAllMembers();
			
		return new ResponseEntity(members, HttpStatus.OK); 
	}
	
	@GetMapping("/members/{lastName}")
	public ResponseEntity<List<Member>> getMemberByLastName(@PathVariable("lastName") String lasName){
		List<Member> member = memberService.findMemberByLastName(lasName);
		return new ResponseEntity(member, HttpStatus.OK); 
	}
	
	@PostMapping("/members")
	public ResponseEntity<Member> addMember(@RequestBody Member member){
		Member newMember = memberService.createMember(member);
		return new ResponseEntity(newMember, HttpStatus.CREATED); 
	}
	@GetMapping("/members/fonction")
	public ResponseEntity<List<MemberFunction>> getMemberFunction(){
		List<MemberFunction> memberFunction = Arrays.asList(MemberFunction.values());
		return new ResponseEntity(memberFunction, HttpStatus.OK); 
	}
	@PutMapping("/members/{idMember}")
	public ResponseEntity<Member> updateMember(@PathVariable Long idMember, @RequestBody Member memberDetails){
		Member member = memberService.findMemberByIdMember(idMember);
		member.setFirstName(memberDetails.getFirstName());
		member.setLastName(memberDetails.getLastName());
		member.setPassword(memberDetails.getPassword());
		member.setCin(memberDetails.getCin());
		member.setEmail(memberDetails.getEmail());
		member.setAddress(memberDetails.getAddress());
		member.setFonction(memberDetails.getFonction());
		Member memberToUpdate = memberService.updateMember(member);

		return new ResponseEntity(memberToUpdate, HttpStatus.OK); 
	}
	@DeleteMapping("/members/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable("id") Long id){
		memberService.deleteMember(id);
		return new ResponseEntity(HttpStatus.OK); 
	}

}
