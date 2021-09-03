package org.sid.testjunit.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sid.dao.EmployeeRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.Employee;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookReservationStatus;
import org.sid.enumclass.MemberFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MemberRepositoryTest {

	MemberFunction memberFunction = null;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Test
	@Rollback(false)   //pour insérer les données générées par la methode de test
	public void testCreateMember() {

		// given
		  Member member = new Member("Ilyass","Ilyass","123","A1414","Ilyass@gmail.com,"  );
		// when
		  Member saveMember = memberRepository.save(member);
		//then
		assertNotNull(member);
		assertTrue(member.getIdMember()>0);

		Assertions.assertThat(saveMember).isNotNull();
		Assertions.assertThat(member.getIdMember()).isGreaterThan(0);
		}
	@Test
	@Rollback(false)   //pour insérer les données générées par la methode de test
	public void createEmployeeTest() {
		// given
		  Employee employee = new Employee("badr", "badr", "1234", "A1515", "badr@gmail.com" );
		// when
		  Employee saveEmployee = employeeRepository.save(employee);
		//then
		assertNotNull(employee);
		assertTrue(employee.getIdEmployee()>0);

		Assertions.assertThat(saveEmployee).isNotNull();
		Assertions.assertThat(employee.getIdEmployee()).isGreaterThan(0);
	}
	
	  @Test 	
	  public void testFindMemberByName() {
		  Long id = 4L;
		  
		  Optional<Member> member = memberRepository.findById(id);
		  assertEquals(member.get().getIdMember(),id);
		  assertTrue(member.get().getIdMember()>0); 
		  

			Assertions.assertThat(member.get().getIdMember()).isEqualByComparingTo(4L);
	   }
	  @Test 		
	  public void getListOfMemberTest() {
		  List<Member> members = memberRepository.findAll();
		    
			Assertions.assertThat(members.size()).isGreaterThan(0);
	  }
	  	  @Test	
	  	  public void updateMemberTest() {
		  //given
	  		Member memberToUpdate = memberRepository.findById(15L).get();
		  memberToUpdate.setFirstName("xxxxx");

		  
		  Member memberUpdated = memberRepository.save(memberToUpdate);
		  
			Assertions.assertThat(memberUpdated.getFirstName()).isEqualTo("xxxxx");
		}
	  
	    
	  
	  @Test
	  public void deleteMemberTest() {
		  Member member = memberRepository.findById(15L).get();
		  memberRepository.delete(member);
		 
		  Member member1 = null;
		 Optional<Member> optionalMember = memberRepository.findById(15L);
		  if(optionalMember.isPresent()) {
			  member1 = optionalMember.get();
		  }
			Assertions.assertThat(member1).isNull();
		
		}
}
