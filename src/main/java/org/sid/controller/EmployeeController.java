package org.sid.controller;

import java.util.Arrays;
import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.Employee;
import org.sid.entities.Member;
import org.sid.entities.Subscription;
import org.sid.enumclass.BookReservationStatus;
import org.sid.enumclass.MemberFunction;
import org.sid.service.BookService;
import org.sid.service.EmployeeService;
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
public class EmployeeController {
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> employee = employeeService.findAllEployees();
			
		return new ResponseEntity(employee, HttpStatus.OK); 
	}
	
	@GetMapping("/employees/{lastName}")
	public ResponseEntity<List<Employee>> getEmployeeByLastName(@PathVariable("lastName") String lastName){
		List<Employee> employee = employeeService.findEmployeeByLastName(lastName);
		return new ResponseEntity(employee, HttpStatus.OK); 
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		Employee newEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity(newEmployee, HttpStatus.CREATED); 
	}
	
	@PutMapping("/employees/{idEmployee}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long idEmployee, @RequestBody Employee employeeDetails){
		Employee employee = employeeService.findEmployeeByIdEmployee(idEmployee);
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setPassword(employeeDetails.getPassword());
		employee.setCin(employeeDetails.getCin());
		employee.setEmail(employeeDetails.getEmail());
		Employee employeeToUpdate = employeeService.updateEmployee(employee);

		return new ResponseEntity(employeeToUpdate, HttpStatus.OK); 
	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable("id") Long id){
		employeeService.deleteEmployee(id);
		return new ResponseEntity(HttpStatus.OK); 
	}

}
