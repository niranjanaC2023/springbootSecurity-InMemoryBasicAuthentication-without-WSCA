package com.eidiko.niranjana.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eidiko.niranjana.entity.Employee;

@Repository
public class EmployeeCrudDaoImpl implements EmployeeCrudDao 
{
	@Override
	public boolean saveEmployeeCrudInDB(Employee emp) 
	{
		Connection con = null;
		PreparedStatement ps =null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class is Loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projecteidiko","root","root");
			System.out.println("Connection Created");
			ps = con.prepareStatement("INSERT INTO employee(id,name,age) VALUES(?,?,?)");
			ps.setString(1,""+emp.getId());
			ps.setString(2,""+emp.getName());
			ps.setString(3,""+emp.getAge());
			int record = ps.executeUpdate();
			System.out.println("data added in DB: "+record);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		    }
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		return true;
	}

	@Override
	public String fetchEmployeeCrudINDB() 
	{
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rst = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class is Loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projecteidiko","root","root");
			System.out.println("Connection Created");
			ps = con.prepareStatement("SELECT id,name,age from employee");
			 rst = ps.executeQuery();
			System.out.println("data from dbb in DB: ");
			while(rst.next()) 
			{
	            System.out.print("ID is :"+rst.getString(1));
	            System.out.print("\t\tNAME is: "+rst.getString(2));
	            System.out.print("\t\tAGE is: "+rst.getString(3));
	            System.out.println(); 
	         }
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (rst != null) {
		        try {
		            rst.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		    }
			if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) {
		           e.printStackTrace();
		        }
		    }
			if (con != null) 
			{
		        try {
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		return "data fetched";
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public String insertEmployeeCrudInDB(String id,String name,String age) 
	{
		String INSERT_DATA= "INSERT INTO employee(id,name,age)values(?,?,?)";
		int count=0;
		try {
			count = jdbcTemplate.update(INSERT_DATA,id,name,age);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("No. of Data Inserted int DB: "+count);
		return "data inserted successfully";
	}

	@Override
	public List<Employee> fetchEmployeeCrudFromDBUsingID(String id) {
		String FETH_DATA= "select * from employee where id=?";
		List<Employee> data  = null;
		Object[] args = {id};
		try {
			 data = jdbcTemplate.query(FETH_DATA, args,BeanPropertyRowMapper.newInstance(Employee.class));
			 System.out.println("fetched data: "+data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
		
	
}
