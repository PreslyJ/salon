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

import com.domain.Client;
import com.domain.Title;
import com.salon.service.ClientRegDAO;
import com.service.TitleDAO;

@WebServlet("/clientRegSServlet")
public class ClientRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");

		if(method.equals("getAll")){
			List<Client> titles = ClientRegDAO.getAll();
			JSONArray array = new JSONArray();
			for (Client title : titles) {
				JSONArray array2 = new JSONArray();
				array2.put(title.getPhoneNo());
				array2.put(title.getFirstName());
				array2.put(title.getLastName());
				array2.put(title.getGender());
				array2.put(title.getEmail());
				array2.put(title.getClientId());
				array.put(array2);
			}
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(array.toString());
		}else if(method.equals("create")){
			String gen = request.getParameter("gen");
			String fsName=request.getParameter("fsName");
			String surname = request.getParameter("surname");
			String mobileNo = request.getParameter("mobileNo");
			String email = request.getParameter("email");			
			String result=ClientRegDAO.insertTitle(gen, fsName, surname,mobileNo,email);
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result +" ");

			}else if(method.equals("modify")){
			String code = request.getParameter("code");
			String description=request.getParameter("description");
			String status = request.getParameter("status");
			int titleId = Integer.parseInt(request.getParameter("titleId"));
			
			String result=TitleDAO.modifyTitle(code, description, status, titleId);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result +" ");		
		}else if(method.equals("delete")){
			int titleId = Integer.parseInt(request.getParameter("titleId"));
			
			String result=TitleDAO.deleteTitle(titleId);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result );		
		}
	}

}
