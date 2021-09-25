package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;
import org.sid.enumclass.MemberFunction;
@Proxy(lazy=false)
@Entity
public class Member  implements Serializable {
	@Id @GeneratedValue
	private Long idMember;
	private String firstName;
	private String lastName;
	private String password;
	private String cin;
	private String email;
	private String address;
	private Date dateOfMembership;
	private MemberFunction fonction;
	@OneToOne(cascade= CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name="Id_Subscription")	
	private Subscription subscription;
	
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Member(String firstName, String lastName, String password, String cin, String email, String address,
			Date dateOfMembership, MemberFunction fonction, Subscription subscription) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.cin = cin;
		this.email = email;
		this.address = address;
		this.dateOfMembership = dateOfMembership;
		this.fonction = fonction;
		this.subscription = subscription;
	}

	public Member(String firstName, String lastName, String password, String cin, String email) {
		
		// TODO Auto-generated constructor stub
	}

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfMembership() {
		return dateOfMembership;
	}

	public void setDateOfMembership(Date dateOfMembership) {
		this.dateOfMembership = dateOfMembership;
	}

	

	public MemberFunction getFonction() {
		return fonction;
	}

	public void setFonction(MemberFunction fonction) {
		this.fonction = fonction;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	@Override
	public String toString() {
		return "Member [idMember=" + idMember + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", cin=" + cin + ", email=" + email + ", address=" + address + ", dateOfMembership="
				+ dateOfMembership + ", fonction=" + fonction + "]";
	}

	
}
