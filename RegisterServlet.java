package com.jbk;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.cj.jdbc.Driver;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regform")
public class RegisterServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		 String fname=req.getParameter("name1");
		 String lname=req.getParameter("name2");
		 String email=req.getParameter("email");
		 String mobno=req.getParameter("num");
		 String address=req.getParameter("add");
		 String gender=req.getParameter("gender1");
		 String dob=req.getParameter("birthday");
		 String city=req.getParameter("city");
		 String state=req.getParameter("state1");
		 String country=req.getParameter("country1");
		 String degree=req.getParameter("degree1");
		 String passyear=req.getParameter("PassYear");
		 String password=req.getParameter("pass");
		
		 
		 
		
		 
		 PrintWriter out=resp.getWriter();
		 out.print("Data is inserted successfully");
		 
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/batch197?useSSL=false","root","root");
			PreparedStatement ps =c.prepareStatement("insert into register (fname,lname,email,mobno,address,gender,dob,city,state,country,degree,passyear,password)values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, mobno);
			ps.setString(5, address);
			ps.setString(6, gender);
			ps.setString(7, dob);
			ps.setString(8, city);
			ps.setString(9, state);
			ps.setString(10, country);
			ps.setString(11, degree);
			ps.setString(12, passyear);
			ps.setString(13, password);
			
			
			ps.executeUpdate();
			System.out.println("data inserted successfully..");
			c.close();
		 }catch(Exception e) {
			 e.printStackTrace();			}
		 
	}

	

}
