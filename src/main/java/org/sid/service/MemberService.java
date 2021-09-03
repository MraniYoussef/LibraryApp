package org.sid.service;

import java.util.List;
import org.sid.dao.BookReservationRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Member;
import org.sid.exception.BookNotFoundException;
import org.sid.exception.BookReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository= memberRepository;
	}
	public Member createUser(Member user) {
		return memberRepository.save(user);
	}
	public List<Member> findAllMembers(){
		return memberRepository.findAll();
	}
	public Member findMemberByIdMember(Long id) {
		return memberRepository.findMemberByIdMember(id);
	}
	public Member updateMember(Member user) {
		return memberRepository.save(user);
	}
		
	public void deleteMember(Long id) {
		memberRepository.deleteMemberByIdMember(id);
	}
	
	
}
