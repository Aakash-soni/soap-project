package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmployeeRepo;
import com.example.demo.entity.Employee;

@Component
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo; 
	
	
	public Employee getEmployee(int id)
	{
		
		return employeeRepo.findById(id);
		
	}
	
	public List <Employee> getAllEmployees()
	{
		List <Employee> employees = (List <Employee>) employeeRepo.findAll();
		return employees;
	}
	
	public Employee insert(Employee e)
	{
		return employeeRepo.save(e);
	}
	
	public boolean deleteEmployee(int eid)
	{
		try {
		employeeRepo.deleteById(eid);
		return true;}
		catch(Exception e)
		{
			return false;
		}
	
	}
	
	
	public void updateEmployee(Employee e)
	{
		employeeRepo.save(e);
	}

}
