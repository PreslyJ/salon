package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.domain.Title;
import com.service.ApplicantRegistrationDAO;
import com.service.TitleDAO;


@WebServlet("/ApplicantRegistrationServlet")
public class ApplicantRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
			case "getTitlesPostalCodesCountries" :	List<Title> titles =TitleDAO.getAllTitles();
													JSONArray array = new JSONArray();
													for (Title title : titles) {
														JSONArray array2 = new JSONArray();
														array2.put(title.getTitleId());
														array2.put(title.getTitleCode());
														array.put(array2);
													}
											
													JSONArray titlesOfficerTypesArray=new JSONArray();
													titlesOfficerTypesArray.put(array);
													PrintWriter out = response.getWriter();
													response.setContentType("application/json");
													out.print(titlesOfficerTypesArray.toString());	
													break;
			case "create"		 			:	String result =addApplicant(request);
											    response.setContentType("text/plain");
											    response.setCharacterEncoding("UTF-8");
											    response.getWriter().write(result);
												break;											
		}							
	}
	public static String addApplicant (HttpServletRequest request){	
		String titleId,initials,initialsFull,surname,NIC,businessName,businessRegNo,address1,address2,city,state,postalCode,country,mobileNo,officeNo,status;
		titleId=request.getParameter("titleId");
		initials=request.getParameter("firstName");
		initialsFull=request.getParameter("initialsFull");
		surname=request.getParameter("surname");
		NIC=request.getParameter("NIC");
		businessName=request.getParameter("businessName");
		businessRegNo=request.getParameter("businessRegNo");
		address1=request.getParameter("address1");
		address2=request.getParameter("address2");
		city=request.getParameter("city");
		state=request.getParameter("state");
		postalCode=request.getParameter("postalCode");
		country=request.getParameter("country");
		mobileNo=request.getParameter("mobileNo");
		officeNo=request.getParameter("officeNo");
		status=request.getParameter("status");
		return ApplicantRegistrationDAO.addApplicant(titleId, initials, initialsFull, surname, NIC, businessName, businessRegNo, address1, address2, city, state, postalCode, country, mobileNo, officeNo, status);
	}
}


