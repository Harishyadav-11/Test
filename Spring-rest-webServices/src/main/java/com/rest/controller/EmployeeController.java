package com.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dao.EmployeeDao;
import com.rest.exception.ResourceNotFoundException;
import com.rest.model.Emploii;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao repo;
	
	@PostMapping("/createEmp")
	public Emploii createEmployee(@RequestBody Emploii emp) {
		return repo.save(emp);	
	}
	@GetMapping("/employees")
	public List<Emploii> getAllEmployees() {
		return repo.findAll();
	}
//	@GetMapping("/employee/{id}")
//	public Optional<Emploii> findEmpById(@PathVariable long id) {
//		return repo.findById(id);	
//	}
	//find employee by id
	@GetMapping("/employee/{id}")
	public ResponseEntity<Emploii> findEmployeeById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
		Emploii emploii =  repo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee id not found"+employeeId));;
		return ResponseEntity.ok().body(emploii);
	}
	// this is update and  pull it  
	//update employee
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<Emploii> updateEmployee(@PathVariable(value = "id") long employeeId,@RequestBody Emploii employeeDetails) throws ResourceNotFoundException {
		Emploii emploii = repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee id not found"+employeeId));
		emploii.setFirstName(employeeDetails.getFirstName());
		emploii.setLastName(employeeDetails.getLastName());
		emploii.setEmailId(employeeDetails.getEmailId());
		repo.save(emploii);
		
		return ResponseEntity.ok().body(emploii);
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public ResponseEntity<Emploii> deleteEmployeeById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
		Emploii emploii =  repo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee id not found"+employeeId));
		repo.deleteById(employeeId);
		return ResponseEntity.ok().build();
	}

}
