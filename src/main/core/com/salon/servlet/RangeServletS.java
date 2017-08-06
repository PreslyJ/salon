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

import com.domain.Range;
import com.salon.service.RangeDAOS;

@WebServlet("/RangeServletS")
public class RangeServletS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");

		if(method.equals("getAll")){
			List<Range> titles = RangeDAOS.getAllTitles();
			JSONArray array = new JSONArray();
			for (Range title : titles) {
				JSONArray array2 = new JSONArray();
				array2.put(title.getCode());
				array2.put(title.getDescription());
				array2.put(title.getStatus());
				array2.put(title.getRangeId());
				array.put(array2);
			}
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(array.toString());
		}else if(method.equals("create")){
			String code = request.getParameter("code");
			String description=request.getParameter("description");
			String status = request.getParameter("status");
			String result=RangeDAOS.insertTitle(code, description, status);
			System.out.println("ok  "+result);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result +" ");

			}else if(method.equals("modify")){
			String code = request.getParameter("code");
			String description=request.getParameter("description");
			String status = request.getParameter("status");
			int titleId = Integer.parseInt(request.getParameter("titleId"));
			
			String result=RangeDAOS.modifyTitle(code, description, status, titleId);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result +" ");		
		}else if(method.equals("delete")){
			int titleId = Integer.parseInt(request.getParameter("titleId"));
			
			String result=RangeDAOS.deleteTitle(titleId);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result );		
		}
	}

}
