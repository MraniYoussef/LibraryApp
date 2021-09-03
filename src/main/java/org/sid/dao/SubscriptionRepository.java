package org.sid.dao;

import java.util.Optional;

import org.sid.entities.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	@Query("select s from Subscription s where s.member.firstName like :x" ) 
	public Page<Subscription> chercher(@Param("x")String s, Pageable pageable);

	public Optional<Subscription> findSubscriptionByIdSubscription(Long id);

	public void deleteSubscriptionByIdSubscription(Long id);
	
	@Query("select s from Subscription s where s.member.idMember like :x" ) 
	public Optional<Subscription> findMemberSubscriptions(@Param("x")Long idMember);
	
	
	
}
