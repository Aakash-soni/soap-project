package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepo extends CrudRepository <Employee,Integer>{
	public Employee findById(int id);

}
