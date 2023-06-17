package com.eidiko.niranjana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eidiko.niranjana.dao.EmployeeCrudDao;
import com.eidiko.niranjana.entity.Employee;

@Service
public class EmployeeCrudServiceImpl implements EmployeeCrudService {

	@Autowired
	private EmployeeCrudDao employeeCrudDao;
	@Override
	public boolean saveEmploeesData(Employee emp) 
	{
		
		//Call DAO and save into DB
		//EmployeeCrudDao employeeCrudDao = new EmployeeCrudDaoImpl();
		boolean result = employeeCrudDao.saveEmployeeCrudInDB(emp);
		return result;
	}
	@Override
	public String fetchEmployeesData() 
	{
		String data = employeeCrudDao.fetchEmployeeCrudINDB();
		return data;
	}
	
	@Override
	public String insertEmployeesData(String id,String name,String age) 
	{
		 employeeCrudDao.insertEmployeeCrudInDB(id, name, age);
		return "inserted";
	}
	@Override
	public List<Employee> fetchEmployeesData(String id) 
	{
		List<Employee> list = employeeCrudDao.fetchEmployeeCrudFromDBUsingID(id);
		return list;
	}
}
