package com.salon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;






import com.domain.Officer;
import com.domain.OfficerType;
import com.domain.Title;
import com.service.OfficerDAO;
import com.service.OfficerTypeDAO;
import com.service.TitleDAO;

@WebServlet("/OfficerServletS")
public class OfficerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
			case "getTitlesAndOfficerTypes" :	List<Title> titles =TitleDAO.getAllTitles();
												List<OfficerType> officertypes=OfficerTypeDAO.getAllOfficerTypes();
												JSONArray officerTypesArray=new JSONArray();
												JSONArray array = new JSONArray();
													for (Title title : titles) {
														JSONArray array2 = new JSONArray();
														array2.put(title.getTitleId());
														array2.put(title.getTitleCode());
														array.put(array2);
													}
													for (OfficerType officerType : officertypes) {
														JSONArray array3 = new JSONArray();
														array3.put(officerType.getOfficerTypeId());
														array3.put(officerType.getCode());
														officerTypesArray.put(array3);
													}
													JSONArray titlesOfficerTypesArray=new JSONArray();
													titlesOfficerTypesArray.put(array);
													titlesOfficerTypesArray.put(officerTypesArray);
													PrintWriter out = response.getWriter();
													response.setContentType("application/json");
													out.print(titlesOfficerTypesArray.toString());	
													break;
			case "create"		 			:	
												String result =addUser(request);
											    response.setContentType("text/plain");
											    response.setCharacterEncoding("UTF-8");
											    response.getWriter().write(result);
												break;
												
			case  "getAll"		  			:	List<Officer> officers=OfficerDAO.getAllOfficers();
												JSONArray officersArray = new JSONArray();
												for (Officer officer : officers) {
													JSONArray officerArray =new JSONArray();
													officerArray.put(TitleDAO.getTitleCodeByTitleId(officer.getTitleId()));
													officerArray.put(officer.getFirstName());										
													officerArray.put(officer.getSurname());
													officerArray.put(officer.getMobileNo());
													officerArray.put(OfficerTypeDAO.getOfficerTypeById(officer.getOfficerTypeId()));
													officerArray.put(officer.getUserName());
													officerArray.put(OfficerDAO.getFullNameByOfficerId(officer.getCreatedUser()));
													officerArray.put(officer.getCreatedDate());
													officerArray.put(officer.getStatus());
													officersArray.put(officerArray);
												}
												PrintWriter writer = response.getWriter();
												response.setContentType("application/json");
												writer.print(officersArray.toString());
												break;				
		}							
	}
	public static String addUser(HttpServletRequest request){	
		String titleId,firstName,surname,designation,mobileNo,officerTypeId,userName,password,status;
		titleId=request.getParameter("titleId");
		firstName=request.getParameter("firstName");
		surname=request.getParameter("surname");
		mobileNo=request.getParameter("mobileNo");
		officerTypeId=request.getParameter("officerTypeId");
		userName=request.getParameter("userName");
		password=request.getParameter("password");
		status=request.getParameter("status");
		int createdUserId=(Integer)request.getSession().getAttribute("officerId");
		return OfficerDAO.addUser(titleId, firstName, surname, mobileNo, officerTypeId, userName, password, status,createdUserId);
	}
}
