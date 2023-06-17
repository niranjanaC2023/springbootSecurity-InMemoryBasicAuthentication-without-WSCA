package com.eidiko.niranjana.dao;
import java.util.List;

import com.eidiko.niranjana.entity.Employee;

public interface EmployeeCrudDao 
{
	
	public boolean saveEmployeeCrudInDB(Employee emp);
	
	public String insertEmployeeCrudInDB(String id,String name,String age);

	public String fetchEmployeeCrudINDB();
	
	public List<Employee> fetchEmployeeCrudFromDBUsingID(String id);

}
