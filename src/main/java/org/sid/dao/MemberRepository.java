package org.sid.dao;

import org.sid.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository
		extends JpaRepository<Member, Long> {
	
	 //@Query("select m from Member me where me.firstName like :x" ) public
	 //Page<Member> chercher(@Param("x")String me, Pageable pageable);

	public Member findMemberByIdMember(Long id);

	public void deleteMemberByIdMember(Long id);
	 
											 




	
}
