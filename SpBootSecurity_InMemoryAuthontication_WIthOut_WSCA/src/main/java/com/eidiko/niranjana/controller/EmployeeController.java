package com.eidiko.niranjana.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.niranjana.entity.Employee;
import com.eidiko.niranjana.service.EmployeeCrudService;

@RestController
@RequestMapping(value="/employee")
//@EnableMethodSecurity
public class EmployeeController 
{
	
	@Autowired
	private EmployeeCrudService employeeCrudService;
	
	@PostMapping("/insert")
	public boolean insertEmployeeData(@RequestBody Employee emp)
	{
		boolean result = employeeCrudService.saveEmploeesData(emp);		
		return result;		
	}
	
	@GetMapping("/fetch")
	public String fetchEmployeeData()
	{
		String data = employeeCrudService.fetchEmployeesData();
		return data;
	}
	
	//@PreAuthorize("hasRole('ADMIN')")  //IF YOU USE THIS ANNOTATION THEN YOU MUST USE @EnableMethodSecurity in top of the controller class
	@GetMapping("/fetchEmployeeData/{id}")
	public ResponseEntity<List<Employee>> fetchEmployeeData(@PathVariable String id)
	{
		List<Employee> data = employeeCrudService.fetchEmployeesData(id);
		return new ResponseEntity<List<Employee>>(data,HttpStatus.OK);		
	}
}
