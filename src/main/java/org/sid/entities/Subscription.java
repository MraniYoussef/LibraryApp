package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Subscription implements Serializable {
	@Id @GeneratedValue
	private Long idSubscription;
	//private Employee employee;
	@OneToOne
	@JoinColumn(name="Id_Member")	
	private Member member;
	private int BlockageDaysPeriod;
	private Date unblockDate;
	private Boolean isBlocked= false;
	private Date dateMembership;
	private double price=0;
	
	
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Subscription( Member member, int blockageDaysPeriod, Boolean isBlocked) {
		super();
		
		this.member =  member;
		BlockageDaysPeriod = blockageDaysPeriod;
		this.isBlocked = isBlocked;
	}

	public Subscription(Member memberToSubscribe, int blockageDaysPeriod, boolean b) {
		// TODO Auto-generated constructor stub
	}

	public Subscription(Optional<Member> memberToSubscribe, int i, boolean b) {
		// TODO Auto-generated constructor stub
	}

	public Subscription(Optional<Member> memberToSubscribe, Object object, boolean b) {
		// TODO Auto-generated constructor stub
	}

	public Long getIdSubscription() {
		return idSubscription;
	}
	public void setIdSubscription(Long idSubscription) {
		this.idSubscription = idSubscription;
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getBlockageDaysPeriod() {
		return BlockageDaysPeriod;
	}
	public void setBlockageDaysPeriod(int blockageDaysPeriod) {
		BlockageDaysPeriod = blockageDaysPeriod;
	}
	
	public Date getUnblockDate() {
		return unblockDate;
	}

	public void setUnblockDate(Date unblockDate) {
		this.unblockDate = unblockDate;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Date getDateMembership() {
		return dateMembership;
	}

	public void setDateMembership(Date dateMembership) {
		this.dateMembership = dateMembership;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
