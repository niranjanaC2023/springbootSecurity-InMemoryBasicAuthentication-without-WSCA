package com.eidiko.niranjana.service;

import java.util.List;

import com.eidiko.niranjana.entity.Employee;


public interface EmployeeCrudService {

	public boolean saveEmploeesData(Employee emp);
	
	public String insertEmployeesData(String id,String name,String age);

	public String fetchEmployeesData();
	
	public List<Employee> fetchEmployeesData(String id);
}
