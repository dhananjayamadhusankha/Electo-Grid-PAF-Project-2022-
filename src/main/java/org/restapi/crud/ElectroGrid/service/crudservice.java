package org.restapi.crud.ElectroGrid.service;


import java.sql.*;
import java.util.ArrayList;

import org.restapi.crud.ElectroGrid.model.crudmodel;


public class crudservice {
	
	Connection con;
	
	public crudservice()  {
		
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
	
	//Add details about the user
	public crudmodel insertUser(crudmodel user) {
		
		// create a prepared statement
		String insert = "insert into user(name, email, address, phoneno, type) values(?,?,?,?,?) ";
		
		// binding values to user table
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getPhoneno());
			ps.setString(5, user.getType());
			
			//execute the statement
			ps.execute();
			System.out.println("Successfully added!");
				
		}catch(Exception e) {
			System.out.println(e +"data insert unsuccess.");
		}
		
		return user;
	
	}
	
	//retrieve all data
	public ArrayList<crudmodel> getUser() throws SQLException{
		
		ArrayList<crudmodel> data = new ArrayList<crudmodel>();
		
		String select = "select * from user";

		PreparedStatement ps = con.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			crudmodel model = new crudmodel();
			
			model.setName(rs.getString("name"));
			model.setEmail(rs.getString("email"));
			model.setAddress(rs.getString("address"));
			model.setPhoneno(rs.getString("phoneno"));
			model.setType(rs.getString("type"));
			
			data.add(model);
		}
		
		return data;
	} 
	
	//search by id
	public ArrayList<crudmodel> getUserById(int id) throws SQLException{
			
			ArrayList<crudmodel> data = new ArrayList<crudmodel>();
			
			String select = "select * from user where id = ?";
	
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				crudmodel model = new crudmodel();
				
				model.setName(rs.getString("name"));
				model.setEmail(rs.getString("email"));
				model.setAddress(rs.getString("address"));
				model.setPhoneno(rs.getString("phoneno"));
				model.setType(rs.getString("type"));
				
				data.add(model);
			}
			
			return data;
	} 
	
	
	//Update the user details
	public crudmodel updateUser(crudmodel user) {
		
		// create a prepared statement
		String insert = "update user set name=?, email=?, address=?, phoneno=?, type=? "
				+ "where id=?";
		
		// binding values to query
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getPhoneno());
			ps.setString(5, user.getType());
			ps.setInt(6, user.getId());
			
			// Execute the statement
			ps.executeUpdate();
			System.out.println("Successfully Updated!");
				
		} catch(Exception e) {
			System.out.println(e +"data update unsuccess.");
		}
		
		return user;
	
	}
	
	// implement the delete payment
	public int deleteUser(int id) {
		
		// create a prepared statement
		String insert = "delete from user where id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			
			ps.setInt(1, id);
			
			// executing the statements
			ps.executeUpdate();
			System.out.println("Successfully deleted!");
				
		}catch(Exception e) {
			System.out.println(e +"data delete unsuccess.");
		}
		
		return id;
	
	}

}
