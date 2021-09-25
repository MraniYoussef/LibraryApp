package org.sid.service;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.BookReservationRepository;
import org.sid.dao.EmployeeRepository;
import org.sid.dao.MemberRepository;
import org.sid.entities.Book;
import org.sid.entities.BookReservation;
import org.sid.entities.Employee;
import org.sid.entities.Member;
import org.sid.exception.BookNotFoundException;
import org.sid.exception.BookReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository= employeeRepository;
	}
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	public List<Employee> findAllEployees(){
		return employeeRepository.findAll();
	}
	public Employee findEmployeeByIdEmployee(Long id) {
		return employeeRepository.findEmployeeByIdEmployee(id);
	}
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	@Transactional	
	public void deleteEmployee(Long id) {
		employeeRepository.deleteEmployeeByIdEmployee(id);
	}
	public List<Employee> findEmployeeByLastName(String lastName) {
		return employeeRepository.findEmployeeByLastName(lastName);
	}
	
	
}
