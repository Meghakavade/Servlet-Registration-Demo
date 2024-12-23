package com.jbk;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.Driver;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		 String email=req.getParameter("email");
		 String password=req.getParameter("pass1");
		 
		
		 
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/batch197?useSSL=false","root","root");
			PreparedStatement ps =c.prepareStatement("select *from register where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
		    ResultSet rs=ps.executeQuery();
		    
		    
		    if(rs.next()) {
		    	
		    		 req.setAttribute("fname", rs.getString(1));
		    		 req.setAttribute("lname", rs.getString(2));
		    		 req.setAttribute("email", rs.getString(3));
		    		 req.setAttribute("password", rs.getString(13));
		    		   	
		 			System.out.println("login successfuly...");
		 			PrintWriter out=resp.getWriter();
		 			out.println("<h1 style='color:blue'>login successfully..</h1>");
		 			RequestDispatcher rd=req.getRequestDispatcher("profile.jsp");
		 			rd.forward(req, resp);
		 
		    
		    }
		    else {
		    	System.out.println("try again...");
				PrintWriter out=resp.getWriter();
				out.println("<h1 style='color:blue'>try again..</h1>");
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, resp);
		    }
			c.close();
		 }catch(Exception e) {
			 e.printStackTrace();
			
	}
}
}
