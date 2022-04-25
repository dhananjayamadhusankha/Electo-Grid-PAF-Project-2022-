package org.restapi.crud.emp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.restapi.crud.emp.model.empmodel;

public class empservice {
	
	Connection con;
	
	public empservice()  {
		
		//Database Connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/electrodb", "root","");
			System.out.println("Success");
		}
		catch(Exception e) {
			System.out.println(e +"data insert unsuccess.");
		}
	}
	
public empmodel insertEmp (empmodel emp) {
	String insert = "insert into employee (name, age, address, email, telno, type) values(?,?,?,?,?,?)";
	
	try {
		PreparedStatement ps = con.prepareStatement(insert);
		ps.setString(1, emp.getName());
		ps.setLong(2, emp.getAge());
		ps.setString(3, emp.getAddress());
		ps.setString(4, emp.getEmail());
		ps.setString(5, emp.getTelno());
		ps.setString(6, emp.getType());
		
		//execute the statement
		ps.execute();
		System.out.println("Successfully added!");

	} catch (Exception e) {
		System.out.println(e +"data insert unsuccess.");
	}
	
	return emp;
}
}