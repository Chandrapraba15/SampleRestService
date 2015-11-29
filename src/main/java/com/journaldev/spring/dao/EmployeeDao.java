package com.journaldev.spring.dao;

import com.journaldev.spring.model.Employee;

public interface EmployeeDao {

	public void insert(Employee customer);
	public Employee findByEmployeeId(String custId);
	
}
