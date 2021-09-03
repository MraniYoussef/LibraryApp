package org.sid.dao;

import org.sid.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository
		extends JpaRepository<Employee, Long> {
	
	 //@Query("select e from Employee em where em.firstName like :x" ) public
	 //Page<Employee> chercher(@Param("x")String em, Pageable pageable);

	public Employee findEmployeeByIdEmployee(Long id);

	public void deleteEmployeeByIdEmployee(Long id);
	 
											 




	
}
