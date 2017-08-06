package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Officer;
import com.service.LoginDAO;
import com.service.OfficerTypeDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String userName,password;
		userName=request.getParameter("username");
		password=request.getParameter("password");
		Officer officer;
		try {
			officer = LoginDAO.authentication(userName, password);
		
			if(officer!=null){
				request.getSession().setAttribute("firstName", officer.getFirstName());
				request.getSession().setAttribute("officerId", officer.getOfficerId());
				request.getSession().setAttribute("OfficerTypeId", officer.getOfficerTypeId());
				String officerType = OfficerTypeDAO.getOfficerTypeById(officer.getOfficerTypeId());
				request.getSession().setAttribute("OfficerType", officerType);
				request.getRequestDispatcher("body.jsp").forward(request, response);
			}else{
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("index.jsp");
		}
  	}
  	
  	
  	
}
