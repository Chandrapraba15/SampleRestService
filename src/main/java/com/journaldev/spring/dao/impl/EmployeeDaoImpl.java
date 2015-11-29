package com.journaldev.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.journaldev.spring.dao.EmployeeDao;
import com.journaldev.spring.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{

private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Employee employee){
		
		String sql = "INSERT INTO Employee " +
				"(ID, NAME) VALUES (?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getId());
			ps.setString(2, employee.getName());			
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public Employee findByEmployeeId(String custId){
		
		String sql = "SELECT * FROM user WHERE name = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, custId);
			Employee Employee = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee = new Employee(					
					rs.getString("NAME")					
				);
			}
			rs.close();
			ps.close();
			return Employee;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
