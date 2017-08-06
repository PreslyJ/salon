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
import com.dto.ProductTypeDTO;
import com.salon.service.ProTypesDAO;
import com.salon.service.RangeDAOS;

@WebServlet("/ProTypesServlet")
public class ProTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");

		if(method.equals("getAll")){
			List<ProductTypeDTO> titles = ProTypesDAO.getAllRecords();
			JSONArray array = new JSONArray();
			for (ProductTypeDTO title : titles) {
				JSONArray array2 = new JSONArray();
				array2.put(title.getRangeCode());
				array2.put(title.getRangeDescription());
				array2.put(title.getCode());
				array2.put(title.getDescription());
				array2.put(title.getProductAmount());
				array2.put(title.getStatus());
				array2.put(title.getProductTypeId());
				array.put(array2);
			}
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(array.toString());
		}else if(method.equals("create")){
			String code = request.getParameter("code");
			String description=request.getParameter("description");
			String status = request.getParameter("status");
			int rangeid=Integer.valueOf(request.getParameter("range"));
			double amt=Double.valueOf(request.getParameter("amt"));
			
			String result=ProTypesDAO.insertTitle(code, description, status,rangeid,amt);
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
			
			String result=ProTypesDAO.deleteTitle(titleId);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result );		
		}else if(method.equals("getProductRanges")){
			List<Range> titles =RangeDAOS.getAllTitles();
			JSONArray array = new JSONArray();
			for (Range title : titles) {
				JSONArray array2 = new JSONArray();
				array2.put(title.getRangeId());
				array2.put(title.getCode());
				array.put(array2);
			}
	
			JSONArray titlesOfficerTypesArray=new JSONArray();
			titlesOfficerTypesArray.put(array);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(titlesOfficerTypesArray.toString());	
		}
	}

}
