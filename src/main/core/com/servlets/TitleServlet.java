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
import com.service.TitleDAO;

@WebServlet("/TitleServlet")
public class TitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");

		if(method.equals("getAll")){
			List<Title> titles = TitleDAO.getAllTitles();
			JSONArray array = new JSONArray();
			for (Title title : titles) {
				JSONArray array2 = new JSONArray();
				array2.put(title.getTitleCode());
				array2.put(title.getTitleDescription());
				array2.put(title.getTitleStatus());
				array2.put(title.getTitleId());
				array.put(array2);
			}
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(array.toString());
		}else if(method.equals("create")){
			String code = request.getParameter("code");
			String description=request.getParameter("description");
			String status = request.getParameter("status");
			String result=TitleDAO.insertTitle(code, description, status);
			System.out.println("ok  "+result);
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
