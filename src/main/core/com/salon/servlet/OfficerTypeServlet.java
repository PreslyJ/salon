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

import com.domain.OfficerType;
import com.salon.service.OfficerTypeDAOS;
import com.service.OfficerTypeDAO;

@WebServlet("/OfficerTypeS")
public class OfficerTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String method=request.getParameter("method");
  		
  		switch(method){
		case "getAll" :   		List<OfficerType> officerTypes = OfficerTypeDAOS.getAllOfficerTypes();
								JSONArray array = new JSONArray();
								for (OfficerType officerType : officerTypes) {
									JSONArray array2 = new JSONArray();
									array2.put(officerType.getCode());
									array2.put(officerType.getDescription());
									array2.put(officerType.getStatus());
									array2.put(officerType.getOfficerTypeId());
									array.put(array2);
								}
								PrintWriter out = response.getWriter();
								response.setContentType("application/json");
								out.print(array.toString());
								break;
								
		case "create" :			String result =addOfficerType(request);
							    response.setContentType("text/plain");
							    response.setCharacterEncoding("UTF-8");
							    response.getWriter().write(result);
								break;
								
		case "modify" :			String resultMod =modifyOfficerType(request);
								System.out.println("ok  "+resultMod);
							    response.setContentType("text/plain");
							    response.setCharacterEncoding("UTF-8");
							    response.getWriter().write(resultMod);
							    break;
		case "delete" :			String resultDel =deleteOfficerType(request);
								System.out.println("ok  "+resultDel);
							    response.setContentType("text/plain");
							    response.setCharacterEncoding("UTF-8");
							    response.getWriter().write(resultDel);
							    break;
  		}
  	}
  	public static String addOfficerType(HttpServletRequest request){
  		String code = request.getParameter("code");
	    String description = request.getParameter("description");
	    String status = request.getParameter("status");
	    String result=OfficerTypeDAOS.addOfficerType(code, description, status);
	    return result;
  	}
  	
	public static String modifyOfficerType(HttpServletRequest request){
  		String code = request.getParameter("code");
		String description=request.getParameter("description");
	    String status = request.getParameter("status");
	    int officerTypeId = Integer.parseInt(request.getParameter("officerTypeId"));
	    String result=OfficerTypeDAOS.modifyOfficerType(code, description, status, officerTypeId);
	    return result;
  	}
  	
  	public static String deleteOfficerType(HttpServletRequest request){
	    int loanTypeId = Integer.parseInt(request.getParameter("officerTypeId"));
	    String result=OfficerTypeDAOS.deleteOfficerType(loanTypeId);
	    return result;
  	}

}


