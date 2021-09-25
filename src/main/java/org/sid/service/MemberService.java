package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.sid.dao.BookReservationRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.enumclass.MemberFunction;
import org.sid.exception.BookNotFoundException;
import org.sid.exception.BookReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository= memberRepository;
	}
	public Member createMember(Member member) {
		Member memberToSave = new Member();
		memberToSave.setFirstName(member.getFirstName());
		memberToSave.setLastName(member.getLastName());
		memberToSave.setAddress(member.getAddress());
		memberToSave.setCin(member.getCin());
		memberToSave.setDateOfMembership(new Date());
		memberToSave.setEmail(member.getEmail());
		memberToSave.setFonction(member.getFonction());
		memberToSave.setPassword(member.getPassword());
		return memberRepository.save(memberToSave);
	}
	public List<Member> findAllMembers(){
		return memberRepository.findAll();
	}
	public Member findMemberByIdMember(Long id) {
		return memberRepository.findMemberByIdMember(id);
	}
	public List<Member> findMemberByLastName(String lastName) {
		return memberRepository.findMemberByLastName(lastName);
	}
	
	public Member updateMember(Member user) {
		return memberRepository.save(user);
	}
		@Transactional
	public void deleteMember(Long id) {
		memberRepository.deleteMemberByIdMember(id);
	}
	
	
}
