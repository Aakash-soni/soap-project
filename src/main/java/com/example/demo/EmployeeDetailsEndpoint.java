package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.aakash.employee.CreateEmployeeDetailsRequest;
import com.aakash.employee.CreateEmployeeDetailsResponse;
import com.aakash.employee.DeleteEmployeeDetailsRequest;
import com.aakash.employee.DeleteEmployeeDetailsResponse;
import com.aakash.employee.EmployeeDetails;
import com.aakash.employee.GetAllEmployeeDetailsRequest;
import com.aakash.employee.GetAllEmployeeDetailsResponse;
import com.aakash.employee.GetEmployeeDetailsRequest;
import com.aakash.employee.GetEmployeeDetailsResponse;
import com.aakash.employee.UpdateEmployeeDetailsRequest;
import com.aakash.employee.UpdateEmployeeDetailsResponse;
import com.example.demo.entity.Employee;




@Endpoint
public class EmployeeDetailsEndpoint {

	@Autowired
	private EmployeeService employeeService;

	@PayloadRoot(namespace = "http://aakash.com/employee", localPart = "GetEmployeeDetailsRequest")
	@ResponsePayload
	public GetEmployeeDetailsResponse processEmployeeDetailsRequest(@RequestPayload GetEmployeeDetailsRequest request) {

		GetEmployeeDetailsResponse response = new GetEmployeeDetailsResponse();

		Employee e = employeeService.getEmployee(request.getId());
		if(e==null)
			throw new EmployeeNotFoundException("No Employee found with Id "+ request.getId());
		EmployeeDetails employee = new EmployeeDetails();
		employee.setId(e.getId());
		employee.setName(e.getName());
		employee.setSalary(e.getSalary());
		response.setEmployeeDetails(employee);
		return response;
	}


	@PayloadRoot(namespace = "http://aakash.com/employee", localPart = "GetAllEmployeeDetailsRequest")
	@ResponsePayload
	public GetAllEmployeeDetailsResponse processAllEmployeeDetailsRequest(@RequestPayload GetAllEmployeeDetailsRequest request) {

		GetAllEmployeeDetailsResponse response = new GetAllEmployeeDetailsResponse();
		List <Employee> employees = employeeService.getAllEmployees();
		List <EmployeeDetails> employee_list = new ArrayList();
		for(Employee e:employees)
		{
			EmployeeDetails ed = new EmployeeDetails();
			ed.setId(e.getId());
			ed.setName(e.getName());
			ed.setSalary(e.getSalary());
			employee_list.add(ed);
		}

		response.setEmployeeDetails(employee_list);
		return response;
	}


	@PayloadRoot(namespace = "http://aakash.com/employee", localPart = "CreateEmployeeDetailsRequest")
	@ResponsePayload
	public CreateEmployeeDetailsResponse createEmployeeDetailsRequest(@RequestPayload CreateEmployeeDetailsRequest request) {

		CreateEmployeeDetailsResponse response = new CreateEmployeeDetailsResponse();
		Employee e = new Employee();
		e.setId(request.getId());
		e.setName(request.getName());
		e.setSalary(request.getSalary());
		employeeService.insert(e);

		EmployeeDetails ed = new EmployeeDetails();
		ed.setId(request.getId());
		ed.setName(request.getName());
		ed.setSalary(request.getSalary());
		response.setEmployeeDetails(ed);
		return response;

	}
	
	
	@PayloadRoot(namespace = "http://aakash.com/employee", localPart = "DeleteEmployeeDetailsRequest")
	@ResponsePayload
	public DeleteEmployeeDetailsResponse deleteEmployeeDetailsRequest(@RequestPayload DeleteEmployeeDetailsRequest request) {
    
		DeleteEmployeeDetailsResponse response = new DeleteEmployeeDetailsResponse();
		if(employeeService.deleteEmployee(request.getId()))
		{
			response.setStatus("Successfull Delete");
		}
		else
		{
			throw new EmployeeNotFoundException("No Employee found with Id "+ request.getId());
		}
		return response;
		
	}
	
	
	@PayloadRoot(namespace = "http://aakash.com/employee", localPart = "UpdateEmployeeDetailsRequest")
	@ResponsePayload
	public UpdateEmployeeDetailsResponse updateEmployeeDetailsRequest(@RequestPayload UpdateEmployeeDetailsRequest request) {
    
		UpdateEmployeeDetailsResponse response = new UpdateEmployeeDetailsResponse();
		
		Employee e = new Employee();
		e.setId(request.getId());
		e.setName(request.getName());
		e.setSalary(request.getSalary());
		employeeService.updateEmployee(e);

		EmployeeDetails ed = new EmployeeDetails();
		ed.setId(request.getId());
		ed.setName(request.getName());
		ed.setSalary(request.getSalary());
		response.setEmployeeDetails(ed);
		return response;
		
		
	}
	
	
	
	
	



}
